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
import com.mycomany.entities.Comptes;
import com.mycomany.utils.Statics;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;




/**
 *
 * @author dell
 */
public class ComptesServices {
    
    
    
     public Comptes c ;
    
    
    
    
      public static ComptesServices instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ComptesServices getInstance() {
        if(instance == null )
            instance = new ComptesServices();
        return instance ;
    }
    
    
    
    public ComptesServices() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutCompte(Comptes comptes, String date) {
        
        String url ="http://localhost:8000/addCompteJSON/new/" + comptes.getIdUtilisateur() + "?experience="+comptes.getExperience()+"&photo="+comptes.getPhoto()+"&diplome="+comptes.getDiplome()+"&entreprise="+comptes.getEntreprise()+"&domaine="+comptes.getDomaine()+"&poste="+comptes.getPoste()+"&dateDiplome="+date; // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    /*
    public ArrayList<Comptes>affichageComptes() {
        ArrayList<Comptes> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/allComptes";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapComptes = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapComptes.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Comptes ge = new Comptes();
                        
                        //dima id fi codename one float 5outhouha
                        float idCompte = Float.parseFloat(obj.get("idCompte").toString());
                        
                        String experience = obj.get("experience").toString();
                         String photo = obj.get("photo").toString();
                        String diplome = obj.get("diplome").toString();
                        String entreprise = obj.get("entreprise").toString();
                         String domaine = obj.get("domaine").toString();
                         String poste = obj.get("poste").toString();
                         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              
                            Date dateDiplome = formatter.parse(obj.get("dateDiplome").toString());
                            
                            
                            
                        ge.setIdCompte((int)idCompte);
                        ge.setExperience(experience);
                        ge.setPhoto(photo);
                        ge.setDiplome(diplome);
                          ge.setEntreprise(entreprise);
                          ge.setDomaine(domaine);
                            ge.setPoste(poste);
                         ge.setDateDiplome(dateDiplome);
                       
                        result.add(ge);
                       
                    
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
        
        
    }
    */
    
    public Comptes parseComptes(String jsonText) {
        
        Comptes c = null;
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            c = new Comptes();
             float idCompte = Float.parseFloat(obj.get("idcompte").toString());
                        
                        String experience = obj.get("experience").toString();
                         String photo = obj.get("photo").toString();
                        String diplome = obj.get("diplome").toString();
                        String entreprise = obj.get("entreprise").toString();
                         String domaine = obj.get("domaine").toString();
                         String poste = obj.get("poste").toString();
                         SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              
                            Date dateDiplome = formatter.parse(obj.get("datediplome").toString());
                            
                            
                            
                        c.setIdCompte((int)idCompte);
                        c.setExperience(experience);
                        c.setPhoto(photo);
                        c.setDiplome(diplome);
                          c.setEntreprise(entreprise);
                          c.setDomaine(domaine);
                            c.setPoste(poste);
                         c.setDateDiplome(dateDiplome);
                          }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
             return c;            
    }

    
        public Comptes showComptes (int id){
        
         String url = Statics.BASE_URL+"/Compte/"+id;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                c = parseComptes(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return c;
        
            
    }
    
  
    
    //Delete 
    public boolean deleteCompte(int idCompte ) {
        String url = Statics.BASE_URL +"/deleteCompteJSON/"+idCompte;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
    
    
    //Update 
    public boolean modifierCompte(Comptes comptes, String date) {
        String url = Statics.BASE_URL +"/updateCompteJSON/"+comptes.getIdCompte()+"?experience="+comptes.getExperience()+"&photo="+comptes.getPhoto()+"&diplome="+comptes.getDiplome()+"&entreprise="+comptes.getEntreprise()+"&domaine="+comptes.getDomaine()+"&poste="+comptes.getPoste()+"&dateDiplome="+date;
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
    
    
    
    
    
    
}
