/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.bas;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
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
@Table(name = "tbl_dal_fardos_fibra")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalFardosFibra.findAll", query = "SELECT t FROM TblDalFardosFibra t"),
    @NamedQuery(name = "TblDalFardosFibra.findById", query = "SELECT t FROM TblDalFardosFibra t WHERE t.tblDalFardosFibraPK.id = :id"),
    @NamedQuery(name = "TblDalFardosFibra.findByIdLote", query = "SELECT t FROM TblDalFardosFibra t WHERE t.tblDalFardosFibraPK.idLote = :idLote"),
    @NamedQuery(name = "TblDalFardosFibra.findByFechahora", query = "SELECT t FROM TblDalFardosFibra t WHERE t.tblDalFardosFibraPK.fechahora = :fechahora"),
    @NamedQuery(name = "TblDalFardosFibra.findByPeso", query = "SELECT t FROM TblDalFardosFibra t WHERE t.peso = :peso"),
    @NamedQuery(name = "TblDalFardosFibra.findByTipo", query = "SELECT t FROM TblDalFardosFibra t WHERE t.tipo = :tipo"),
    @NamedQuery(name = "TblDalFardosFibra.findByMicronaire", query = "SELECT t FROM TblDalFardosFibra t WHERE t.micronaire = :micronaire"),
    @NamedQuery(name = "TblDalFardosFibra.findByFermentado", query = "SELECT t FROM TblDalFardosFibra t WHERE t.fermentado = :fermentado"),
    @NamedQuery(name = "TblDalFardosFibra.findByFardoVerdadero", query = "SELECT t FROM TblDalFardosFibra t WHERE t.fardoVerdadero = :fardoVerdadero")})
public class TblDalFardosFibra implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TblDalFardosFibraPK tblDalFardosFibraPK;
    @Basic(optional = false)
    @Column(name = "peso")
    private int peso;
    @Basic(optional = false)
    @Column(name = "tipo")
    private int tipo;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "micronaire")
    private BigDecimal micronaire;
    @Basic(optional = false)
    @Column(name = "fermentado")
    private short fermentado;
    @Basic(optional = false)
    @Column(name = "fardo_verdadero")
    private short fardoVerdadero;

    public TblDalFardosFibra() {
    }

    public TblDalFardosFibra(TblDalFardosFibraPK tblDalFardosFibraPK) {
        this.tblDalFardosFibraPK = tblDalFardosFibraPK;
    }

    public TblDalFardosFibra(TblDalFardosFibraPK tblDalFardosFibraPK, int peso, int tipo, BigDecimal micronaire, short fermentado, short fardoVerdadero) {
        this.tblDalFardosFibraPK = tblDalFardosFibraPK;
        this.peso = peso;
        this.tipo = tipo;
        this.micronaire = micronaire;
        this.fermentado = fermentado;
        this.fardoVerdadero = fardoVerdadero;
    }

    public TblDalFardosFibra(int id, int idLote, Date fechahora) {
        this.tblDalFardosFibraPK = new TblDalFardosFibraPK(id, idLote, fechahora);
    }

    public TblDalFardosFibraPK getTblDalFardosFibraPK() {
        return tblDalFardosFibraPK;
    }

    public void setTblDalFardosFibraPK(TblDalFardosFibraPK tblDalFardosFibraPK) {
        this.tblDalFardosFibraPK = tblDalFardosFibraPK;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getMicronaire() {
        return micronaire;
    }

    public void setMicronaire(BigDecimal micronaire) {
        this.micronaire = micronaire;
    }

    public short getFermentado() {
        return fermentado;
    }

    public void setFermentado(short fermentado) {
        this.fermentado = fermentado;
    }

    public short getFardoVerdadero() {
        return fardoVerdadero;
    }

    public void setFardoVerdadero(short fardoVerdadero) {
        this.fardoVerdadero = fardoVerdadero;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tblDalFardosFibraPK != null ? tblDalFardosFibraPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalFardosFibra)) {
            return false;
        }
        TblDalFardosFibra other = (TblDalFardosFibra) object;
        if ((this.tblDalFardosFibraPK == null && other.tblDalFardosFibraPK != null) || (this.tblDalFardosFibraPK != null && !this.tblDalFardosFibraPK.equals(other.tblDalFardosFibraPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.rrhh.TblDalFardosFibra[ tblDalFardosFibraPK=" + tblDalFardosFibraPK + " ]";
    }
    
}
