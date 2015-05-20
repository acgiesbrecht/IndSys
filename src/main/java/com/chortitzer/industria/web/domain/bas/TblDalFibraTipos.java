/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.bas;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Industria
 */
@Entity
@Table(name = "tbl_dal_fibra_tipos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblDalFibraTipos.findAll", query = "SELECT t FROM TblDalFibraTipos t"),
    @NamedQuery(name = "TblDalFibraTipos.findById", query = "SELECT t FROM TblDalFibraTipos t WHERE t.id = :id"),
    @NamedQuery(name = "TblDalFibraTipos.findByDescripcion", query = "SELECT t FROM TblDalFibraTipos t WHERE t.descripcion = :descripcion"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2012", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2012 = :bonificacion2012"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2013", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2013 = :bonificacion2013"),
    @NamedQuery(name = "TblDalFibraTipos.findByBonificacion2014", query = "SELECT t FROM TblDalFibraTipos t WHERE t.bonificacion2014 = :bonificacion2014")})
public class TblDalFibraTipos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "bonificacion_2012")
    private Integer bonificacion2012;
    @Column(name = "bonificacion_2013")
    private Integer bonificacion2013;
    @Column(name = "bonificacion_2014")
    private Integer bonificacion2014;

    public TblDalFibraTipos() {
    }

    public TblDalFibraTipos(Integer id) {
        this.id = id;
    }

    public TblDalFibraTipos(Integer id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getBonificacion2012() {
        return bonificacion2012;
    }

    public void setBonificacion2012(Integer bonificacion2012) {
        this.bonificacion2012 = bonificacion2012;
    }

    public Integer getBonificacion2013() {
        return bonificacion2013;
    }

    public void setBonificacion2013(Integer bonificacion2013) {
        this.bonificacion2013 = bonificacion2013;
    }

    public Integer getBonificacion2014() {
        return bonificacion2014;
    }

    public void setBonificacion2014(Integer bonificacion2014) {
        this.bonificacion2014 = bonificacion2014;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblDalFibraTipos)) {
            return false;
        }
        TblDalFibraTipos other = (TblDalFibraTipos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.rrhh.TblDalFibraTipos[ id=" + id + " ]";
    }
    
}
