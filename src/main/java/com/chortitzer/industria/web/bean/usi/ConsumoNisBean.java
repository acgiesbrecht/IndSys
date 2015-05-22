/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.usi;

import com.chortitzer.industria.web.bean.common.CommonBean;
import com.chortitzer.industria.web.dao.usi.Dao_usi;
import com.chortitzer.industria.web.domain.usi.ConsumoNisModel;
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
public class ConsumoNisBean {

    @Inject
    Dao_usi dao;

    @Inject
    CommonBean commonBean;

    List<ConsumoNisModel> consumoNisList;
    private List<ConsumoNisModel> consumoNisFilteredList;
    private List<String> mesList = new ArrayList<>();
    private List<String> anoList = new ArrayList<>();

    Calendar calendar = Calendar.getInstance();

    private String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
    private String ano = String.valueOf(calendar.get(Calendar.YEAR));
    private String mesLetras;

    public ConsumoNisBean() {
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
        getConsumoNisList();
    }

    public List<ConsumoNisModel> getConsumoNisList() {
        List<ConsumoNisModel> list = dao.getConsumoNis(mes, ano);
        return list;
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
        //getConsumoNisList();
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
        //getConsumoNisList();
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
        return s.substring(0, 1) + "-" + s.substring(1, 4) + "-" + s.substring(4, 7);
    }

    /**
     * @return the consumoNisFilteredList
     */
    public List<ConsumoNisModel> getConsumoNisFilteredList() {
        return consumoNisFilteredList;
    }

    /**
     * @param consumoNisFilteredList the consumoNisFilteredList to set
     */
    public void setConsumoNisFilteredList(List<ConsumoNisModel> consumoNisFilteredList) {
        this.consumoNisFilteredList = consumoNisFilteredList;
    }

    /**
     * @return the mesLetras
     */
    public String getMesLetras() {
        return commonBean.mesInttoString(mes);
    }

    /**
     * @param mesLetras the mesLetras to set
     */
    public void setMesLetras(String mesLetras) {
        this.mesLetras = mesLetras;
    }

}
