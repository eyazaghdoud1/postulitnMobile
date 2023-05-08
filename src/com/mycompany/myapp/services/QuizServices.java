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
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP I5
 */
public class QuizServices {

    public ArrayList<Quiz> quizlist;

    public static QuizServices instance = null;
    public boolean resultOK;
    public  int score = 0;
    private ConnectionRequest req;

    private QuizServices() {
        req = new ConnectionRequest();
    }

    public static QuizServices getInstance() {
        if (instance == null) {
            instance = new QuizServices();
        }
        return instance;
    }

    public ArrayList<Quiz> parseQuiz(String jsonText) {
        try {
            quizlist = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> quizListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) quizListJson.get("root");

            for (Map<String, Object> obj : list) {
                Quiz q = new Quiz();
                float id = Float.parseFloat(obj.get("id").toString());
                q.setId((int) id);
                q.setSecteur(obj.get("secteur").toString());
                q.setSpecialite(obj.get("specialite").toString());
                q.setNom(obj.get("nom").toString());

                quizlist.add(q);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return quizlist;
    }

     public ArrayList<Quiz> getAllQuiz() {

        String url = Statics.BASE_APP_URL + "listQuizJSON" ;

        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizlist = parseQuiz(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return quizlist;

    }
     
     public int score(int idquiz, int idcandidat, String rep1, String rep2, String rep3, String rep4, String rep5) {

        String url = Statics.BASE_APP_URL +"passerquizJSON/" + idquiz 
                + "/" + idcandidat
                + "?rep1=" + rep1
                + "&rep2=" + rep2
                + "&rep3=" + rep3
                + "&rep4=" + rep4
                + "&rep5=" + rep5;
                

        
        
        req.setUrl(url);
        req.setPost(false);
       
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                String responseText = new String(req.getResponseData());
                 score = Integer.parseInt(responseText.trim());
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return score;
    }
    
}
