/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.entities.Commentaires;
import com.company.entities.ProjetFreelance;
import com.company.entities.Secteur;
import com.mycompany.entities.Utilisateur;

import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Users
 */
public class ServiceProjets {

    public ArrayList<ProjetFreelance> ProjetFreelances;

    public static ServiceProjets instance = null;
    public boolean resultOK;
    //initilisation connection request 
    private ConnectionRequest req;

    public static ServiceProjets getInstance() {
        if (instance == null) {
            instance = new ServiceProjets();
        }
        return instance;
    }

    public ServiceProjets() {
        req = new ConnectionRequest();

    }

    public boolean addProjets(ProjetFreelance pf, String dated, String datef, int id) {

        String url = "http://localhost:8000/addProjetsjson/"+id
                + "?theme=" + pf.getTheme()
                + "&description=" + pf.getDescription()
                + "&duree=" + pf.getDuree()
                + "&datedebut=" + dated
                + "&datedebut=" + datef
                + "&nom=" + pf.getNom()
                + "&secteur=" + pf.getS().getDescription();
        ;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    public ArrayList<ProjetFreelance> parseProjetFreelance(String jsonText) {
        try {
            ProjetFreelances = new ArrayList<>();
            JSONParser j = new JSONParser();
            System.out.println("here");
            Map<String, Object> ProjetFreelancesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) ProjetFreelancesListJson.get("root");

            for (Map<String, Object> obj : list) {
                ProjetFreelance c = new ProjetFreelance();
                float id = Float.parseFloat(obj.get("idprojet").toString());
                c.setIdProjet((int) id);
                c.setDateDebut(obj.get("datedebut").toString());
                c.setDateFin(obj.get("datefin").toString());
                float duree = Float.parseFloat(obj.get("duree").toString());
                c.setDuree((int) duree);
                Map<String, Object> user = (Map<String, Object>) obj.get("idresponsable");
                Utilisateur responsable = new Utilisateur();
                responsable.setNom(user.get("nom").toString());
                responsable.setPrenom(user.get("prenom").toString());
                responsable.setEmail(user.get("email").toString());
               
                
               
                c.setIdResponsable(responsable);
                c.setNom(obj.get("nom").toString());
                c.setTheme(obj.get("theme").toString());
                Map<String, Object> idsecteurMap = (Map<String, Object>) obj.get("idsecteur");
                Secteur sec = new Secteur();
                sec.setIdSecteur((int) Float.parseFloat(idsecteurMap.get("idsecteur").toString()));
                sec.setDescription((String) idsecteurMap.get("description"));
                c.setS(sec);
                c.setDescription(obj.get("description").toString());

                ProjetFreelances.add(c);
            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return ProjetFreelances;
    }

    
    
    public ArrayList<ProjetFreelance> getAllProjets(int id) {
        
        String url = Statics.BASE_URL + "/AllProjetsjsonN/"+id;
        
        req.setUrl(url);
        
        if (req == null) {
            System.out.println("requete nulle");
        } else {
            System.out.println("body" + req.getUrl());
        }
        
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ProjetFreelances = parseProjetFreelance(new String(req.getResponseData()));

                //  Log.p("Response JSON: " + req.getResponseData() );
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return ProjetFreelances;
        
    }
    //detail
    public ProjetFreelance DetailProjetFreelance(int id, ProjetFreelance pf) {

        String url = Statics.BASE_URL + "/DetailcommentaireJson?" + id;
        req.setUrl(url);
        String str = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));
                /*      pf.setIdCommentaire(Integer.parseInt(obj.get("IdCommentaire").toString()));
                pf.setContenu(obj.get("contenu").toString());
                pf.setIdProjet(Integer.parseInt(obj.get("IdProjet").toString()));
                pf.setIdUser(Integer.parseInt(obj.get("IdUser").toString()));*/

            } catch (IOException ex) {
                System.out.println("error related to sql :( " + ex.getMessage());
            }
            System.out.println("data === " + str);

        }));

        NetworkManager.getInstance().addToQueueAndWait(req);//execution 
        return pf;
    }

   public boolean deleteProjet(int id) {
        
        String url = Statics.BASE_URL + "/api/deleteProjet/"+id;
        
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

    //Update 
     public boolean modifierProjets(ProjetFreelance pf, String dated, String datef, int id) {

        String url = "http://localhost:8000/updateProjetsjson/"+id
                + "?theme=" + pf.getTheme()
                + "&description=" + pf.getDescription()
                + "&duree=" + pf.getDuree()
                + "&datedebut=" + dated
                + "&datedebut=" + datef
                + "&nom=" + pf.getNom()
                + "&secteur=" + pf.getS().getDescription();
        ;

        req.setUrl(url);
        req.setPost(false);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }

}
