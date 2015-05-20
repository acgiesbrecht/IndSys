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
public class RrhhTiemposPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Basic(optional = false)
    @Column(name = "nro_empleado")
    private long nroEmpleado;

    public RrhhTiemposPK() {
    }

    public RrhhTiemposPK(Date fechahora, long nroEmpleado) {
        this.fechahora = fechahora;
        this.nroEmpleado = nroEmpleado;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
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
        hash += (fechahora != null ? fechahora.hashCode() : 0);
        hash += (int) nroEmpleado;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhTiemposPK)) {
            return false;
        }
        RrhhTiemposPK other = (RrhhTiemposPK) object;
        if ((this.fechahora == null && other.fechahora != null) || (this.fechahora != null && !this.fechahora.equals(other.fechahora))) {
            return false;
        }
        if (this.nroEmpleado != other.nroEmpleado) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.rrhh.RrhhTiemposPK[ fechahora=" + fechahora + ", nroEmpleado=" + nroEmpleado + " ]";
    }
    
}
