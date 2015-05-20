/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.service.fps;

import com.chortitzer.industria.web.domain.fps.FpsAvgValueModel;
import com.chortitzer.industria.web.domain.fps.FpsHorarioReactorModel;
import com.chortitzer.industria.web.domain.fps.TblFpsLotes;
import com.chortitzer.industria.web.domain.fps.TblFpsTambores;
import java.util.Date;
import java.util.List;

public interface Service_fps {

    <T> List<T> getAll(Class<T> klass);

    <T> T save(T t);

    <T> void delete(T t);

    <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate);

    List<TblFpsTambores> getByLote(TblFpsLotes lote);

    List<FpsAvgValueModel> getAvgFpsParamByDateRange(int Param, Date startDate, Date endDate);

    FpsHorarioReactorModel getHorario(Date fecha, int reactor);

}
