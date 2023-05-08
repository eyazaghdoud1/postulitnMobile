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
public class Utilisateur {
    private int id;
    private String nom, prenom, email, tel, adresse, mdp;
    private Date dateNaissance;
    private Role role;
    private String salt;
    
    //Constructors
    public Utilisateur() {
    }
    
    public Utilisateur(int id, Role role) {
        this.id = id;
        this.role = role;
    }

    public Utilisateur(int id, String nom, String prenom, String email, String tel, String adresse, String mdp, Date dateNaissance, Role role, String salt) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        this.mdp = mdp;
        this.dateNaissance = dateNaissance;
        this.role = role;
        this.salt = salt;
    }

    public Utilisateur(String nom, String prenom, String email, String tel, String adresse, String mdp, Date dateNaissance, Role role, String salt) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.tel = tel;
        this.adresse = adresse;
        this.mdp = mdp;
        this.dateNaissance = dateNaissance;
        this.role = role;
        this.salt = salt;
    }

    //getters & setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
    

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    //show
    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", email=" + email + ", tel=" + tel + ", adresse=" + adresse + ", mdp=" + mdp + ", dateNaissance=" + dateNaissance + ", role=" + role + '}';
    }

    

    
    
    

   
}