/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;



/**
 *
 * @author HP I5
 */
public class Entretien  {

    private int id;
    private Candidature candidature; 
    /* type: par tel ou en presentiel */
    private String type; 
    private Date date;
    private String heure; 
    private String lieu;
    private int guideId;
    
    /* constructeur vide */
    public Entretien() {
    }



    /* getters and setters */ 
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Candidature getCandidature() {
        return candidature;
    }
    public int getCandidatureId() {
        return candidature.getId();
    }

    public void setCandidature(Candidature candidature) {
        this.candidature = candidature;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public int getGuideId() {
        return guideId;
    }

    public void setGuideId (int guideId) {
        this.guideId = guideId;
    }

    @Override
    public String toString() {
        return "Entretien{" + "id=" + id + ", candidature=" + candidature + ", type=" + type + ", date=" + date + ", heure=" + heure + ", lieu=" + lieu + ", idGuide=" + guideId + '}';
    }

    

    
}

