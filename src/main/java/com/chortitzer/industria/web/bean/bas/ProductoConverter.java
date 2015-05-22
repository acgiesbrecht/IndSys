/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.bas;

import com.chortitzer.industria.web.dao.bas.Dao_bas;
import com.chortitzer.industria.web.domain.bas.Tblproductos;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;
import javax.inject.Named;
import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;

/**
 *
 * @author Industria
 */
@Named
@Scope("view")
@FacesConverter("com.chortitzer.industria.web.bean.bas.ProductoConverter")
public class ProductoConverter implements Converter {

    @Inject
    Dao_bas dao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component,
            String value) {
        if (value != null && value.trim().length() > 0 && StringUtils.isNumeric(value)) {
            return dao.findByPk(Tblproductos.class, Integer.parseInt(value));
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component,
            Object value) {
        if (value != null) {
            return String.valueOf(value);
        } else {
            return null;
        }
    }
}
