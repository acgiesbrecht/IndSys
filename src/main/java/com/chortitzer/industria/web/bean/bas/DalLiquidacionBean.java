/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.bas;

import com.chortitzer.industria.web.dao.bas.Dao_bas;
import com.chortitzer.industria.web.domain.bas.DalLiquidacionModel;
import com.chortitzer.industria.web.domain.bas.TblDalLotes;
import com.chortitzer.industria.web.domain.bas.Tblempresa;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named
@Scope("view")
public class DalLiquidacionBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    Dao_bas service;

    private List<DalLiquidacionModel> liquidacionList;

    private List<Tblempresa> empresaList;

    private Tblempresa selectedEmpresa;
    private String selectedLote = "";
    private int selectedAno;
    private List<TblDalLotes> lotesList;
    private List<Integer> anosList;
    private String hasLotes;

    @PostConstruct
    private void init() {
        getAnosList();
        setSelectedAno(anosList.get(0));
        getEmpresaList();
    }

    /**
     * @return the empresaList
     */
    public List<Tblempresa> getEmpresaList() {
        empresaList = service.getAll(Tblempresa.class);
        return empresaList;
    }

    /**
     * @param empresaList the empresaList to set
     */
    public void setEmpresaList(List<Tblempresa> empresaList) {
        this.empresaList = empresaList;
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
     * @return the selectedLote
     */
    public String getSelectedLote() {
        return selectedLote;
    }

    /**
     * @param selectedLote the selectedLote to set
     */
    public void setSelectedLote(String selectedLote) {
        this.selectedLote = selectedLote;
    }

    /**
     * @return the lotesList
     */
    public List<TblDalLotes> getLotesList() {
        if (selectedEmpresa != null) {
            lotesList = service.getLotesByEmpresaAndAno(selectedEmpresa.getId(), getSelectedAno());
            if (lotesList.size() > 0) {
                setSelectedLote(lotesList.get(0).toString());
            } else {
                setSelectedLote("");
            }

        }

        return lotesList;
    }

    /**
     * @param lotesList the lotesList to set
     */
    public void setLotesList(List<TblDalLotes> lotesList) {
        this.lotesList = lotesList;
    }

    /**
     * @return the anosList
     */
    public List<Integer> getAnosList() {
        anosList = service.getDalAnosAll();
        return anosList;
    }

    /**
     * @param anosList the anosList to set
     */
    public void setAnosList(List<Integer> anosList) {
        this.anosList = anosList;
    }

    /**
     * @return the selectedAno
     */
    public int getSelectedAno() {
        return selectedAno;
    }

    /**
     * @param selectedAno the selectedAno to set
     */
    public void setSelectedAno(int selectedAno) {
        this.selectedAno = selectedAno;
    }

    /**
     * @return the liquidacionList
     */
    public List<DalLiquidacionModel> getLiquidacionList() {
        liquidacionList = null;
        if (selectedEmpresa == null) {
            liquidacionList = service.getLiquidacionByAno(selectedAno);
        } else if (!selectedLote.equals("") && !selectedLote.equals("Null")) {
            liquidacionList = service.getLiquidacionByLote(selectedLote);
        }
        return liquidacionList;
    }

    /**
     * @param liquidacionList the liquidacionList to set
     */
    public void setLiquidacionList(List<DalLiquidacionModel> liquidacionList) {
        this.liquidacionList = liquidacionList;
    }

}
