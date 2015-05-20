/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.usi;

import com.chortitzer.industria.web.domain.usi.NisSinLecturaModel;
import com.chortitzer.industria.web.domain.usi.NisUsuario;
import com.chortitzer.industria.web.service.usi.Service_usi;
import java.util.ArrayList;
import java.util.Calendar;
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
public class NisSinLecturaBean {

    @Inject
    Service_usi service;

    List<NisSinLecturaModel> nisSinLecturaList;
    private List<NisSinLecturaModel> nisSinLecturaFilteredList;
    private List<String> mesList = new ArrayList<>();
    private List<String> anoList = new ArrayList<>();
    private int listCount;

    Calendar calendar = Calendar.getInstance();

    private String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    private String ano = String.valueOf(calendar.get(Calendar.YEAR));

    public NisSinLecturaBean() {
        int mesActual = calendar.get(Calendar.MONTH) + 1;

        for (int i = 1; i > -11; i--) {
            mesList.add(String.valueOf(mesActual));
            mesActual--;
            if (mesActual == 0) {
                mesActual = 12;
            }
        }

        int anoActual = calendar.get(Calendar.YEAR);
        for (int i = anoActual; i > anoActual - 3; i--) {
            anoList.add(String.valueOf(i));
        }
    }

    @PostConstruct
    private void init() {
        getNisSinLecturaList();
    }

    public List<NisSinLecturaModel> getNisSinLecturaList() {
        nisSinLecturaList = service.getNisSinLectura(mes, ano);
        listCount = nisSinLecturaList.size();
        return nisSinLecturaList;
    }

    /**
     * @return the mes
     */
    public String getMes() {
        return mes;
    }

    /**
     * @param mes the mes to set
     */
    public void setMes(String mes) {
        this.mes = mes;
        //getNisSinLecturaList();
    }

    /**
     * @return the ano
     */
    public String getAno() {
        return ano;
    }

    /**
     * @param ano the ano to set
     */
    public void setAno(String ano) {
        this.ano = ano;
        //getNisSinLecturaList();
    }

    /**
     * @return the mesList
     */
    public List<String> getMesList() {
        return mesList;
    }

    /**
     * @param mesList the mesList to set
     */
    public void setMesList(List<String> mesList) {
        this.mesList = mesList;
    }

    /**
     * @return the anoList
     */
    public List<String> getAnoList() {
        return anoList;
    }

    /**
     * @param anoList the anoList to set
     */
    public void setAnoList(List<String> anoList) {
        this.anoList = anoList;
    }

    public String formatNis(String s) {
        try{
            return s.substring(0, 1) + "-" + s.substring(1, 4) + "-" + s.substring(4, 7);
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            return "ERROR";
        }
        
    }

    /**
     * @return the listCount
     */
    public int getListCount() {
        return listCount;
    }

    /**
     * @param listCount the listCount to set
     */
    public void setListCount(int listCount) {
        this.listCount = listCount;
    }

    /**
     * @return the nisSinLecturaFilteredList
     */
    public List<NisSinLecturaModel> getNisSinLecturaFilteredList() {
        return nisSinLecturaFilteredList;
    }

    /**
     * @param nisSinLecturaFilteredList the nisSinLecturaFilteredList to set
     */
    public void setNisSinLecturaFilteredList(List<NisSinLecturaModel> nisSinLecturaFilteredList) {
        this.nisSinLecturaFilteredList = nisSinLecturaFilteredList;
    }

}
