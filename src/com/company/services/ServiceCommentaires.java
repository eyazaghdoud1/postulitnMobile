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
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/**
 *
 * @author Users
 */
public class ServiceCommentaires {
    // crud symfony 
    
    //singleton
    
    public static ServiceCommentaires instance = null ; 
    public static boolean resultOk = true;
    //initilisation connection request 
    private ConnectionRequest req;
    
    
    
    
    public static ServiceCommentaires getInstance() {
        if(instance == null )
            instance = new ServiceCommentaires();
        return instance ;
    }
    
    
    
    
    public ServiceCommentaires(){
        req = new ConnectionRequest();

    }
    
    //ajout d'un commentaire 
     public void ajoutCommentaires(Commentaires commentaire) {
     
       String url =Statics.BASE_URL+"/addCommentairejson1?idProjet="+commentaire.getIdProjet()+"contenu="+commentaire.getContenu();
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json 
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution de la request 
           
}
     
     //affichage
    
    public ArrayList<Commentaires>affichageCommentaire() {
        ArrayList<Commentaires> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/listCommentairesjson/{idprojet}";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapCommentaires = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapCommentaires.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Commentaires com = new Commentaires();
                        
                        //dima id fi codename one float 5outhouha
                        float IdCommentaire = Float.parseFloat(obj.get("IdCommentaire").toString());
                        String contenu = obj.get("contenu").toString();
                        float IdProjet = Float.parseFloat(obj.get("IdProjet").toString());
                        float IdUser = Float.parseFloat(obj.get("IdUser").toString());
                        
                        com.setIdCommentaire((int)IdCommentaire);
                        com.setContenu(contenu);
                        com.setIdProjet((int)IdProjet);
                        com.setIdUser((int)IdUser);
   
                        //insert data into ArrayList result
                        result.add(com);
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
     public Commentaires DetailCommentaires( int id , Commentaires commentaire) {
        
        String url = Statics.BASE_URL+"/DetailcommentaireJson?"+id;
        req.setUrl(url);
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
            JSONParser jsonp = new JSONParser();
            try {  
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(str.toCharArray()));
              commentaire.setIdCommentaire(Integer.parseInt(obj.get("IdCommentaire").toString()));
                commentaire.setContenu(obj.get("contenu").toString());
                commentaire.setIdProjet(Integer.parseInt(obj.get("IdProjet").toString()));
                commentaire.setIdUser(Integer.parseInt(obj.get("IdUser").toString()));
             
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }          
            System.out.println("data === "+str);
   
        }));
        
              NetworkManager.getInstance().addToQueueAndWait(req);//execution 
              return commentaire;
    }
    
     
     //delete 
       public boolean deleteCommentaires(int id ) {
        String url = Statics.BASE_URL +"/deleteCommentairesjson?id="+id;
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
    public boolean modifierCommentaire(Commentaires commentaire) {
        String url = Statics.BASE_URL +"/updateCommentairesjson?idProjet="+commentaire.getIdProjet()+"idCommentaire="+commentaire.getIdCommentaire()+"contenu="+commentaire.getContenu();
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution des requetes
    return resultOk;
        
    }
    
     
    
    
    
    
    
    
    
    
    
}