/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.domain.bas;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Industria
 */
@Entity
public class DalLiquidacionModel implements Serializable{
    @Id
    private int id;
    private Date fechahora;
    private int peso;
    private int pesoanticipo;
    private long pago;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the fechahora
     */
    public Date getFechahora() {
        return fechahora;
    }

    /**
     * @param fechahora the fechahora to set
     */
    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    /**
     * @return the peso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the peso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }

    /**
     * @return the pesoanticipo
     */
    public int getPesoanticipo() {
        return pesoanticipo;
    }

    /**
     * @param pesoanticipo the pesoanticipo to set
     */
    public void setPesoanticipo(int pesoanticipo) {
        this.pesoanticipo = pesoanticipo;
    }

    /**
     * @return the pago
     */
    public long getPago() {
        return pago;
    }

    /**
     * @param pago the pago to set
     */
    public void setPago(long pago) {
        this.pago = pago;
    }
    
}
