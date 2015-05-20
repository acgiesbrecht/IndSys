/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.admin;

import com.chortitzer.industria.web.types.Interval;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


/**
 *
 * @author Industria
 */
@Entity
@TypeDef(name="interval", typeClass = Interval.class)
public class TiemposModel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private TiemposModelPK tiemposModelPK;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hora_salida")
    private Date horaSalida;
    @Column(name = "tiempo")
    @Type(type = "interval")   
    private Integer tiempo;

    /**
     * @return the horaSalida
     */
    public Date getHoraSalida() {
        return horaSalida;
    }

    /**
     * @param horaSalida the horaSalida to set
     */
    public void setHoraSalida(Date horaSalida) {
        this.horaSalida = horaSalida;
    }

    /**
     * @return the tiempo
     */
    public Integer getTiempo() {
        return tiempo;
    }

    /**
     * @param tiempo the tiempo to set
     */
    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    /**
     * @return the tiemposModelPK
     */
    public TiemposModelPK getTiemposModelPK() {
        return tiemposModelPK;
    }

    /**
     * @param tiemposModelPK the tiemposModelPK to set
     */
    public void setTiemposModelPK(TiemposModelPK tiemposModelPK) {
        this.tiemposModelPK = tiemposModelPK;
    }
}
