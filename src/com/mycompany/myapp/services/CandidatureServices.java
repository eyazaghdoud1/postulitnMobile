/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.Typeoffre;
import com.mycompany.myapp.entities.Utilisateur;
import java.text.ParseException;
import java.util.Date;

/**
 *
 * @author HP I5
 */
public class CandidatureServices {
    
    public ArrayList<Candidature> candidatures;
    
    public static CandidatureServices instance = null;
    public boolean resultOK;
    private ConnectionRequest req;
    
    private CandidatureServices() {
        req = new ConnectionRequest();
    }
    
    public static CandidatureServices getInstance() {
        if (instance == null) {
            instance = new CandidatureServices();
        }
        return instance;
    }
    
    public ArrayList<Candidature> parseCandidatures(String jsonText) {
        try {
            candidatures = new ArrayList<>();
            JSONParser j = new JSONParser();
            System.out.println("here");
            Map<String, Object> candidaturesListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) candidaturesListJson.get("root");
            
            for (Map<String, Object> obj : list) {
                Candidature c = new Candidature();
                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
                c.setCv(obj.get("cv").toString());
                c.setLettre(obj.get("lettre").toString());
                
                c.setEtat(obj.get("etat").toString());
                Map<String, Object> offre = (Map<String, Object>) obj.get("idoffre");
                //offre =(Map<String, Object>) obj.get("idoffre");
                Offre of = new Offre();
                
                of.setPoste(offre.get("poste").toString());
                of.setEntreprise(offre.get("entreprise").toString());
                of.setLieu(offre.get("lieu").toString());
                
                c.setIdOffre(of);
                
                Map<String, Object> user = (Map<String, Object>) obj.get("idcandidat");
                Utilisateur candidat = new Utilisateur();
                candidat.setNom(user.get("nom").toString());
                candidat.setPrenom(user.get("prenom").toString());
                candidat.setEmail(user.get("email").toString());
                candidat.setTel(user.get("tel").toString());
                candidat.setAdresse(user.get("adresse").toString());
                c.setIdCandidat(candidat);

                //  c.setIdCandidat(((int) Float.parseFloat(obj.get("idCandidat").toString())));
                // c.setIdOffre(((int) Float.parseFloat(obj.get("idOffre").toString())));
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    c.setDate(formatter.parse(obj.get("date").toString()));
                } catch (ParseException ex) {
                    
                }
                
                candidatures.add(c);
            }
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return candidatures;
    }
    
    public ArrayList<Candidature> getAllCandidatures(String role, int id) {
        
        String url = Statics.BASE_APP_URL + "allCandidatures/" + role + "/" + id;
        // String url = "http://localhost:8000/all";
        // String url ="http://localhost:8000/allCandidatures/Candidat/68"; 
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
                candidatures = parseCandidatures(new String(req.getResponseData()));

                //  Log.p("Response JSON: " + req.getResponseData() );
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return candidatures;
        
    }

    /* add */
    public boolean addCandidature(Candidature c) {
        
        String url = Statics.BASE_APP_URL + "addCandidatureJSON/" + c.getIdOffre().getId() + "/" + c.getIdCandidat().getId()
                + "?cv="
                + c.getCv() + "&lettre=" + c.getLettre();
        
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

    /* update */
    public boolean updateCandidature(Candidature c) {
        
        String url = Statics.BASE_APP_URL + "updateCandidatureJSON/" + c.getId()
                + "?cv="
                + c.getCv() + "&lettre=" + c.getLettre();
        
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

    /* delete */
    public boolean deleteCandidature(int id) {
        
        String url = Statics.BASE_APP_URL + "deleteCandidatureJSON/" + id;
        
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

    // valider
   
    public boolean validerCandidature(int id) {
        
        String url = Statics.BASE_APP_URL + "validerCandidatureJSON/" + id;
        
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
    
     public boolean accepterCandidature(int id) {
        
        String url = Statics.BASE_APP_URL + "accepterCandidatureJSON/" + id;
        
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
     
     public boolean refuserCandidature(int id) {
        
        String url = Statics.BASE_APP_URL + "refuserCandidatureJSON/" + id;
        
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
