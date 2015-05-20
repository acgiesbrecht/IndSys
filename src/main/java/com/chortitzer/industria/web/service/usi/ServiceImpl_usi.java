/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.service.usi;

import com.chortitzer.industria.web.dao.usi.Dao_usi;
import com.chortitzer.industria.web.domain.usi.ConsumoNisModel;
import com.chortitzer.industria.web.domain.usi.NisSinLecturaModel;
import com.chortitzer.industria.web.domain.usi.NisUsuario;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.Transactional;

@Named
@Scope("session")
public class ServiceImpl_usi implements Service_usi {

    @Inject
    private Dao_usi dao;

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
    public List<ConsumoNisModel> getConsumoNis(String mes, String ano){
        return dao.getConsumoNis(mes, ano);
    }
    
    @Transactional(readOnly = true)
    public List<NisSinLecturaModel> getNisSinLectura(String mes, String ano){
        return dao.getNisSinLectura(mes, ano);
    }
    
    @Transactional(readOnly = true)
    public <T> T findByPk(Class<T> type, Object o){
        return dao.findByPk(type, o);
    }
    
    @Transactional(readOnly = true)
    public long countByNrSerie(int nrserie){
        return dao.countByNrSerie(nrserie);
    }

    @Transactional(readOnly = true)
    public NisUsuario findByNis(String nis){
        return dao.findByNis(nis);
    }
    
    /*@Transactional(readOnly = true)
    public List<ConsumoNisModel> getByDate(String mes, String ano){
        return dao.getByDate(mes, ano);
    }*/
    
}
