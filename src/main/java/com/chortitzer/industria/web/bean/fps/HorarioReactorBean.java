/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.chortitzer.industria.web.bean.fps;

import com.chortitzer.industria.web.dao.fps.Dao_fps;
import com.chortitzer.industria.web.domain.fps.FpsHorarioReactorModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import org.springframework.context.annotation.Scope;

@Named
@Scope("view")
public class HorarioReactorBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    Dao_fps dao;

    private Date fecha;
    private FpsHorarioReactorModel r1;
    private FpsHorarioReactorModel r2;
    private FpsHorarioReactorModel r3;
    private FpsHorarioReactorModel r4;
    private FpsHorarioReactorModel r5;
    private FpsHorarioReactorModel r6;
    private FpsHorarioReactorModel r7;
    private List<FpsHorarioReactorModel> reactoresList;

    Calendar calendar = Calendar.getInstance();

    @PostConstruct
    private void init() {
        setFecha(new Date());
    }

    /**
     * @return the fecha
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * @param fecha the fecha to set
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
        getReactoresList();
    }

    /**
     * @return the r1
     */
    public FpsHorarioReactorModel getR1() {
        return r1;
    }

    /**
     * @param r1 the r1 to set
     */
    public void setR1(FpsHorarioReactorModel r1) {
        this.r1 = r1;
    }

    /**
     * @return the r2
     */
    public FpsHorarioReactorModel getR2() {
        return r2;
    }

    /**
     * @param r2 the r2 to set
     */
    public void setR2(FpsHorarioReactorModel r2) {
        this.r2 = r2;
    }

    /**
     * @return the r3
     */
    public FpsHorarioReactorModel getR3() {
        return r3;
    }

    /**
     * @param r3 the r3 to set
     */
    public void setR3(FpsHorarioReactorModel r3) {
        this.r3 = r3;
    }

    /**
     * @return the r4
     */
    public FpsHorarioReactorModel getR4() {
        return r4;
    }

    /**
     * @param r4 the r4 to set
     */
    public void setR4(FpsHorarioReactorModel r4) {
        this.r4 = r4;
    }

    /**
     * @return the r5
     */
    public FpsHorarioReactorModel getR5() {
        return r5;
    }

    /**
     * @param r5 the r5 to set
     */
    public void setR5(FpsHorarioReactorModel r5) {
        this.r5 = r5;
    }

    /**
     * @return the r6
     */
    public FpsHorarioReactorModel getR6() {
        return r6;
    }

    /**
     * @param r6 the r6 to set
     */
    public void setR6(FpsHorarioReactorModel r6) {
        this.r6 = r6;
    }

    /**
     * @return the r7
     */
    public FpsHorarioReactorModel getR7() {
        return r7;
    }

    /**
     * @param r7 the r7 to set
     */
    public void setR7(FpsHorarioReactorModel r7) {
        this.r7 = r7;
    }

    /**
     * @param reactoresList the reactores to set
     */
    public void setReactoresList(List<FpsHorarioReactorModel> reactoresList) {
        this.reactoresList = reactoresList;
    }

    /**
     * @return the reactoresList
     */
    public List<FpsHorarioReactorModel> getReactoresList() {
        reactoresList = new ArrayList<>();
        reactoresList.add(dao.getHorario(fecha, 1));
        reactoresList.add(dao.getHorario(fecha, 2));
        reactoresList.add(dao.getHorario(fecha, 3));
        reactoresList.add(dao.getHorario(fecha, 4));
        reactoresList.add(dao.getHorario(fecha, 5));
        reactoresList.add(dao.getHorario(fecha, 6));
        reactoresList.add(dao.getHorario(fecha, 7));
        return reactoresList;
    }

}
