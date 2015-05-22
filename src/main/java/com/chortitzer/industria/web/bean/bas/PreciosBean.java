/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.bas;

import com.chortitzer.industria.web.dao.bas.Dao_bas;
import com.chortitzer.industria.web.domain.bas.Precios;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named
@Scope("view")
public class PreciosBean implements Serializable {

    private static final long serialVersionUID = 1L;
    @Inject
    Dao_bas dao;

    private List<Precios> preciosList;
    private List<Precios> preciosFilteredList;

    @PostConstruct
    private void init() {
        getPreciosList();
    }

    /**
     * @return the preciosList
     */
    public List<Precios> getPreciosList() {
        preciosList = dao.getAll(Precios.class);
        return preciosList;
    }

    /**
     * @param preciosList the preciosList to set
     */
    public void setPreciosList(List<Precios> preciosList) {
        this.preciosList = preciosList;
    }

    /**
     * @return the preciosFilteredList
     */
    public List<Precios> getPreciosFilteredList() {
        return preciosFilteredList;
    }

    /**
     * @param preciosFilteredList the preciosFilteredList to set
     */
    public void setPreciosFilteredList(List<Precios> preciosFilteredList) {
        this.preciosFilteredList = preciosFilteredList;
    }

}
