/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.service.bas;

import com.chortitzer.industria.web.domain.bas.Tblempresa;
import com.chortitzer.industria.web.domain.bas.Tblproductos;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;

public interface Service_bas {

    <T> List<T> getAll(Class<T> klass);

    <T> T save(T t);

    <T> void delete(T t);

    <T> T findByPk(Class<T> type, Object o);
    
    <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate);

    <T> List<T> getByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto);        
    
    <T> List<T> getByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa);
    
    <T> List<T> getByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto);
    
}
