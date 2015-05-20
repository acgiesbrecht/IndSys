/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.usi;

import com.chortitzer.industria.web.service.usi.Service_usi;
import com.vividsolutions.jts.geom.Point;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Waypoint;
import org.apache.commons.io.FileUtils;
import org.geotools.data.DefaultTransaction;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.Transaction;
import org.geotools.data.mysql.MySQLDataStoreFactory;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.data.simple.SimpleFeatureStore;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.jdbc.JDBCDataStore;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;
import org.opengis.referencing.NoSuchAuthorityCodeException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.primefaces.event.FileUploadEvent;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Industria
 */
@Named
@Scope("view")
public class ActualizarBaseBean {

    @Inject
    Service_usi service;

    private String status;
    private Boolean up = true;
    private int fileCounter = 0;
    private File shapeFile;
    String tempDir = System.getProperty("java.io.tmpdir");

    @PostConstruct
    private void init() {
        up = true;
    }

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Exito! ", event.getFile().getFileName() + " fue cargado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            String string = event.getFile().getFileName();
            String extension = string.substring(string.length() - 3, string.length());
            string = toCamelCase(string);
            File file = new File(tempDir, string);
            FileUtils.copyInputStreamToFile(event.getFile().getInputstream(), file);
            if (extension.equals("shp")) {
                shapeFile = file;
            }
            fileCounter++;
            if (fileCounter == 5) {
                process(shapeFile);
                fileCounter = 0;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void process(File file) {
        try {
            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            System.out.println(store.getInfo().getTitle());
            System.out.println(store.toString());

            SimpleFeatureSource featureSource = store.getFeatureSource();
            SimpleFeatureCollection collection = featureSource.getFeatures();

            // datastore creation
            MySQLDataStoreFactory factory = new MySQLDataStoreFactory();
            Map<String, String> params = new HashMap<>();
            params.put("database", "industria_usi");
            params.put("dbtype", "mysql");
            params.put("host", "192.168.1.10");
            params.put("port", "3306");
            params.put("user", "root");
            params.put("passwd", "123456");
            JDBCDataStore mysqlDataStore = factory.createDataStore(params);

            SimpleFeatureType schema = featureSource.getSchema();
            mysqlDataStore.removeSchema(schema.getName());
            mysqlDataStore.createSchema(schema);

            String typeName = mysqlDataStore.getTypeNames()[0];
            Transaction transaction = new DefaultTransaction("create");

            SimpleFeatureSource mysqlFeatureSource = mysqlDataStore.getFeatureSource(featureSource.getName());

            if (mysqlFeatureSource instanceof SimpleFeatureStore) {
                SimpleFeatureStore featureStore = (SimpleFeatureStore) mysqlFeatureSource;

                featureStore.setTransaction(transaction);
                try {
                    featureStore.addFeatures(collection);
                    transaction.commit();
                } catch (IOException e) {
                    setStatus(e.getMessage());
                    transaction.rollback();
                } finally {
                    transaction.close();
                }
            }

            //'create GPX '---------------------------
            try {

                CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:4326");
                CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:32721");

                GPX gpx = new GPX();

                SimpleFeatureIterator iterator = collection.features();
                int count = 0;
                while (iterator.hasNext()) {
                    SimpleFeature feature = iterator.next();

                    boolean lenient = true;

                    MathTransform mathTransform = CRS.findMathTransform(sourceCrs, targetCrs, lenient);
                    Point point = (Point) feature.getDefaultGeometry();
                    DirectPosition2D srcDirectPosition2D = new DirectPosition2D(sourceCrs, point.getX(), point.getY());
                    DirectPosition2D destDirectPosition2D = new DirectPosition2D();
                    mathTransform.transform(srcDirectPosition2D, destDirectPosition2D);

                    Waypoint wpnt = new Waypoint();
                    wpnt.setLatitude(destDirectPosition2D.getX());
                    wpnt.setLongitude(destDirectPosition2D.getY());
                    wpnt.setName(feature.getAttribute("NIS").toString() + "-" + feature.getAttribute("USUARIO").toString());
                    wpnt.setTime(new Date());
                    gpx.addWaypoint(wpnt);
                    // System.out.println(wpnt.toString());
                    count++;
                    setStatus("Se encontraron " + String.valueOf(count) + " puntos.\n\n");
                }
                iterator.close();

                gpx.setVersion("1.1");
                gpx.setCreator("CIN-CCH");

                GPXParser parser = new GPXParser();                
                
                FileOutputStream out = new FileOutputStream("\\industria\\gis\\nis.gpx");
                parser.writeGPX(gpx, out);

                out.close();
            } catch (Exception e) {
                setStatus(e.getMessage());
            }
            up = false;
        } catch (IOException e) {
            setStatus(e.getMessage());
        } finally {
        }
    }

    static String toCamelCase(String s) {
        String[] parts = s.split(" ");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase()
                + s.substring(1).toLowerCase();
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the up
     */
    public Boolean getUp() {
        return up;
    }

    /**
     * @param up the up to set
     */
    public void setUp(Boolean up) {
        this.up = up;
    }

}
