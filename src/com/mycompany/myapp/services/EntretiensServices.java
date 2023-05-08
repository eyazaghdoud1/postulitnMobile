/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Entretien;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP I5
 */
public class EntretiensServices {

    public ArrayList<Entretien> entretiens;

    public static EntretiensServices instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private EntretiensServices() {
        req = new ConnectionRequest();
    }

    public static EntretiensServices getInstance() {
        if (instance == null) {
            instance = new EntretiensServices();
        }
        return instance;
    }

    public ArrayList<Entretien> parseEntretiens(String jsonText) {
        try {
            entretiens = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> entretiensListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) entretiensListJson.get("root");

            for (Map<String, Object> obj : list) {
                Entretien e = new Entretien();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setType(obj.get("type").toString());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    e.setDate(formatter.parse(obj.get("date").toString()));
                } catch (ParseException ex) {

                }
                e.setHeure(obj.get("heure").toString());
                e.setLieu(obj.get("lieu").toString());

                Map<String, Object> cand = (Map<String, Object>) obj.get("idcandidature");
                Candidature c = new Candidature();
                c.setId((int) Float.parseFloat(cand.get("id").toString()));
                c.setEtat(cand.get("etat").toString());
                
                
                Map<String, Object> user = (Map<String, Object>) cand.get("idcandidat");
                Utilisateur u = new Utilisateur();
                u.setId((int) Float.parseFloat(user.get("id").toString()));
                u.setNom(user.get("nom").toString());
                u.setPrenom(user.get("prenom").toString());

                Map<String, Object> offre = (Map<String, Object>) cand.get("idoffre");
                Offre o = new Offre();
                o.setId((int) Float.parseFloat(offre.get("idoffre").toString()));
                o.setPoste(offre.get("poste").toString());
                o.setEntreprise(offre.get("entreprise").toString());
                o.setLieu(offre.get("lieu").toString());
                
                c.setIdCandidat(u);
                c.setIdOffre(o);

                
                try {
                    c.setDate(formatter.parse(obj.get("date").toString()));
                } catch (ParseException ex) {
                    
                }
                
                e.setCandidature(c);
               
                
                entretiens.add(e);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return entretiens;
    }

    public ArrayList<Entretien> getAllEntretiens(String role, int id) {

        String url = Statics.BASE_APP_URL + "entretiensJSON/" + role + "/" + id;

        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                entretiens = parseEntretiens(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return entretiens;

    }
    
    public ArrayList<Entretien> getEntretiensByCandidature(String role, int id) {
    String url = Statics.BASE_APP_URL + "entretiensCanidatureJSON/"+ id;

        req.setUrl(url);

        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                entretiens = parseEntretiens(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return entretiens;
    }

    // add
    public boolean addEntretien(Entretien e, Candidature c, String date) {

        String url = "http://localhost:8000/addEntretienJSON/" + c.getId()
                + "?type=" + e.getType()
                + "&date=" + date
                + "&lieu=" + e.getLieu()
                + "&horaire=" + e.getHeure();
                

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

    public boolean updateEntretien(Entretien e, String date) {

        String url = Statics.BASE_APP_URL + "updateEntretienJSON/" + e.getId()
              
                + "?date=" + date
                + "&lieu=" + e.getLieu()
                + "&horaire=" + e.getHeure();

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

    // delete
    public boolean deleteEntretien(int id) {

        String url = Statics.BASE_APP_URL + "deleteEntretienJSON/" + id;

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
