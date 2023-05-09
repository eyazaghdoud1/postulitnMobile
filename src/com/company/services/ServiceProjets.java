/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import com.company.entities.Commentaires;
import com.company.entities.ProjetFreelance;
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
    
        public static ServiceProjets instance = null ; 
       public boolean resultOK;
    //initilisation connection request 
    private ConnectionRequest req;
    
    
    
    
    public static ServiceProjets getInstance() {
        if(instance == null )
            instance = new ServiceProjets();
        return instance ;
    }
    
    
    
    
    public ServiceProjets(){
        req = new ConnectionRequest();

    }
    
    //ajout d'un commentaire 
     public void ajoutProjets(ProjetFreelance pf) {
     
       String url =Statics.BASE_URL+"/addProjetsjson?theme"+pf.getTheme()+"description="+pf.getDescription()+"duree="+pf.getDuree()+"dateDebut="+pf.getDateDebut()+"dateFin="+pf.getDateFin()+"idSecteur="+pf.getS()+"idResonsable="+pf.getIdResponsable()+"nom="+pf.getNom()+"note="+pf.getNote();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            String str = new String(req.getResponseData());//Reponse json 
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de la request       
}
     public boolean addProjets(ProjetFreelance pf, String date) {

        String url = "http://localhost:8000/addProjetsjson?/" 
                + "?theme=" + pf.getTheme()
                + "?description=" + pf.getDescription()
                + "?duree=" + pf.getDuree()
                + "&datedebut=" + date
                + "&datedebut=" + date
                + "&secteur=" + pf.getS()
                + "&Nom=" + pf.getNom()
                + "&Note=" + pf.getNote();
                

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
     
     //affichage
    
    public ArrayList<ProjetFreelance>affichageProjet() {
        ArrayList<ProjetFreelance> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/listCommentairesjson/{idprojet}";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapProjetFreelance = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapProjetFreelance.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        ProjetFreelance pf = new ProjetFreelance();
                        
                        //dima id fi codename one float 5outhouha
                       /* float IdCommentaire = Float.parseFloat(obj.get("IdCommentaire").toString());
                        String contenu = obj.get("contenu").toString();
                        float IdProjet = Float.parseFloat(obj.get("IdProjet").toString());
                        float IdUser = Float.parseFloat(obj.get("IdUser").toString());
                        
                        com.setIdCommentaire((int)IdCommentaire);
                        com.setContenu(contenu);
                        com.setIdProjet((int)IdProjet);
                        com.setIdUser((int)IdUser);*/
   
                        //insert data into ArrayList result
                        result.add(pf);
                    }         
                }catch(Exception ex) {     
                    ex.printStackTrace();
                }
            }
        });
      NetworkManager.getInstance().addToQueueAndWait(req);//execution de la requete
        return result; 
    }
     
    
    //detail
     public ProjetFreelance DetailProjetFreelance( int id , ProjetFreelance pf) {
        
        String url = Statics.BASE_URL+"/DetailcommentaireJson?"+id;
        req.setUrl(url);
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {  
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));
        /*      pf.setIdCommentaire(Integer.parseInt(obj.get("IdCommentaire").toString()));
                pf.setContenu(obj.get("contenu").toString());
                pf.setIdProjet(Integer.parseInt(obj.get("IdProjet").toString()));
                pf.setIdUser(Integer.parseInt(obj.get("IdUser").toString()));*/
             
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }          
            System.out.println("data === "+str);
   
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution 
              return pf;
    }
    
     
     //delete 
       public boolean deleteProjetFreelance(int id ) {
        String url = Statics.BASE_URL +"/deleteCommentairesjson?id="+id;
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOK;
    }
    
    
    
    //Update 
    public boolean modifierProjetFreelance(Commentaires commentaire) {
        String url = Statics.BASE_URL +"/updateCommentairesjson?idProjet="+commentaire.getIdProjet()+"idCommentaire="+commentaire.getIdCommentaire()+"contenu="+commentaire.getContenu();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution des requetes
    return resultOK;
        
    }
    
}
