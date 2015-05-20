/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.service.fps;

import com.chortitzer.industria.web.dao.fps.Dao_fps;
import com.chortitzer.industria.web.domain.fps.FpsAvgValueModel;
import com.chortitzer.industria.web.domain.fps.FpsHorarioReactorModel;
import com.chortitzer.industria.web.domain.fps.TblFpsLotes;
import com.chortitzer.industria.web.domain.fps.TblFpsTambores;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Named
@Scope("session")
public class ServiceImpl_fps implements Service_fps {

    @Inject
    private Dao_fps dao;

    //@Transactional(value="tm", readOnly = true)
    @Override
    @Transactional(readOnly = true)
    public <T> List<T> getAll(Class<T> klass) {
        return dao.getAll(klass);
    }

    //@Transactional(value="tm")
    @Override
    @Transactional
    public <T> T save(T t) {
        T newRecord = null;
        newRecord = dao.save(t);
        return newRecord;
    }

    //@Transactional(value="tm")
    @Override
    @Transactional
    public <T> void delete(T t) {
        dao.delete(t);
    }

    @Override
    @Transactional(readOnly = true)
    public <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate) {
        return dao.getByDateRange(klass, DateColumn, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<FpsAvgValueModel> getAvgFpsParamByDateRange(int Param, Date startDate, Date endDate) {
        return dao.getAvgFpsParamByDateRange(Param, startDate, endDate);
    }

    @Transactional(readOnly = true)
    public List<FpsAvgValueModel> getAvgFpsParamByDate(int Param, Date fecha) {
        return dao.getAvgFpsParamByDate(Param, fecha);
    }

    public List<TblFpsTambores> getByLote(TblFpsLotes lote) {
        return dao.getByLote(lote);
    }

    @Override
    public FpsHorarioReactorModel getHorario(Date fecha, int reactor) {
        return dao.getHorario(fecha, reactor);
    }
}
