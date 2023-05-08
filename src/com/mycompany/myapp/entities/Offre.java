package com.mycompany.myapp.entities;
import java.util.Date;


/**
 *
 * @author Aziz Ben Guirat
 */
public class Offre {
     private int idOffre;
    private String poste , description ,lieu ,entreprise ,specialite;
    private Date dateExpiration;
    private Typeoffre type;
    private int idRecruteur;
   
    public Offre() {
    }

    public Offre(int idOffre, String poste, String description,String lieu, String specialite, Date dateExpiration,Typeoffre type,int idRecruteur ) {
        this.idOffre = idOffre;
        this.poste = poste;
        this.description = description;
        this.lieu = lieu;
        this.entreprise = entreprise;
        this.specialite = specialite;
        this.dateExpiration = dateExpiration;
        this.type = type;
        this.idRecruteur = idRecruteur;
        
    }

    public Offre(String poste, String description, String lieu, String entreprise, String specialite, Date dateExpiration, Typeoffre type, int idRecruteur) {
        this.poste = poste;
        this.description = description;
        this.lieu = lieu;
        this.entreprise = entreprise;
        this.specialite = specialite;
        this.dateExpiration = dateExpiration;
        this.type = type;
        this.idRecruteur = idRecruteur;

    }


    
    
    //getters and setters
    public int getId() {
        return idOffre;
    }

    public void setId(int idOffre) {
        this.idOffre = idOffre;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }
     public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
     public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
     public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }
     public Date getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(Date dateExpiration) {
        this.dateExpiration = dateExpiration;
    }
     public Typeoffre getType() {
        return this.type;
    }

    public void setType(Typeoffre type) {
        this.type = type;
    }

    public int getIdRecruteur() {
        return idRecruteur;
    }

    public void setIdRecruteur(int IdRecruteur) {
        this.idRecruteur = IdRecruteur;
    }

    
    //display

    @Override
    public String toString() {
        return "Offre{" + "idOffre=" + idOffre + ", poste=" + poste + ", description=" + description + ", lieu=" + lieu + ", entreprise=" + entreprise + ", specialite=" + specialite + ", dateExpiration=" + dateExpiration + ", type=" + type + ", idRecruteur=" + idRecruteur + '}';
    }

   

    
    
}
