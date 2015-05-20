/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Industria
 */
@Embeddable
public class TiemposModelPK implements Serializable {

    @Basic(optional = false)    
    @Column(name = "hora_entrada")
    @Temporal(TemporalType.TIMESTAMP)
    private Date horaEntrada;
    @Column(name = "nro_empleado")
    @Basic(optional = false)
    private long nroEmpleado;

    public TiemposModelPK() {
    }

    public TiemposModelPK(Date horaEntrada, long nroEmpleado) {
        this.horaEntrada = horaEntrada;
        this.nroEmpleado = nroEmpleado;
    }

    public Date getHoraEntrada() {
        return horaEntrada;
    }

    public void setHoraEntrada(Date horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    public long getNroEmpleado() {
        return nroEmpleado;
    }

    public void setNroEmpleado(long nroEmpleado) {
        this.nroEmpleado = nroEmpleado;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (horaEntrada != null ? horaEntrada.hashCode() : 0);
        hash += (int) nroEmpleado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TiemposModelPK)) {
            return false;
        }
        TiemposModelPK other = (TiemposModelPK) object;
        if ((this.horaEntrada == null && other.horaEntrada != null) || (this.horaEntrada != null && !this.horaEntrada.equals(other.horaEntrada))) {
            return false;
        }
        if (this.nroEmpleado != other.nroEmpleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.rrhh.RrhhPK[ horaEntrada=" + horaEntrada + ", nroEmpleado=" + nroEmpleado + " ]";
    }
    
}
