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
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.entities.QuizQuestion;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP I5
 */
public class QuizQuestionsService {
        public ArrayList<QuizQuestion> quizquestions;

    public static QuizQuestionsService instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    private QuizQuestionsService() {
        req = new ConnectionRequest();
    }

    public static QuizQuestionsService getInstance() {
        if (instance == null) {
            instance = new QuizQuestionsService();
        }
        return instance;
    }

    public ArrayList<QuizQuestion> parseQuestions(String jsonText) {
        try {
            quizquestions = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> quizQuestionsJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) quizQuestionsJson.get("root");

            for (Map<String, Object> obj : list) {
                QuizQuestion q = new QuizQuestion();
                float id = Float.parseFloat(obj.get("id").toString());
                q.setId((int) id);
                q.setQuestion(obj.get("question").toString());
                q.setOption1(obj.get("option1").toString());
                q.setOption2(obj.get("option2").toString());
                q.setOption3(obj.get("option3").toString());
                q.setReponse(obj.get("reponse").toString());
                Map<String, Object> quiz = (Map<String, Object>) obj.get("idquiz");
                Quiz qu= new Quiz();
                qu.setId((int) Float.parseFloat(quiz.get("id").toString()));
                qu.setNom(quiz.get("nom").toString());
                q.setIdQuiz(qu);

                quizquestions.add(q);

            }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return quizquestions;
    }

     public ArrayList<QuizQuestion> getAllQuestions(int idQuiz) {

        String url = Statics.BASE_APP_URL + "quizJSON/" + idQuiz;

        req.setUrl(url);

        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                quizquestions = parseQuestions(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);

        return quizquestions;

    }
    
}
