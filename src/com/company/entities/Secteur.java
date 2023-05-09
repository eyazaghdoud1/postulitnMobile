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
public class Secteur {
        
     private int idSecteur;
     private String description; 

     
    public Secteur() {
    }
     
    public Secteur(int idSecteur, String description) {
        this.idSecteur = idSecteur;
        this.description = description;
    }
    public Secteur(String description) {
        
        this.description = description;
    }

    public int getIdSecteur() {
        return idSecteur;
    }

    public String getDescription() {
        return description;
    }

    public void setIdSecteur(int idSecteur) {
        this.idSecteur = idSecteur;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "secteur{" + "idSecteur=" + idSecteur + ", description=" + description + '}';
    }
    
    
}
