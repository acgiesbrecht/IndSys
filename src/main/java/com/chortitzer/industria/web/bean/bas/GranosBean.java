/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.bas;

import com.chortitzer.industria.web.dao.bas.Dao_bas;
import com.chortitzer.industria.web.domain.bas.DetalleGranos;
import com.chortitzer.industria.web.domain.bas.Tblempresa;
import com.chortitzer.industria.web.domain.bas.Tblpesadas;
import com.chortitzer.industria.web.domain.bas.Tblproductos;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.context.annotation.Scope;

@Named
@Scope("view")
public class GranosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    Dao_bas dao;

    private List<DetalleGranos> granosList;
    private List<DetalleGranos> granosFilteredList;
    private List<Tblempresa> empresaList;
    private List<Tblproductos> productosGranosList;
    private Date fechaDesde;
    private Date fechaHasta;
    private Tblempresa selectedEmpresa;
    private Tblproductos selectedProducto;
    private int selectedRango = 1;
    private long totalBruto;
    private long totalTara;
    private long totalNeto;

    Calendar calendar = Calendar.getInstance();

    @PostConstruct
    private void init() {
        setSelectedRango(1);
        getEmpresaList();
        getProductosGranosList();
        getGranosList();
    }

    /**
     * @return the pesadasList
     */
    public List<DetalleGranos> getGranosList() {

        if (selectedEmpresa == null && getSelectedProducto() == null) {
            granosList = dao.getByDateRange(DetalleGranos.class, "fechahora", fechaDesde, fechaHasta);
        } else if (selectedEmpresa == null && getSelectedProducto() != null) {
            granosList = dao.getViewByDateRangeAndProducto(DetalleGranos.class, "fechahora", fechaDesde, fechaHasta, selectedProducto);
        } else if (selectedEmpresa != null && getSelectedProducto() == null) {
            granosList = dao.getViewByDateRangeAndEmpresa(DetalleGranos.class, "fechahora", fechaDesde, fechaHasta, selectedEmpresa);
        } else if (selectedEmpresa != null && getSelectedProducto() != null) {
            granosList = dao.getViewByDateRangeAndEmpresaAndProducto(DetalleGranos.class, "fechahora", fechaDesde, fechaHasta, selectedEmpresa, selectedProducto);
        }
        getTotalBruto();
        getTotalTara();
        getTotalNeto();
        return granosList;
    }

    /**
     * @param granosList the pesadasList to set
     */
    public void setGranosList(List<DetalleGranos> granosList) {
        this.granosList = granosList;
    }

    /**
     * @return the fechaDesde
     */
    public Date getFechaDesde() {
        return fechaDesde;
    }

    /**
     * @param fechaDesde the fechaDesde to set
     */
    public void setFechaDesde(Date fechaDesde) {
        this.fechaDesde = fechaDesde;
    }

    /**
     * @return the fechaHasta
     */
    public Date getFechaHasta() {
        return fechaHasta;
    }

    /**
     * @param fechaHasta the fechaHasta to set
     */
    public void setFechaHasta(Date fechaHasta) {
        this.fechaHasta = fechaHasta;
    }

    /**
     * @return the selectedEmpresa
     */
    public Tblempresa getSelectedEmpresa() {
        return selectedEmpresa;
    }

    /**
     * @param selectedEmpresa the selectedEmpresa to set
     */
    public void setSelectedEmpresa(Tblempresa selectedEmpresa) {
        this.selectedEmpresa = selectedEmpresa;
    }

    /**
     * @return the selectedProducto
     */
    public Tblproductos getSelectedProducto() {
        return selectedProducto;
    }

    /**
     * @param selectedProducto the selectedProducto to set
     */
    public void setSelectedProducto(Tblproductos selectedProducto) {
        this.selectedProducto = selectedProducto;
    }

    /**
     * @return the empresaList
     */
    public List<Tblempresa> getEmpresaList() {
        empresaList = dao.getAll(Tblempresa.class);
        return empresaList;
    }

    /**
     * @param empresaList the empresaList to set
     */
    public void setEmpresaList(List<Tblempresa> empresaList) {
        this.empresaList = empresaList;
    }

    /**
     * @return the totalBruto
     */
    public long getTotalBruto() {
        totalBruto = 0;
        /*for (Tblpesadas d : pesadasList) {
         totalBruto += d.getBruto();
         }*/
        totalBruto = granosList.stream().mapToLong(x -> x.getBruto()).sum();
        return totalBruto;
    }

    /**
     * @param totalBruto the totalBruto to set
     */
    public void setTotalBruto(long totalBruto) {
        this.totalBruto = totalBruto;
    }

    /**
     * @return the totalTara
     */
    public long getTotalTara() {
        totalTara = 0;
        for (DetalleGranos d : granosList) {
            totalTara += d.getTara();
        }
        return totalTara;
    }

    /**
     * @param totalTara the totalTara to set
     */
    public void setTotalTara(long totalTara) {
        this.totalTara = totalTara;
    }

    /**
     * @return the totalNeto
     */
    public long getTotalNeto() {
        totalNeto = 0;
        for (DetalleGranos d : granosList) {
            totalNeto += d.getNeto();
        }
        return totalNeto;
    }

    /**
     * @param totalNeto the totalNeto to set
     */
    public void setTotalNeto(long totalNeto) {
        this.totalNeto = totalNeto;
    }

    /**
     * @return the selectedRango
     */
    public int getSelectedRango() {
        return selectedRango;
    }

    /**
     * @param selectedRango the selectedRango to set
     */
    public void setSelectedRango(int selectedRango) {
        this.selectedRango = selectedRango;
        if (selectedRango != 0) {
            setFechaHasta(new Date());
            switch (selectedRango) {
                case 1:
                    setFechaDesde(DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH));
                    break;
                case 2:
                    fechaDesde = DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
                    setFechaDesde(DateUtils.addDays(fechaDesde, -1));
                    fechaHasta = DateUtils.addDays(fechaDesde, 1);
                    setFechaHasta(DateUtils.addMilliseconds(fechaHasta, -1));
                    break;
                case 3:
                    calendar = DateUtils.truncate(calendar, Calendar.DAY_OF_MONTH);
                    calendar.set(Calendar.DAY_OF_WEEK, calendar.getFirstDayOfWeek());
                    setFechaDesde(calendar.getTime());
                    break;
                case 4:
                    setFechaDesde(DateUtils.truncate(new Date(), Calendar.MONTH));
                    break;
                case 5:
                    setFechaDesde(DateUtils.truncate(new Date(), Calendar.YEAR));
                    break;
            }
        }
    }

    /**
     * @return the granosFilteredList
     */
    public List<DetalleGranos> getGranosFilteredList() {
        return granosFilteredList;
    }

    /**
     * @param granosFilteredList the pesadasFilteredList to set
     */
    public void setGranosFilteredList(List<DetalleGranos> granosFilteredList) {
        this.granosFilteredList = granosFilteredList;
    }

    /**
     * @return the productosGranosList
     */
    public List<Tblproductos> getProductosGranosList() {
        productosGranosList = dao.getAllGranosProductos();
        return productosGranosList;
    }

    /**
     * @param productosGranosList the productosGranosList to set
     */
    public void setProductosGranosList(List<Tblproductos> productosGranosList) {
        this.productosGranosList = productosGranosList;
    }

}
