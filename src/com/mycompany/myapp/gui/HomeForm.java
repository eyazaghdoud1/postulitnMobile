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
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.utils.Statics;
import java.io.IOException;

/**
 *
 * @author HP I5
 */
public class HomeForm extends Form {

    public HomeForm() {

        try {
            setTitle("Home");
            setLayout(BoxLayout.y());

            Image img = Image.createImage("/logo.png");
            Label imageLabel = new Label(img);
            Container container = new Container(new FlowLayout(Component.CENTER));
            container.add(imageLabel);
            add(container);

            if (Statics.USER.getRole().getDescription().equals("Recruteur")) {
                Button btnrec = new Button("Recruteur candidatures");
                btnrec.addActionListener(e -> new RecruteurListCandidaturesForm(this).show());
                add(btnrec);

            } else {
                Button btnListCandidatures = new Button("Vos candidatures");
                btnListCandidatures.addActionListener(e -> new ListCandiaturesForm(this).show());
                add(btnListCandidatures);
                Button btn = new Button("Postuler");
                btn.addActionListener(e -> new PostulerForm(this).show());
                add(btn);

                Button btnQuizList = new Button("Quiz");
                btnQuizList.addActionListener(e -> new QuizListForm(this).show());
                add(btnQuizList);

            }

            // Button btnListOffres = new Button("Cherchez des offres");
            //Button btnListProjets = new Button("Projets freelance");
            // Button btnCompte = new Button("Compte");
            Button btnEnt = new Button("Entretiens");
            btnEnt.addActionListener(e -> new ListEntretiensForm(this).show());

            // add(btnListOffres);
            //add(btnListProjets);
            //add(btnCompte);
            //add(btn);
            add(btnEnt);

        } catch (IOException ex) {

        }

    }

}
