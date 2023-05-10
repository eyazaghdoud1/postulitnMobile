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
import com.mycomany.entities.Guidesentretiens;
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
public class GuidesentretiensServices {
    
    
    public Guidesentretiens c ;
    
    
    
    
    
      public static GuidesentretiensServices instance = null ;
    
    public static boolean resultOk = true;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static GuidesentretiensServices getInstance() {
        if(instance == null )
            instance = new GuidesentretiensServices();
        return instance ;
    }
    
    
    
    public GuidesentretiensServices() {
        req = new ConnectionRequest();
        
    }
    
    
    //ajout 
    public void ajoutGuide(Guidesentretiens guidesentretiens) {
        
        String url =Statics.BASE_URL+"/addGuideJSON?domaine="+guidesentretiens.getDomaine()+"&specialite="+guidesentretiens.getSpecialite()+"&support="+guidesentretiens.getSupport(); // aa sorry n3adi getId lyheya mech ta3 user ta3 reclamation
        
        req.setUrl(url);
        req.addResponseListener((e) -> {
            
            String str = new String(req.getResponseData());//Reponse json hethi lyrinaha fi navigateur 9bila
            System.out.println("data == "+str);
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
        
    }
    
    
    //affichage
    
    public ArrayList<Guidesentretiens>affichageGuides() {
        ArrayList<Guidesentretiens> result = new ArrayList<>();
        
        String url = Statics.BASE_URL+"/allGuides";
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapGuidesentretiens = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapGuidesentretiens.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                        Guidesentretiens ge = new Guidesentretiens();
                        
                        //dima id fi codename one float 5outhouha
                        float Idguide = Float.parseFloat(obj.get("idguide").toString());
                        
                        String domaine = obj.get("domaine").toString();
                        
                        String specialite = obj.get("specialite").toString();
                        String support = obj.get("support").toString();
                        
                        ge.setIdguide((int)Idguide);
                        ge.setDomaine(domaine);
                        ge.setSpecialite(specialite);
                        ge.setSupport(support);
                        /*
                        //Date 
                        String DateConverter =  obj.get("date").toString().substring(obj.get("date").toString().indexOf("timestamp") + 10 , obj.get("date").toString().lastIndexOf("}"));
                        
                        Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                        
                        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                        String dateString = formatter.format(currentTime);
                        re.setDate(dateString);
                        */
                        //insert data into ArrayList result
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
    
    
    
   
    
  public Guidesentretiens parseGuides(String jsonText) {
        
        Guidesentretiens c = null;
        try {
            
            JSONParser j = new JSONParser();
            Map<String, Object> obj = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            c = new Guidesentretiens();
             float idGuide = Float.parseFloat(obj.get("idguide").toString());
                        
                        String domaine = obj.get("domaine").toString();
                         String specialite = obj.get("specialite").toString();
                        String support = obj.get("support").toString();
                       
                            
                            
                            
                        c.setIdguide((int)idGuide);
                        c.setDomaine(domaine);
                        c.setSpecialite(specialite);
                        c.setSupport(support);
                         
                          }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
             return c;            
    }

    
  
    public Guidesentretiens showGuides (int id){
        
         String url = Statics.BASE_URL+"/Guide/"+id;
        req.setUrl(url);
        req.setPost(false);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
               
                
                c = parseGuides(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return c;
        
            
    }
    
  
  
  
    //Delete 
    public boolean deleteGuide(int Idguide ) {
        String url = Statics.BASE_URL +"/deleteGuideJSON?id="+Idguide;
        
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
    public boolean modifierGuide(Guidesentretiens guidesentretiens) {
        String url = Statics.BASE_URL +"/updateGuideJSON?id="+guidesentretiens.getIdguide()+"&domaine="+guidesentretiens.getDomaine()+"&specialite="+guidesentretiens.getSpecialite()+"&support="+guidesentretiens.getSupport();
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
