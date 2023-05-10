/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycomany.entities;

/**
 *
 * @author dell
 */
public class Guidesentretiens {
    
    
    
    
    
    private int idguide;
private String domaine;
private String specialite;
private String support;
private double note;
private int nombreNotes;

public Guidesentretiens() {
    this.nombreNotes = 0;
}

public Guidesentretiens(int idguide, String domaine, String specialite, String support, int nombreNote,double note) {
    this.idguide = idguide;
    this.domaine = domaine;
    this.specialite = specialite;
    this.support = support;
    this.note= note;
    this.nombreNotes = nombreNote;
}


    
    //getters and setters
    public int getIdguide() {
        return idguide;
    }

    public void setIdguide(int idguide) {
        this.idguide = idguide;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
     }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getSupport() {
        return support;
    }

    public void setSupport(String support) {
        this.support = support;
    }
    
    
    //nouveauuu
    public double getNote() {
    return note;
}

public void setNote(double note) {
    this.note = note;
}

    public int getNombreNotes() {
        return nombreNotes;
    }

    public void setNombreNotes(int nombreNotes) {
        this.nombreNotes = nombreNotes;
    }
    
     //display

    @Override
    public String toString() {
        return "GuideEntretien{" + "idguide=" + idguide + ", domaine=" + domaine + ", specialite=" + specialite + ", support=" + support + "note=" + note + '}';
    }

    
    
    
    
    
    
    
}
