/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.dao.admin;

import com.chortitzer.industria.web.domain.admin.TiemposModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import org.springframework.context.annotation.Scope;

@Named
@Scope("session")
public class DaoImpl_admin implements Dao_admin {

    //@PersistenceContext(type = PersistenceContextType.EXTENDED)
    @PersistenceContext(unitName = "PU_admin", type = PersistenceContextType.EXTENDED)
    private EntityManager em;

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }

    @SuppressWarnings("unchecked")
    public <T> List<T> getAll(Class<T> klass) {
        return em.createQuery("Select t from " + klass.getSimpleName() + " t")
                .getResultList();
    }

    public <T> T save(T t) {
        T newRecord = null;
        newRecord = em.merge(t);
        return newRecord;
    }

    public <T> void delete(T t) {
        em.remove(em.merge(t));
        em.flush();
    }

    public <T> List<T> getByDateRange(Class<T> klass, String DateColumn, Date startDate, Date endDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String sQuery = "Select t from " + klass.getSimpleName() + " t where t." + DateColumn + " between '" + sdf.format(startDate) + "' and '" + sdf.format(endDate) + "'";
        System.out.print(sQuery);
        return em.createQuery(sQuery).getResultList();
    }
    @Override
    public List<TiemposModel> getTiempos(){
        String sQuery = " with cte as" +
                        " (" +
                        "	select	*, row_number() over (partition by nro_empleado order by fechahora) as rn" +
                        "	from	rrhh_tiempos" +
                        " )" +
                        " select	i.nro_empleado, i.fechahora as hora_entrada, o.fechahora as hora_salida, o.fechahora - i.fechahora as tiempo" +
                        " from	cte i" +
                        "	inner join cte o	on	i.nro_empleado	= o.nro_empleado" +
                        "				and	i.rn 	= o.rn - 1" +
                        " where	i.es	= 0" +
                        " and	o.es = 1" +
                        " order by i.nro_empleado, i.rn";
        return (List<TiemposModel>)em.createNativeQuery(sQuery,TiemposModel.class).getResultList();
    }
    


}
