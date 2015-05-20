/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.service.bas;

import com.chortitzer.industria.web.dao.bas.Dao_bas;
import com.chortitzer.industria.web.domain.bas.Tblempresa;
import com.chortitzer.industria.web.domain.bas.Tblproductos;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Named
@Scope("session")
public class ServiceImpl_bas implements Service_bas {

    @Inject
    private Dao_bas dao;

    //@Transactional(value="tm", readOnly = true)
    @Transactional(readOnly = true)
    public <T> List<T> getAll(Class<T> klass) {
        return dao.getAll(klass);
    }

    //@Transactional(value="tm")
    @Transactional
    public <T> T save(T t) {
        T newRecord = null;
        newRecord = dao.save(t);
        return newRecord;
    }

    //@Transactional(value="tm")
    @Transactional
    public <T> void delete(T t) {
        dao.delete(t);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate) {
        return dao.getByDateRange(klass, DateColumn, startDate, endDate);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> getByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto) {
        return dao.getByDateRangeAndProducto(klass, DateColumn, startDate, endDate, producto);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> getByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa) {
        return dao.getByDateRangeAndEmpresa(klass, DateColumn, startDate, endDate, empresa);
    }
    
    public <T> List<T> getByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto){
        return dao.getByDateRangeAndEmpresaAndProducto(klass, DateColumn, startDate, endDate, empresa, producto);
    }
    
    @Transactional(readOnly = true)
    public <T> T findByPk(Class<T> type, Object o){
        return dao.findByPk(type, o);
    }
}
