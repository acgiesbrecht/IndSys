/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.admin;

/**
 *
 * @author Industria
 */
import com.chortitzer.industria.web.domain.admin.TiemposModel;
import com.chortitzer.industria.web.domain.fps.FpsAvgValueModel;
import java.util.Date;
import java.util.List;

public interface Dao_admin {

    <T> List<T> getAll(Class<T> klass);

    <T> T save(T t);

    <T> void delete(T t);

    <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate);
    
    List<TiemposModel> getTiempos();
}
