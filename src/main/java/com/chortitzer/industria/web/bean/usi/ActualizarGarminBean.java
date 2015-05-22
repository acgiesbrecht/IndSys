/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.usi;

import com.chortitzer.industria.web.dao.usi.Dao_usi;
import com.vividsolutions.jts.geom.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.ViewHandler;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.alternativevision.gpx.GPXParser;
import org.alternativevision.gpx.beans.GPX;
import org.alternativevision.gpx.beans.Waypoint;
import org.apache.commons.io.FileUtils;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureIterator;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.geometry.DirectPosition2D;
import org.geotools.referencing.CRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Industria
 */
@Named
@Scope("view")
public class ActualizarGarminBean {

    @Inject
    Dao_usi dao;

    private String status;
    private Boolean up = true;
    private StreamedContent download;
    private int fileCounter = 0;
    private File shapeFile;
    String tempDir = System.getProperty("java.io.tmpdir");

    public ActualizarGarminBean() {

    }

    @PostConstruct
    private void init() {
        up = true;
    }

    public void refresh() {
        FacesContext context = FacesContext.getCurrentInstance();
        String viewId = context.getViewRoot().getViewId();
        ViewHandler handler = context.getApplication().getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);
    }

    private void process(File file) {
        try {
            //copyFile(event.getFile().getFileName(), event.getFile().getInputstream());

            FileDataStore store = FileDataStoreFinder.getDataStore(file);
            System.out.println(store.getInfo().getTitle());
            System.out.println(store.toString());

            SimpleFeatureSource featureSource = store.getFeatureSource();

            CoordinateReferenceSystem targetCrs = CRS.decode("EPSG:4326");
            CoordinateReferenceSystem sourceCrs = CRS.decode("EPSG:32721");
            //Filter filter = CQL.toFilter("NAME == 'Hwy 31a'");
            //SimpleFeatureCollection collection = featureSource.getFeatures( filter );
            SimpleFeatureCollection collection = featureSource.getFeatures();

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

            System.out.println(tempDir + "\\nis.gpx");

            FileOutputStream out = new FileOutputStream(tempDir + "\\nis.gpx");
            parser.writeGPX(gpx, out);

            out.close();

            download = new DefaultStreamedContent(new FileInputStream(tempDir + "\\nis.gpx"), "text/plain", "nis.gpx");
            up = false;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }
    }

    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Exito! ", event.getFile().getFileName() + " fue cargado.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            String string = event.getFile().getFileName();
            String extension = string.substring(string.length() - 3, string.length());

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

    /**
     * @return the download
     */
    public StreamedContent getDownload() {
        up = true;
        RequestContext.getCurrentInstance().update(":frm");
        return download;
    }

    /**
     * @param download the donwload to set
     */
    public void setDownload(StreamedContent download) {
        this.download = download;
    }

}
