/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.service;

import com.chortitzer.industria.web.dao.admin.Dao_admin;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Named
@Scope("session")
public class ServiceImpl implements Service {

    @Inject
    private Dao_admin dao;

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
        
    @Transactional(readOnly = true)
    public <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate) {
        return dao.getByDateRange(klass, DateColumn, startDate, endDate);
    }
    
}
