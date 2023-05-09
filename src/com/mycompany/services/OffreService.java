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
import com.mycompany.entities.Offre;
import com.mycompany.entities.typeOffre;
import com.mycompany.utils.Statics;

import java.util.ArrayList;

import java.util.Map;

import org.json.JSONObject;

/**
 *
 * @author Aziz Ben Guirat
 */
public class OffreService {

    private static OffreService instance;
    private ConnectionRequest req;
    public ArrayList<Offre> Offres;
    public ArrayList<Offre> Forms1;
    public boolean valide;
    public boolean ResultOK;

    public OffreService() {
        req = new ConnectionRequest();
    }

    public static OffreService getInstance() {
        if (instance == null) {
            instance = new OffreService();
        }
        return instance;

    }

    public ArrayList<Offre> affichageOffres() {
        ArrayList<Offre> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/api/offres/all";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    java.util.List<Map<String, Object>> listOfMaps = (java.util.List<Map<String, Object>>) mapOffres.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Offre offre = new Offre();

                        offre.setIdOffre((int) Float.parseFloat(obj.get("idoffre").toString()));
                        offre.setPoste((String) obj.get("poste"));
                        offre.setEntreprise((String) obj.get("entreprise"));
                        offre.setDateExpiration(obj.get("dateexpiration").toString());
                        offre.setSpecialite((String) obj.get("specialite"));
                        offre.setLieu((String) obj.get("lieu"));
                        Map<String, Object> idTypeMap = (Map<String, Object>) obj.get("idtype");
                        typeOffre typeOffre = new typeOffre();
                        typeOffre.setIdtype((int) Float.parseFloat(idTypeMap.get("idtype").toString()));
                        typeOffre.setDescription((String) idTypeMap.get("description"));
                        offre.setIdtype(typeOffre);
                        offre.setDescription((String) obj.get("description"));

                        //insert data into ArrayList result
                        result.add(offre);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }
    public ArrayList<Offre> affichageOffreExceptUser(int id) {
        ArrayList<Offre> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/api/offres/all/"+id;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    java.util.List<Map<String, Object>> listOfMaps = (java.util.List<Map<String, Object>>) mapOffres.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Offre offre = new Offre();

                        offre.setIdOffre((int) Float.parseFloat(obj.get("idoffre").toString()));
                        offre.setPoste((String) obj.get("poste"));
                        offre.setEntreprise((String) obj.get("entreprise"));
                        offre.setDateExpiration(obj.get("dateexpiration").toString());
                        offre.setSpecialite((String) obj.get("specialite"));
                        offre.setLieu((String) obj.get("lieu"));
                        Map<String, Object> idTypeMap = (Map<String, Object>) obj.get("idtype");
                        typeOffre typeOffre = new typeOffre();
                        typeOffre.setIdtype((int) Float.parseFloat(idTypeMap.get("idtype").toString()));
                        typeOffre.setDescription((String) idTypeMap.get("description"));
                        offre.setIdtype(typeOffre);
                        offre.setDescription((String) obj.get("description"));

                        //insert data into ArrayList result
                        result.add(offre);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }

    public ArrayList<Offre> affichageMesOffres( int id) {
        ArrayList<Offre> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/api/offres/user/"+id;
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    java.util.List<Map<String, Object>> listOfMaps = (java.util.List<Map<String, Object>>) mapOffres.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        Offre offre = new Offre();

                        float id = Float.parseFloat(obj.get("idoffre").toString());
                        offre.setIdOffre((int) id);
                        offre.setPoste((String) obj.get("poste"));
                        offre.setEntreprise((String) obj.get("entreprise"));
                        offre.setDateExpiration(obj.get("dateexpiration").toString());
                        offre.setSpecialite((String) obj.get("specialite"));
                        offre.setLieu((String) obj.get("lieu"));
                        Map<String, Object> idTypeMap = (Map<String, Object>) obj.get("idtype");
                        typeOffre typeOffre = new typeOffre();
                        typeOffre.setIdtype((int) Float.parseFloat(idTypeMap.get("idtype").toString()));
                        typeOffre.setDescription((String) idTypeMap.get("description"));
                        offre.setIdtype(typeOffre);
                        offre.setDescription((String) obj.get("description"));

                        //insert data into ArrayList result
                        result.add(offre);

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }
    //ajout 
    public boolean addOffre(Offre F , String idtype) {
        ResultOK = false;
        String url = Statics.BASE_URL + "/api/createOffre/69" +
             "?poste=" + F.getPoste() +
             "&entreprise=" + F.getEntreprise() +
             "&specialite=" + F.getSpecialite() +
             "&lieu=" + F.getLieu() +
             "&description=" + F.getDescription() +
             "&dateExpiration=" + F.getDateExpiration() + 
              "&idType=" + idtype ;
        
        req.setUrl(url);
        

        

        req.setPost(true);
        

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK = req.getResponseCode() == 200;
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }

    public ArrayList<String> getAllTypesOffres() {
        ArrayList<String> result = new ArrayList<>();

        String url = Statics.BASE_URL + "/api/typeoffre/all";
        req.setUrl(url);

        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();

                try {
                    Map<String, Object> mapTypeOffres = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));

                    java.util.List<Map<String, Object>> listOfMaps = (java.util.List<Map<String, Object>>) mapTypeOffres.get("root");

                    for (Map<String, Object> obj : listOfMaps) {
                        typeOffre type = new typeOffre();

                        type.setIdtype((int) Float.parseFloat(obj.get("idtype").toString()));
                        type.setDescription((String) obj.get("description"));

                        //insert data into ArrayList result
                        result.add(type.getDescription());

                    }

                } catch (Exception ex) {

                    ex.printStackTrace();
                }

            }
        });

        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;

    }
    
    
    public boolean deleteOffre(int id) {
        
        String url = Statics.BASE_URL + "/api/deleteOffre/"+id;
        
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }
    
    
    
     public boolean updateOffre(Offre c, String idtype,int idOldOffre) {
        
        String url = Statics.BASE_URL + "/api/updateOffre/" +idOldOffre +
             "?poste=" + c.getPoste() +
             "&entreprise=" + c.getEntreprise() +
             "&specialite=" + c.getSpecialite() +
             "&lieu=" + c.getLieu() +
             "&description=" + c.getDescription() +
             "&dateExpiration=" + c.getDateExpiration() + 
              "&idType=" + idtype ;
        req.setUrl(url);
        req.setPost(true);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ResultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return ResultOK;
    }
}
