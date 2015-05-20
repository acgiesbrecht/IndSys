/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.admin;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "rrhh_tiempos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RrhhTiempos.findAll", query = "SELECT r FROM RrhhTiempos r"),
    @NamedQuery(name = "RrhhTiempos.findByFechahora", query = "SELECT r FROM RrhhTiempos r WHERE r.rrhhTiemposPK.fechahora = :fechahora"),
    @NamedQuery(name = "RrhhTiempos.findByNroEmpleado", query = "SELECT r FROM RrhhTiempos r WHERE r.rrhhTiemposPK.nroEmpleado = :nroEmpleado"),
    @NamedQuery(name = "RrhhTiempos.findByEs", query = "SELECT r FROM RrhhTiempos r WHERE r.es = :es")})
public class RrhhTiempos implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected RrhhTiemposPK rrhhTiemposPK;
    @Column(name = "es")
    private Short es;

    public RrhhTiempos() {
    }

    public RrhhTiempos(RrhhTiemposPK rrhhTiemposPK) {
        this.rrhhTiemposPK = rrhhTiemposPK;
    }

    public RrhhTiempos(Date fechahora, long nroEmpleado) {
        this.rrhhTiemposPK = new RrhhTiemposPK(fechahora, nroEmpleado);
    }

    public RrhhTiemposPK getRrhhTiemposPK() {
        return rrhhTiemposPK;
    }

    public void setRrhhTiemposPK(RrhhTiemposPK rrhhTiemposPK) {
        this.rrhhTiemposPK = rrhhTiemposPK;
    }

    public Short getEs() {
        return es;
    }

    public void setEs(Short es) {
        this.es = es;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rrhhTiemposPK != null ? rrhhTiemposPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RrhhTiempos)) {
            return false;
        }
        RrhhTiempos other = (RrhhTiempos) object;
        if ((this.rrhhTiemposPK == null && other.rrhhTiemposPK != null) || (this.rrhhTiemposPK != null && !this.rrhhTiemposPK.equals(other.rrhhTiemposPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.rrhh.RrhhTiempos[ rrhhTiemposPK=" + rrhhTiemposPK + " ]";
    }
    
}
