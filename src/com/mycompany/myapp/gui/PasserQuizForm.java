/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Style;
import com.mycompany.myapp.entities.Quiz;
import com.mycompany.myapp.entities.QuizQuestion;
import com.mycompany.myapp.services.QuizQuestionsService;
import com.mycompany.myapp.services.QuizServices;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;


/**
 *
 * @author HP I5
 */
public class PasserQuizForm extends Form {

    String rep1, rep2, rep3, rep4, rep5;
    int score=0;
    public PasserQuizForm(Form previous, Quiz quiz) {
        setTitle("Quiz");
        setLayout(BoxLayout.y());

        ArrayList<QuizQuestion> q = QuizQuestionsService.getInstance().getAllQuestions(quiz.getId());
        
        /** adding the elements ***/
         TextArea q1 = new TextArea(q.get(0).getQuestion());
         q1.setEditable(false);
         Style style1 = q1.getStyle();
         style1.setFgColor(0x000000);

       Container radioContainer1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q1rb1 = new RadioButton("A: " + q.get(0).getOption1());
        RadioButton q1rb2 = new RadioButton("B: " +q.get(0).getOption2());
        RadioButton q1rb3 = new RadioButton("C: " +q.get(0).getOption3());

        radioContainer1.add(q1rb1);
        radioContainer1.add(q1rb2);
        radioContainer1.add(q1rb3);

        ButtonGroup radioGroup1 = new ButtonGroup(q1rb1, q1rb2, q1rb3);
        add(q1);
        add(radioContainer1);
        
        TextArea q2 = new TextArea(q.get(1).getQuestion());
         q2.setEditable(false);
         Style style2 = q2.getStyle();
         style2.setFgColor(0x000000);

       Container radioContainer2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q2rb1 = new RadioButton("A: " + q.get(1).getOption1());
        RadioButton q2rb2 = new RadioButton("B: " +q.get(1).getOption2());
        RadioButton q2rb3 = new RadioButton("C: " +q.get(1).getOption3());

        radioContainer2.add(q2rb1);
        radioContainer2.add(q2rb2);
        radioContainer2.add(q2rb3);

        ButtonGroup radioGroup2 = new ButtonGroup(q2rb1, q2rb2, q2rb3);
        add(q2);
        add(radioContainer2);
        
         TextArea q3 = new TextArea(q.get(2).getQuestion());
         q3.setEditable(false);
         Style style3 = q3.getStyle();
         style3.setFgColor(0x000000);

       Container radioContainer3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q3rb1 = new RadioButton("A: " + q.get(2).getOption1());
        RadioButton q3rb2 = new RadioButton("B: " +q.get(2).getOption2());
        RadioButton q3rb3 = new RadioButton("C: " +q.get(2).getOption3());

        radioContainer3.add(q3rb1);
        radioContainer3.add(q3rb2);
        radioContainer3.add(q3rb3);

        ButtonGroup radioGroup3 = new ButtonGroup(q3rb1, q3rb2, q3rb3);
        add(q3);
        add(radioContainer3);
        
         TextArea q4 = new TextArea(q.get(3).getQuestion());
         q4.setEditable(false);
         Style style4 = q4.getStyle();
         style4.setFgColor(0x000000);

       Container radioContainer4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q4rb1 = new RadioButton("A: " + q.get(3).getOption1());
        RadioButton q4rb2 = new RadioButton("B: " +q.get(3).getOption2());
        RadioButton q4rb3 = new RadioButton("C: " +q.get(3).getOption3());

        radioContainer4.add(q4rb1);
        radioContainer4.add(q4rb2);
        radioContainer4.add(q4rb3);

        ButtonGroup radioGroup4 = new ButtonGroup(q4rb1, q4rb2, q4rb3);
        add(q4);
        add(radioContainer4);
        
        
         TextArea q5 = new TextArea(q.get(4).getQuestion());
         q5.setEditable(false);
         Style style5 = q5.getStyle();
         style5.setFgColor(0x000000);

       Container radioContainer5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q5rb1 = new RadioButton("A: " + q.get(4).getOption1());
        RadioButton q5rb2 = new RadioButton("B: " +q.get(4).getOption2());
        RadioButton q5rb3 = new RadioButton("C: " +q.get(4).getOption3());

        radioContainer5.add(q5rb1);
        radioContainer5.add(q5rb2);
        radioContainer5.add(q5rb3);

        ButtonGroup radioGroup5 = new ButtonGroup(q5rb1, q5rb2, q5rb3);
        add(q5);
        add(radioContainer5);

        
        /******************************/

        
        Button saveBtn = new Button("Submit");
        add(saveBtn);
        saveBtn.addActionListener((evt) -> {
            switch (radioGroup1.getSelectedIndex()) {
                case 0:
                    rep1="A";
                    break;
                case 1:
                    rep1="B";
                    break;
                default:
                    rep1="C";
                    break;
            }
             switch (radioGroup2.getSelectedIndex()) {
                case 0:
                    rep2="A";
                    break;
                case 1:
                    rep2="B";
                    break;
                default:
                    rep2="C";
                    break;
            }
              switch (radioGroup3.getSelectedIndex()) {
                case 0:
                    rep3="A";
                    break;
                case 1:
                    rep3="B";
                    break;
                default:
                    rep3="C";
                    break;
            }
               switch (radioGroup4.getSelectedIndex()) {
                case 0:
                    rep4="A";
                    break;
                case 1:
                    rep4="B";
                    break;
                default:
                    rep4="C";
                    break;
            }
                switch (radioGroup5.getSelectedIndex()) {
                case 0:
                    rep5="A";
                    break;
                case 1:
                    rep5="B";
                    break;
                default:
                    rep5="C";
                    break;
            }
         
                score = QuizServices.getInstance().score(quiz.getId(), Statics.USER.getId(), rep1, rep2, rep3, rep4, rep5);
                
                QuizScoreForm f = new QuizScoreForm(quiz, score);
                f.show();
                
            
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
  

    public void addElements(ArrayList<QuizQuestion> q) {
         TextArea q1 = new TextArea(q.get(0).getQuestion());
         q1.setEditable(false);
         Style style1 = q1.getStyle();
         style1.setFgColor(0x000000);

       Container radioContainer1 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q1rb1 = new RadioButton("A: " + q.get(0).getOption1());
        RadioButton q1rb2 = new RadioButton("B: " +q.get(0).getOption2());
        RadioButton q1rb3 = new RadioButton("C: " +q.get(0).getOption3());

        radioContainer1.add(q1rb1);
        radioContainer1.add(q1rb2);
        radioContainer1.add(q1rb3);

        ButtonGroup radioGroup1 = new ButtonGroup(q1rb1, q1rb2, q1rb3);
        add(q1);
        add(radioContainer1);
        
        TextArea q2 = new TextArea(q.get(1).getQuestion());
         q2.setEditable(false);
         Style style2 = q2.getStyle();
         style2.setFgColor(0x000000);

       Container radioContainer2 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q2rb1 = new RadioButton("A: " + q.get(1).getOption1());
        RadioButton q2rb2 = new RadioButton("B: " +q.get(1).getOption2());
        RadioButton q2rb3 = new RadioButton("C: " +q.get(1).getOption3());

        radioContainer2.add(q2rb1);
        radioContainer2.add(q2rb2);
        radioContainer2.add(q2rb3);

        ButtonGroup radioGroup2 = new ButtonGroup(q2rb1, q2rb2, q2rb3);
        add(q2);
        add(radioContainer2);
        
         TextArea q3 = new TextArea(q.get(2).getQuestion());
         q3.setEditable(false);
         Style style3 = q3.getStyle();
         style3.setFgColor(0x000000);

       Container radioContainer3 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q3rb1 = new RadioButton("A: " + q.get(2).getOption1());
        RadioButton q3rb2 = new RadioButton("B: " +q.get(2).getOption2());
        RadioButton q3rb3 = new RadioButton("C: " +q.get(2).getOption3());

        radioContainer3.add(q3rb1);
        radioContainer3.add(q3rb2);
        radioContainer3.add(q3rb3);

        ButtonGroup radioGroup3 = new ButtonGroup(q3rb1, q3rb2, q3rb3);
        add(q3);
        add(radioContainer3);
        
         TextArea q4 = new TextArea(q.get(3).getQuestion());
         q4.setEditable(false);
         Style style4 = q4.getStyle();
         style4.setFgColor(0x000000);

       Container radioContainer4 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q4rb1 = new RadioButton("A: " + q.get(3).getOption1());
        RadioButton q4rb2 = new RadioButton("B: " +q.get(3).getOption2());
        RadioButton q4rb3 = new RadioButton("C: " +q.get(3).getOption3());

        radioContainer4.add(q4rb1);
        radioContainer4.add(q4rb2);
        radioContainer4.add(q4rb3);

        ButtonGroup radioGroup4 = new ButtonGroup(q4rb1, q4rb2, q4rb3);
        add(q4);
        add(radioContainer4);
        
        
         TextArea q5 = new TextArea(q.get(4).getQuestion());
         q5.setEditable(false);
         Style style5 = q5.getStyle();
         style5.setFgColor(0x000000);

       Container radioContainer5 = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        RadioButton q5rb1 = new RadioButton("A: " + q.get(4).getOption1());
        RadioButton q5rb2 = new RadioButton("B: " +q.get(4).getOption2());
        RadioButton q5rb3 = new RadioButton("C: " +q.get(4).getOption3());

        radioContainer5.add(q5rb1);
        radioContainer5.add(q5rb2);
        radioContainer5.add(q5rb3);

        ButtonGroup radioGroup5 = new ButtonGroup(q5rb1, q5rb2, q5rb3);
        add(q5);
        add(radioContainer5);

    }

}
