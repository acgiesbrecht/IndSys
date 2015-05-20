/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.fps;

import com.chortitzer.industria.web.domain.fps.TblFpsLotes;
import com.chortitzer.industria.web.domain.fps.TblFpsTambores;
import com.chortitzer.industria.web.service.fps.Service_fps;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Industria
 */
@Named
@Scope("view")
public class TamboresBean  implements Serializable{
    private static final long serialVersionUID = 1L;
    @Inject
    Service_fps service;

    private List<TblFpsLotes> tblFpsLotesList;        
    private TblFpsLotes selectedLote;
    
    private List<TblFpsTambores> tblFpsTamboresList;

    public TamboresBean() {
        
    }

    @PostConstruct
    private void init() {
          tblFpsLotesList = service.getAll(TblFpsLotes.class);        
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
        
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
            //dt = (TblFpsLotesList)event.getSource();
            service.save(tblFpsLotesList.get(event.getRowIndex()));
            
            if(tblFpsLotesList.get(event.getRowIndex()).getId()==null){
                tblFpsLotesList = service.getAll(TblFpsLotes.class);    
            }
            System.out.println(tblFpsLotesList.get(event.getRowIndex()).getId());
        }
    }
    
    public void onLoteSelect(SelectEvent event){
        tblFpsTamboresList = service.getByLote(selectedLote); 
    }
    
    /**
     * @return the tblFpsLotesList
     */
    public List<TblFpsLotes> getTblFpsLotesList() {
        return tblFpsLotesList;
    }

    /**
     * @param tblFpsLotesList the tblFpsLotesList to set
     */
    public void setTblFpsLotesList(List<TblFpsLotes> tblFpsLotesList) {
        this.tblFpsLotesList = tblFpsLotesList;
    }
    
    public double pesoNeto(TblFpsTambores tambor){
        if(tambor.getPesoBruto() >= 0 && tambor.getPesoTara() >= 0){
            return tambor.getPesoBruto() - tambor.getPesoTara();
        }else{
            return 0;
        }
        
    }
    
    public void addTambor(){
        TblFpsTambores newTambor = new TblFpsTambores();        
        if(tblFpsTamboresList.size()>0){
            newTambor.setIdFabrica(tblFpsTamboresList.get(tblFpsTamboresList.size()-1).getIdFabrica()+1);
        }else{
            newTambor.setIdFabrica(1);
        }
        
        newTambor.setIdLote(selectedLote);        
        newTambor.setPesoBruto(0);
        newTambor.setPesoTara(0);
        service.save(newTambor);        
        tblFpsTamboresList = service.getAll(TblFpsTambores.class); 
        //tblFpsTamboresList.add(newTambor);
    }
    
    public void deleteTambor(TblFpsTambores tambor){
        service.delete(tambor);        
        tblFpsTamboresList = service.getAll(TblFpsTambores.class); 
    }

    /**
     * @return the selectedLote
     */
    public TblFpsLotes getSelectedLote() {
        return selectedLote;
    }

    /**
     * @param selectedLote the selectedLote to set
     */
    public void setSelectedLote(TblFpsLotes selectedLote) {
        this.selectedLote = selectedLote;
    }

    /**
     * @return the tblFpsTamboresList
     */
    public List<TblFpsTambores> getTblFpsTamboresList() {
        return tblFpsTamboresList;
    }

    /**
     * @param tblFpsTamboresList the tblFpsTamboresList to set
     */
    public void setTblFpsTamboresList(List<TblFpsTambores> tblFpsTamboresList) {
        this.tblFpsTamboresList = tblFpsTamboresList;
    }
    
}
