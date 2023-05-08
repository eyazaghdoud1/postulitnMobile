/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.services.CandidatureServices;

/**
 *
 * @author HP I5
 */
public class DetailsCandidatureForm extends Form {

    Candidature cand;

    DetailsCandidatureForm current;

    public DetailsCandidatureForm(Form previous, Candidature c) {

        current = this;
        setTitle("Détails candidature");
        setLayout(BoxLayout.y());
        cand = c;

        Container cont1 = new Container(new FlowLayout(Component.CENTER));
        Label posteLabel = new Label("Poste: " + c.getIdOffre().getPoste());
        cont1.add(posteLabel);

        Label entLabel = new Label("Entreprise: " + c.getIdOffre().getEntreprise());
        //Label typeLabel = new Label("Type: " + c.getIdOffre().getType());

        Label lieuLabel = new Label("Lieu: " + c.getIdOffre().getLieu());
        add(cont1);

        //Container mainCont = new Container(new FlowLayout(CENTER, CENTER));
        add(entLabel);
        add(lieuLabel);
        //add(typeLabel);
        Label etatLabel = new Label("Etat: " + c.getEtat());
        add(etatLabel);

        // cv
        Button btnCv = new Button("CV");
        //add(btnCv);
        btnCv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentForm df = new DocumentForm(current, "CV", cand);
                df.show();
            }
        });

        // lettre 
        Button btnLettre = new Button("Lettre de motivation");
        // add(btnLettre);
        btnLettre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentForm df = new DocumentForm(current, "Lettre de motivation", cand);
                df.show();
            }
        });

        addAll(btnCv, btnLettre);

        //Container cont = new Container(new FlowLayout(Component.CENTER));
        Button entretiensButton = new Button("Entretiens");
        add(entretiensButton);
        
        entretiensButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EntretiensCandidatureForm f = new EntretiensCandidatureForm(current, c);
                f.show();
            }
        });
        //cont.add(entretiensButton);
        //add(cont);

        Container gestionCont = new Container(new FlowLayout(Component.CENTER));
        Label l = new Label("Gérer votre candidature");
        Container gestionCont2 = new Container(new FlowLayout(Component.CENTER));
       
        Button btnModif = new Button("Modifier");
        Button btnSupp = new Button("Supprimer");
         if (c.getEtat()!="Enregistrée") {
             btnModif.setEnabled(false);
             btnSupp.setEnabled(false);
         }
        gestionCont2.addAll(btnModif, btnSupp);
        gestionCont.addAll(l, gestionCont2);
        add(gestionCont);

        btnModif.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UpdateCandidatureForm f = new UpdateCandidatureForm(current);
                f.show();
            }
        });
        btnSupp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Command ok = new Command("Oui");
                Command cancel = new Command("Non");
                Command result = Dialog.show("Confirmation", "Etes-vous sures de vouloir annuler cette candidature?", ok, cancel);
                if (result == ok) {
                    CandidatureServices.getInstance().deleteCandidature(cand.getId());
                }
                ListCandiaturesForm f = new ListCandiaturesForm(current);
                f.show();
            }
        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        show();

    }

}
