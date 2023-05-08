/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.services.QuizServices;
import java.util.ArrayList;

/**
 *
 * @author HP I5
 */
public class QuizListForm extends Form{
    QuizListForm current;
    public QuizListForm(Form previous) {
        current =this;
        setTitle("Liste des quiz");
        setLayout(BoxLayout.y());

        
        ArrayList<Quiz> quizlist= QuizServices.getInstance().getAllQuiz();
        for (Quiz q : quizlist) {
            addElement(q);
            
        }

          getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
       

    }
    
     public void addElement(Quiz q) {
        Button btn = new Button("Quiz: " + q.getNom());
        add(btn);
        btn.addActionListener(e -> 
        {  
            new PasserQuizForm(current, q).show();
        
        });
        

    }
    
}
