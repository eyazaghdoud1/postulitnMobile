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
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.myapp.GUI.SessionManager;
import com.mycompany.myapp.UTILS.Statics;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//import java.sql.Date;
////import java.util.ArrayList;
////
////import java.util.List;
////import java.util.Map;


/**
 * 
 *
 * @author ezine
 */
public class ServiceUtilisateur {
    
    public static ServiceUtilisateur instance =null;
    public static boolean resultOk = true;
    String json;
    private ConnectionRequest req;
    public ArrayList<Utilisateur> utilisateurs;
    Utilisateur u;
    
    public static ServiceUtilisateur getInstance(){
        if (instance == null)
            instance = new ServiceUtilisateur();
            return instance;
        
    }
    
    public ServiceUtilisateur(){
        req= new ConnectionRequest();
    }
    
    
    //signin
    
      public void signin(Utilisateur u) {
        
        
        String url = Statics.BASE_URL+"/connexionJSON?email="+u.getEmail()+"&mdp="+u.getMdp();
        req = new ConnectionRequest(url); 
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
                System.out.println(json);
            if(json.equals("null")) {
                Dialog.show("Echec d'authentification","Username ou mot de passe eronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                 Statics.USER=parseUser(new String(req.getResponseData()));
               //  System.out.println(Statics.USER.getEmail());
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                //Session 
/*                 float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);
                
                SessionManager.setMdp(user.get("mdp").toString());
                SessionManager.setEmail(user.get("email").toString());*/
                Dialog.show("Success","You are signed in","OK",null);
                
            }}catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
        //Signup
   
    public void signup(Utilisateur u, String datenaissance, String role ) {
        
     
        String url=Statics.BASE_URL +"/addUtilisateurJSON?nom="+u.getNom()+"&prenom="+u.getPrenom()
                +"&email="+u.getEmail()+"&tel="+u.getEmail()+"&adresse="+u.getAdresse()+"&mdp="+u.getMdp()  +"&datenaissance="+ datenaissance 
                +"&role="+role;
       
        
        req=new ConnectionRequest();
        req.setUrl(url);
        req.setPost(false);
         req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
       
        //Controle de saisie
        /*  if(u.getNom().equals(" ") && u.getPrenom().equals(" ") && u.getEmail().equals(" ") && u.getMdp().equals(" ") && u.getTel().equals(" ")) {
             
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }*/
 
        /* req.addResponseListener((e)-> {
            byte[]data = (byte[]) e.getMetaData();
            String responseData = new String(data);
            
            System.out.println("data ===>"+responseData);
        }
        );
     
        NetworkManager.getInstance().addToQueueAndWait(req);*/
        
    }

    
    
    
    
   /* public ArrayList<Utilisateur> parseUser(String jsonText) {
        
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> usersListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

             List<Map<String, Object>> list = (List<Map<String, Object>>) usersListJson.get("root");

            for (Map<String, Object> obj : list) {
                Utilisateur u = new Utilisateur();
                float id = Float.parseFloat(obj.get("id").toString());
                u.setId((int) id);
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setEmail(obj.get("email").toString());
                u.setTel(obj.get("tel").toString());
                u.setAdresse(obj.get("adresse").toString());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
              try {
                    u.setDateNaissance(formatter.parse(obj.get("datenaissance").toString()));
                } catch (ParseException ex) {

                }
                u.setMdp(obj.get("mdp").toString());
                
                Map<String, Object> role = (Map<String, Object>) obj.get("id");
                Role r = new Role();
                r.setId((int) Float.parseFloat(obj.get("id").toString()));
                r.setDescription(json);
               
                
                u.setRole(r);

                
                utilisateurs.add(u);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return utilisateurs;
    } 
    */
    
    public Utilisateur parseUser(String jsonText) {
        
        Utilisateur u = null;
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> userJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            u = new Utilisateur();
            float id = Float.parseFloat(userJson.get("id").toString());
            u.setId((int) id);
            u.setNom(userJson.get("nom").toString());
            u.setPrenom(userJson.get("prenom").toString());
            u.setEmail(userJson.get("email").toString());
            u.setTel(userJson.get("tel").toString());
            u.setAdresse(userJson.get("adresse").toString());
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                u.setDateNaissance(formatter.parse(userJson.get("datenaissance").toString()));
            } catch (ParseException ex) {

            }
            u.setMdp(userJson.get("mdp").toString());

            Map<String, Object> role = (Map<String, Object>) userJson.get("idrole");
            Role r = new Role();
            r.setId((int) Float.parseFloat(role.get("idrole").toString()));
            r.setDescription(role.get("description").toString());

            u.setRole(r);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return u;
    }

    
        public Utilisateur showUser (int id){
        
        String url = Statics.BASE_URL+"/UserJSON/"+id;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                u=parseUser(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return u;
        
            
    }
    /*
     public ArrayList<Utilisateur> getList() {
        try {
            Map<String, Object> parsedJson = new JSONParser().parseJSON(new CharArrayReader(
                    new String(cr.getResponseData()).toCharArray()
            ));
            List<Map<String, Object>> list = (List<Map<String, Object>>) parsedJson.get("root");

            for (Map<String, Object> obj : list) {
                Utilisateur user = new Utilisateur(
                        (int) Float.parseFloat(obj.get("id").toString()),

                        (String) obj.get("email"),
                        (String) obj.get("Role"),
                        (String) obj.get("mdp"),
                        (String) obj.get("nom"),
                        (String) obj.get("prenom"),
                        (String) obj.get("tel"),
                        (String) obj.get("role")

                );

                listUsers.add(user);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listUsers;
    } */
    /*
    
    
    
    //add
    public void ajout_user(Utilisateur user){
        String url=Statics.BASE_URL+"/addUtilisateurJSON?nom="+user.getNom()+"&prenom="+user.getPrenom()
                +"&email="+user.getEmail()+"&tel="+user.getTel()+"&adresse="+user.getAdresse()+"&mdp="+user.getMdp()
                +"&dateNaissance"+user.getDateNaissance()+"&idRole="+user.getRole().getDescription();
        
        req.setUrl(url);
        req.addResponseListener((e)->{
        String str = new String(req.getResponseData());
            System.out.println("data =="+str);
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    */
    
    //Read
    /*
    public ArrayList<Utilisateur>affichageUser(){
        ArrayList<Utilisateur> list = new ArrayList<>();
        String url= Statics.BASE_URL+"/UsersListeJSON";
        req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp;
                jsonp = new JSONParser();
                try{
                    Map<String,Object> mapUtilisateurs = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    list<Map<String,Object>> listofMaps =(List<Map<String,Object>>) mapUtilisateurs.get("root");
                    
                    for (Map<String,Object> obj : listofMaps){
                        Utilisateur u = new Utilisateur();
                        float id = Float.parseFloat(obj.get("id").toString());
                        String nom = obj.get("nom").toString();
                        String prenom = obj.get("prenom").toString();
                        String email = obj.get("email").toString();
                        String tel = obj.get("tel").toString();
                        String adresse = obj.get("adresse").toString();
                        String mdp = obj.get("mdp").toString();
                        
                        Role role = (Role) obj.get("role");
                        u.setId((int)id);
                        u.setNom(nom);
                        u.setPrenom(prenom);
                        u.setEmail(email);
                        u.setTel(tel);
                        u.setAdresse(adresse);
                        u.setMdp(mdp);
                       // u.setDateNaissance(dateNaissance);
                        u.setRole(role);
                        
//               String DateConverter= obj.get("dateNaissance").toString().substring(obj.get("dateNaissance").toString().indexOf("timestamp")+10, obj.get("obj").toString().lastIndexOf(")"));
//               Date currentTime = new Date(Double.valueOf(DateConverter).longValue()*1000);
//                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//                    String dateString = formatter.format(currentTime);
//                    u.setDateNaissance(dateString);
                    
                    list.add(u);
                    
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
              
            }
        });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return list;

    }*/
    
    //delete
    public boolean deleteUtilisateur(int id) {
        String url = Statics.BASE_URL +"/deleteUserJSON/"+id;
        
        req.setUrl(url);
        req.setPost(false);
        
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
    public boolean modifierUtilisateur(Utilisateur user, String datenaissance, String role) {
        String url = Statics.BASE_URL +"/updateUserJSON/"+ user.getId()+"?nom="+user.getNom()+"&prenom="+user.getPrenom()
                +"&email="+user.getEmail()+"&tel="+user.getTel()+"&adresse="+user.getAdresse()+"&mdp="+user.getMdp()
                +"&datenaissance="+datenaissance+"&role="+role;
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

