/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Aziz Ben Guirat
 */
import java.util.Date;

/**
 *
 * @author Lenovo
 */
public class Offre {
    //nemchio taw nchofo entity fi symfony
    
    private int idOffre;
    private String poste,description,lieu,entreprise,specialite;
    private String dateExpiration;
    private typeOffre idtype;
    private Utilisateur idRecruteur;

    public Offre(int idOffre, String poste, String description, String lieu, String entreprise, String specialite, String dateExpiration, typeOffre idtype, Utilisateur idRecruteur) {
        this.idOffre = idOffre;
        this.poste = poste;
        this.description = description;
        this.lieu = lieu;
        this.entreprise = entreprise;
        this.specialite = specialite;
        this.dateExpiration = dateExpiration;
        this.idtype = idtype;
        this.idRecruteur = idRecruteur;
    }

    public typeOffre getIdtype() {
        return idtype;
    }

    public Utilisateur getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdtype(typeOffre idtype) {
        this.idtype = idtype;
    }

    public void setIdRecruteur(Utilisateur idRecruteur) {
        this.idRecruteur = idRecruteur;
    }
    

    public Offre() {
    }

    
    
    
    public Offre(int idOffre, String poste, String description, String lieu, String specialite ,String dateExpiration) {
        this.idOffre = idOffre;
        this.poste = poste;
        this.description = description;
        this.lieu = lieu;
        this.specialite = specialite;
        this.dateExpiration = dateExpiration;
    }


    public int getIdOffre() {
        return idOffre;
    }

    public String getPoste() {
        return poste;
    }

    public String getDescription() {
        return description;
    }

    public String getLieu() {
        return lieu;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setIdOffre(int idOffre) {
        this.idOffre = idOffre;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    @Override
    public String toString() {
        return "Offre{" + "idOffre=" + idOffre + ", poste=" + poste + ", description=" + description + ", lieu=" + lieu + ", entreprise=" + entreprise + ", specialite=" + specialite + ", dateExpiration=" + dateExpiration + ", idtype=" + idtype + ", idRecruteur=" + idRecruteur + '}';
    }

    
    
    
    
    
    
    
    }


