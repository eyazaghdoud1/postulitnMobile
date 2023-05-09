/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

import java.util.Date;

/**
 *
 * @author Users
 */
public class ProjetFreelance {
      private int idProjet, duree;
   private String theme, description;
   private Date dateDebut, dateFin; 
   public Secteur s; 
   private int idResponsable;
   private String Nom;  
   private int note; 
   

    public ProjetFreelance() {
    }

    public ProjetFreelance(int duree, String theme, String description, Date dateDebut, Date dateFin, Secteur s) {
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s=s;
    }

    public ProjetFreelance(int idProjet, int duree, String theme, String description, Date dateDebut, Date dateFin, Secteur s) {
        this.idProjet = idProjet;
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s=s;
    }

    public ProjetFreelance(int idProjet, int duree, String theme, String description, Date dateDebut, Date dateFin, Secteur s, int idResponsable, String Nom) {
        this.idProjet = idProjet;
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s = s;
        this.idResponsable = idResponsable;
        this.Nom = Nom;
    }

    public ProjetFreelance(int duree, String theme, String description, Date dateDebut, Date dateFin, Secteur s, int idResponsable, String Nom) {
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s = s;
        this.idResponsable = idResponsable;
        this.Nom = Nom;
    }

    public ProjetFreelance(int idProjet, int duree, String theme, String description, Date dateDebut, Date dateFin, Secteur s, int idResponsable, String Nom, int note) {
        this.idProjet = idProjet;
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s = s;
        this.idResponsable = idResponsable;
        this.Nom = Nom;
        this.note = note;
    }


    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }
    
     


    public int getIdProjet() {
        return idProjet;
    }

    public int getDuree() {
        return duree;
    }

    public String getTheme() {
        return theme;
    }

    public String getDescription() {
        return description;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Secteur getS() {
        return s;
    }

    public void setS(Secteur s) {
        this.s = s;
    }

    public int getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(int idResponsable) {
        this.idResponsable = idResponsable;
    }

    public ProjetFreelance(int duree, String theme, String description, Date dateDebut, Date dateFin, Secteur s, int idResponsable) {
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s = s;
        this.idResponsable = idResponsable;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ProjetFreelance{" + "idProjet=" + idProjet + ", duree=" + duree + ", theme=" + theme + ", description=" + description + ", dateDebut=" + dateDebut + ", dateFin=" + dateFin + ", s=" + s + ", idResponsable=" + idResponsable + ", Nom=" + Nom + ", note=" + note + '}';
    }
}
