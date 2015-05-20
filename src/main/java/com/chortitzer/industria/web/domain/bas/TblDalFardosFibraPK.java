/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.bas;

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
public class TblDalFardosFibraPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @Column(name = "id_lote")
    private int idLote;
    @Basic(optional = false)
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;

    public TblDalFardosFibraPK() {
    }

    public TblDalFardosFibraPK(int id, int idLote, Date fechahora) {
        this.id = id;
        this.idLote = idLote;
        this.fechahora = fechahora;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdLote() {
        return idLote;
    }

    public void setIdLote(int idLote) {
        this.idLote = idLote;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        hash += (int) idLote;
        hash += (fechahora != null ? fechahora.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalFardosFibraPK)) {
            return false;
        }
        TblDalFardosFibraPK other = (TblDalFardosFibraPK) object;
        if (this.id != other.id) {
            return false;
        }
        if (this.idLote != other.idLote) {
            return false;
        }
        if ((this.fechahora == null && other.fechahora != null) || (this.fechahora != null && !this.fechahora.equals(other.fechahora))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.rrhh.TblDalFardosFibraPK[ id=" + id + ", idLote=" + idLote + ", fechahora=" + fechahora + " ]";
    }
    
}
