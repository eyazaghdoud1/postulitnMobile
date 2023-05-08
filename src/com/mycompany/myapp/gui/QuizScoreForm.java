/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Quiz;

/**
 *
 * @author HP I5
 */
public class QuizScoreForm extends Form{

    public QuizScoreForm(Quiz quiz,int score) {
        setTitle("Quiz score");
        setLayout(new FlowLayout(CENTER, CENTER));
        
         Container cont = new Container(new FlowLayout(CENTER, CENTER));
         Label l1 = new Label("Quiz: " + quiz.getNom());
         cont.add(l1);
         add(cont);
         Container cont2 = new Container(new FlowLayout(CENTER, CENTER));
         Label l2 = new Label("Secteur: " + quiz.getSecteur());
         cont2.add(l2);
         add(cont2);
         Container cont3 = new Container(new FlowLayout(CENTER, CENTER));
         Label l3 = new Label("Spécilaité: " + quiz.getSpecialite());
           cont2.add(l3);
         add(cont3);
         
          Container cont4 = new Container(new FlowLayout(CENTER, CENTER));
         Label l4 = new Label("Votre score est: " + score + "/5");
         cont4.add(l4);
         add(cont4);
         

         Button btnRetour = new Button("Retourner à la liste des quiz");
         btnRetour.addActionListener((evt) -> {
             QuizListForm f = new QuizListForm(this);
             f.show();
         });
         add(btnRetour);
         
        
        
        
    }
    
    
}
