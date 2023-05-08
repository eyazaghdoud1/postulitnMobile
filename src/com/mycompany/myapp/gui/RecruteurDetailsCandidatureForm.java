/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

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
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Entretien;
import com.mycompany.myapp.services.CandidatureServices;
import com.mycompany.myapp.services.EntretiensServices;
import java.util.ArrayList;

/**
 *
 * @author HP I5
 */
public class RecruteurDetailsCandidatureForm extends Form {

    Candidature cand = RecruteurListCandidaturesForm.recruteurSelectedCandidature;

    RecruteurDetailsCandidatureForm current;

    public RecruteurDetailsCandidatureForm(Form previous, Candidature c) {

        current = this;
        setTitle("Détails candidature");
        setLayout(BoxLayout.y());
        cand = c;

        Container cont1 = new Container(new FlowLayout(Component.CENTER));
        Label titleLabel = new Label("Votre offre");
        cont1.add(titleLabel);
        add(cont1);

        Label posteLabel = new Label("Poste: " + c.getIdOffre().getPoste());
        Label entLabel = new Label("Entreprise: " + c.getIdOffre().getEntreprise());
        //Label typeLabel = new Label("Type: " + c.getIdOffre().getType());

        Label lieuLabel = new Label("Lieu: " + c.getIdOffre().getLieu());

        //Container mainCont = new Container(new FlowLayout(CENTER, CENTER));
        add(posteLabel);
        add(entLabel);
        add(lieuLabel);
        //add(typeLabel);
        Label etatLabel = new Label("Etat: " + c.getEtat());
        add(etatLabel);

        // informations candidat
        Container ucont1 = new Container(new FlowLayout(Component.CENTER));
        Label cLabel = new Label("Candidat: " + c.getIdCandidat().getNom() + " " + c.getIdCandidat().getPrenom());
        ucont1.add(cLabel);
        add(ucont1);
        Label emailLabel = new Label("Email: " + c.getIdCandidat().getEmail());
        Label telLabel = new Label("Téléphone: " + c.getIdCandidat().getTel());
        Label adresseLabel = new Label("Adresse: " + c.getIdCandidat().getAdresse());
        addAll(emailLabel, telLabel, adresseLabel);

        // cv
        Button btnCv = new Button("CV");

        btnCv.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DocumentForm df = new DocumentForm(current, "CV", cand);
                df.show();
            }
        });

        // lettre 
        Button btnLettre = new Button("Lettre de motivation");

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
        entretiensButton.addActionListener((evt) -> {
            EntretiensCandidatureForm f = new EntretiensCandidatureForm(current, c);
            f.show();
        });
        //cont.add(entretiensButton);
        //add(cont);

        Container gestionCont = new Container(new FlowLayout(Component.CENTER));
        Label l = new Label("Gérer l'état de la candidature");
        gestionCont.add(l);
        Container gestionCont2 = new Container(new FlowLayout(Component.CENTER));
        if (c.getEtat().equals("Enregistrée")) {
            Button btnValider = new Button("Valider");
            gestionCont2.add(btnValider);
            
            btnValider.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Command ok = new Command("Valider");
                    Command cancel = new Command("Annuler");
                    Command result = Dialog.show("Confirmation", "Le candidat est capable de suivre l'état de sa candidature, continuer?", ok, cancel);
                    if (result == ok) {
                        CandidatureServices.getInstance().validerCandidature(RecruteurListCandidaturesForm.recruteurSelectedCandidature.getId());

                    }
                    RecruteurDetailsCandidatureForm f = new RecruteurDetailsCandidatureForm(current, RecruteurListCandidaturesForm.recruteurSelectedCandidature);
                    f.show();

                }
            });
        } else {
            Button btnValider = new Button("Validée");
            btnValider.setEnabled(false);
            gestionCont2.add(btnValider);
            ArrayList<Entretien> es = EntretiensServices.getInstance().getEntretiensByCandidature("Recruteur", 
                    RecruteurListCandidaturesForm.recruteurSelectedCandidature.getId());
            if (es.size()==2) {
                 Button btnAccepter = new Button("Accepter");
                   btnAccepter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Command ok = new Command("Accepter");
                    Command cancel = new Command("Annuler");
                    Command result = Dialog.show("Confirmation", "Le candidat est capable de suivre l'état de sa candidature, continuer?", ok, cancel);
                    if (result == ok) {
                        CandidatureServices.getInstance().accepterCandidature(RecruteurListCandidaturesForm.recruteurSelectedCandidature.getId());
                         RecruteurListCandidaturesForm f = new RecruteurListCandidaturesForm(current);
                        f.show();
                    }

                }
            });
                 Button btnRefuser = new Button("Refuser");
                   btnRefuser.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Command ok = new Command("Refuser");
                    Command cancel = new Command("Annuler");
                    Command result = Dialog.show("Confirmation", "Le candidat est capable de suivre l'état de sa candidature, continuer?", ok, cancel);
                    if (result == ok) {
                        CandidatureServices.getInstance().refuserCandidature(RecruteurListCandidaturesForm.recruteurSelectedCandidature.getId());
                         RecruteurListCandidaturesForm f = new RecruteurListCandidaturesForm(current);
                        f.show();
                    }
                   
                }
            });
                 gestionCont2.addAll(btnAccepter, btnRefuser);
               
            
            }
        
        }

        addAll(gestionCont, gestionCont2);

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

    }

}
