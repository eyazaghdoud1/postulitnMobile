/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entities;

/**
 *
 * @author Users
 */
public class Commentaires {
    
     private int idCommentaire;
     private String contenu; 
     private int idUser; 
     private int idProjet;

    public Commentaires() {
    }

    public Commentaires(String contenu) {
        this.contenu = contenu;
    }

    public Commentaires(int idCommentaire, String contenu) {
        this.idCommentaire = idCommentaire;
        this.contenu = contenu;
    }

    
    
    public int getIdCommentaire() {
        return idCommentaire;
    }

    public String getContenu() {
        return contenu;
    }

    public Commentaires(int idCommentaire, String contenu, int idUser) {
        this.idCommentaire = idCommentaire;
        this.contenu = contenu;
        this.idUser = idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUser() {
        return idUser;
    }
   

    public void setIdCommentaire(int idCommentaire) {
        this.idCommentaire = idCommentaire;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Commentaires(int idCommentaire, String contenu, int idUser, int idProjet) {
        this.idCommentaire = idCommentaire;
        this.contenu = contenu;
        this.idUser = idUser;
        this.idProjet = idProjet;
    }

    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    @Override
    public String toString() {
        return "Commentaire{" + "idCommentaire=" + idCommentaire + ", contenu=" + contenu + ", idUser=" + idUser + ", idProjet=" + idProjet + '}';
    }
    
    
    
}
