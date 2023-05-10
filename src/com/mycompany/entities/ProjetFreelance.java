/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

import com.mycompany.entities.Utilisateur;
import java.util.Date;

/**
 *
 * @author Users
 */
public class ProjetFreelance {
      private int idProjet, duree;
   private String theme, description;
   private String dateDebut, dateFin; 
   public Secteur s; 
   private Utilisateur idResponsable;
   private String Nom;  
   private int note; 
   

    public ProjetFreelance() {
    }

    public ProjetFreelance(int duree, String theme, String description, String dateDebut, String dateFin, Secteur s) {
        this.duree = duree;
        this.theme = theme;
        this.description = description;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.s=s;
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

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateFin() {
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

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public Secteur getS() {
        return s;
    }

    public void setS(Secteur s) {
        this.s = s;
    }

    public Utilisateur getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Utilisateur idResponsable) {
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
