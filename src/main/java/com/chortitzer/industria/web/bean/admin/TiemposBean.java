/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.admin;

import com.chortitzer.industria.web.dao.admin.Dao_admin;
import com.chortitzer.industria.web.domain.admin.TiemposModel;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Industria
 */
@Named
@Scope("view")
public class TiemposBean implements Serializable {
private static final long serialVersionUID = 1L;
    @Inject
    Dao_admin service;

    private List<TiemposModel> tiemposList;        
    private List<TiemposModel> tiemposFilteredList;        
    private Date fechaDesde;
    private Date fechaHasta;
    private int selectedRango = 1;
    

    public TiemposBean() {
        
    }

    @PostConstruct
    private void init() {
          setTiemposList(service.getTiempos());        
    }

    /**
     * @return the tiemposList
     */
    public List<TiemposModel> getTiemposList() {
        return tiemposList;
    }

    /**
     * @param tiemposList the tiemposList to set
     */
    public void setTiemposList(List<TiemposModel> tiemposList) {
        this.tiemposList = tiemposList;
    }

    /**
     * @return the tiemposFilteredList
     */
    public List<TiemposModel> getTiemposFilteredList() {
        return tiemposFilteredList;
    }

    /**
     * @param tiemposFilteredList the tiemposFilteredList to set
     */
    public void setTiemposFilteredList(List<TiemposModel> tiemposFilteredList) {
        this.tiemposFilteredList = tiemposFilteredList;
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
    }
    
    public Date convertInterval(int interval){
        return new Date(interval*1000);
    }

    
    
}
