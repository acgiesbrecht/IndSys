/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.chortitzer.industria.web.domain.lab;

import java.io.Serializable;
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
@Table(name = "lab_tipo_muestra_sublineas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabTipoMuestraSublineas.findAll", query = "SELECT l FROM LabTipoMuestraSublineas l"),
    @NamedQuery(name = "LabTipoMuestraSublineas.findById", query = "SELECT l FROM LabTipoMuestraSublineas l WHERE l.labTipoMuestraSublineasPK.id = :id"),
    @NamedQuery(name = "LabTipoMuestraSublineas.findByDescripcion", query = "SELECT l FROM LabTipoMuestraSublineas l WHERE l.labTipoMuestraSublineasPK.descripcion = :descripcion")})
public class LabTipoMuestraSublineas implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected LabTipoMuestraSublineasPK labTipoMuestraSublineasPK;

    public LabTipoMuestraSublineas() {
    }

    public LabTipoMuestraSublineas(LabTipoMuestraSublineasPK labTipoMuestraSublineasPK) {
        this.labTipoMuestraSublineasPK = labTipoMuestraSublineasPK;
    }

    public LabTipoMuestraSublineas(int id, String descripcion) {
        this.labTipoMuestraSublineasPK = new LabTipoMuestraSublineasPK(id, descripcion);
    }

    public LabTipoMuestraSublineasPK getLabTipoMuestraSublineasPK() {
        return labTipoMuestraSublineasPK;
    }

    public void setLabTipoMuestraSublineasPK(LabTipoMuestraSublineasPK labTipoMuestraSublineasPK) {
        this.labTipoMuestraSublineasPK = labTipoMuestraSublineasPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (labTipoMuestraSublineasPK != null ? labTipoMuestraSublineasPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LabTipoMuestraSublineas)) {
            return false;
        }
        LabTipoMuestraSublineas other = (LabTipoMuestraSublineas) object;
        if ((this.labTipoMuestraSublineasPK == null && other.labTipoMuestraSublineasPK != null) || (this.labTipoMuestraSublineasPK != null && !this.labTipoMuestraSublineasPK.equals(other.labTipoMuestraSublineasPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.chortitzer.industria.web.domain.lab.LabTipoMuestraSublineas[ labTipoMuestraSublineasPK=" + labTipoMuestraSublineasPK + " ]";
    }
    
}
