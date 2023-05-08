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
public class Candidature {
    
    private int id;
    // private Utilisateur candidat;
    private Utilisateur idCandidat;
    // private Offre offre;
    private Offre idOffre;
    private String cv; 
    private String lettre;
    private Date date;
    private String etat;




    /* constructeur pour la récupération de la bdd */
    public Candidature() {
    
    }
    
    /* getters and setters */
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getLettre() {
        return lettre;
    }

    public void setLettre(String lettre) {
        this.lettre = lettre;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Utilisateur getIdCandidat() {
        return idCandidat;
    }

    public void setIdCandidat(Utilisateur idCandidat) {
        this.idCandidat = idCandidat;
    }

    public Offre getIdOffre() {
        return idOffre;
    }

    public void setIdOffre(Offre idOffre) {
        this.idOffre = idOffre;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    
    /* methode toString */
    @Override
    public String toString() {
        return "Candidature{" + "id=" + id + ", idCandidat=" + idCandidat + ", idOffre=" + idOffre + ", cv=" + cv + ", lettre=" + lettre + ", date=" + date + ", etat=" + etat + '}';
    }
    
    
   
    
    
}
