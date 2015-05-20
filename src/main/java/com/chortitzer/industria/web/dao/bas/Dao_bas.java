/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.bas;

/**
 *
 * @author Industria
 */
import com.chortitzer.industria.web.domain.bas.DalLiquidacionModel;
import com.chortitzer.industria.web.domain.bas.DalResumenFibraModel;
import com.chortitzer.industria.web.domain.bas.TblDalLotes;
import com.chortitzer.industria.web.domain.bas.Tblempresa;
import com.chortitzer.industria.web.domain.bas.Tblproductos;
import java.util.Date;
import java.util.List;

public interface Dao_bas {

    <T> List<T> getAll(Class<T> klass);

    <T> T save(T t);

    <T> void delete(T t);

    <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate);

    <T> List<T> getByDateRangeAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblproductos producto);

    <T> List<T> getByDateRangeAndEmpresa(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa);

    <T> List<T> getByDateRangeAndEmpresaAndProducto(Class<T> klass, String DateColumn, Date startDate, Date endDate, Tblempresa empresa, Tblproductos producto);

    <T> T findByPk(Class<T> type, Object o);

    List<DalResumenFibraModel> getResumenByLote(String lote);

    List<DalResumenFibraModel> getResumenByAno(int ano);

    List<DalLiquidacionModel> getLiquidacionByLote(String lote);

    List<DalLiquidacionModel> getLiquidacionByAno(int ano);

    List<Integer> getDalAnosAll();

    List<TblDalLotes> getLotesByEmpresaAndAno(int idEmpresa, int ano);
}
