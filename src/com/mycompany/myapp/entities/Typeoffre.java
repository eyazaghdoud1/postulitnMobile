/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

/**
 *
 * @author Aziz Ben Guirat
 */
public class Typeoffre {
    private int idType ;
    private String description ;
    
    
    public Typeoffre() {}
     public Typeoffre(int idType,String description) {
        this.idType = idType;
        this.description = description;
       
       }
     
     //getters and setters
    public int getId() {
        return idType;
    }

    public void setId(int idType) {
        this.idType = idType;
    }

     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    //display

    @Override
    public String toString() {
        return "Typeoffre{" + "idType=" + idType + ", description=" + description +'}';
    }
}


