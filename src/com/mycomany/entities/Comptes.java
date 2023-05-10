/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycomany.entities;

import java.util.Date;


//import models.VisiteGuide;

/**
 *
 * @author dell
 */
public class Comptes {
    
    
 //att
    private int idCompte;
    private String experience, photo,diplome,entreprise,domaine,poste ;
    private Date dateDiplome;
    private int idUtilisateur;
    
    //const
    public Comptes() {
    }
    public Comptes(String experience, String photo, String cv, String diplome, Date dateDiplome, String entreprise) {
        this.experience = experience;
        this.photo = photo;
        this.diplome = diplome;
        this.dateDiplome = dateDiplome;
        this.entreprise = entreprise;
    }

    public Comptes(int idCompte, String experience, String photo, String cv, String diplome, String entreprise, Date dateDiplome) {
        this.idCompte = idCompte;
        this.experience = experience;
        this.photo = photo;
        this.diplome = diplome;
        this.entreprise = entreprise;
        this.dateDiplome = dateDiplome;
    }
    
    public int getIdCompte() {
        return idCompte;
    }

    //getters and setters
    public void setIdCompte(int idCompte) {    
        this.idCompte = idCompte;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

   
    public String getDiplome() {
        return diplome;
    }

    public int getIdUtilisateur() {
        return idUtilisateur;
    }

    public void setIdUtilisateur(int idUtilisateur) {
        this.idUtilisateur = idUtilisateur;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public Date getDateDiplome() {
        return dateDiplome;
    }

    public void setDateDiplome(Date dateDiplome) {
        this.dateDiplome = dateDiplome;
    }
    public String getEntreprise() {
        return entreprise;
    }
    public void setEntreprise(String entreprise) {
    this.entreprise = entreprise;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    //display

    @Override
    public String toString() {
        return "Compte{" + "idCompte=" + idCompte + ", experience=" + experience + ", photo=" + photo + ", diplome=" + diplome + ", entreprise=" + entreprise + ", domaine=" + domaine + ", poste=" + poste + ", dateDiplome=" + dateDiplome + '}';
    }

   
        
    
    
}
    
    

