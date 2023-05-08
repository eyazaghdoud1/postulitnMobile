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
import com.mycompany.myapp.services.EntretiensServices;
import com.mycompany.myapp.utils.Statics;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 *
 * @author HP I5
 */
public class EntretiensCandidatureForm extends Form {

    EntretiensCandidatureForm current;

    public EntretiensCandidatureForm(Form previous, Candidature c) {

        setTitle("Détails entretiens");
        setLayout(BoxLayout.y());

        current = this;

        Container cont1 = new Container(new FlowLayout(Component.CENTER));
        Label posteLabel = new Label("Poste: " + c.getIdOffre().getPoste());
        cont1.add(posteLabel);
        Container cont2 = new Container(new FlowLayout(Component.CENTER));
        Label candidatLabel = new Label("Entreprise: " + c.getIdOffre().getEntreprise());
        cont2.add(candidatLabel);
        addAll(cont1, cont2);

        ArrayList<Entretien> entretiens = EntretiensServices.getInstance().getEntretiensByCandidature("Candidat", c.getId());
        if (entretiens.size() == 2) {
            int i = 1;
            for (Entretien e : entretiens) {

                Container contEnt = new Container(new FlowLayout(Component.CENTER));
                Label eLabel = new Label("Entretien " + i);
                contEnt.add(eLabel);
                add(contEnt);
                addElement(e);
                if (Statics.USER.getRole().getDescription().equals("Recruteur")) {

                    Button modifBtn = new Button("Modifier");
                    Button suppBtn = new Button("Supprimer");
                    modifBtn.addActionListener((evt) -> {
                        UpdateEntretienForm f = new UpdateEntretienForm(current, e, false);
                        f.show();
                    });

                    suppBtn.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent event) {

                            Command ok = new Command("Oui");
                            Command cancel = new Command("Non");
                            Command result = Dialog.show("Confirmation", "Etes-vous sures de vouloir annuler cet entretien?", ok, cancel);
                            if (result == ok) {
                                EntretiensServices.getInstance().deleteEntretien(e.getId());
                                current.refreshTheme();
                            }
                            
                           
                        }
                    });
                    addAll(modifBtn, suppBtn);

                }
                i++;

            }
        } else if (entretiens.size() == 1) {

            Container contEnt = new Container(new FlowLayout(Component.CENTER));
            Label eLabel = new Label("Entretien ");
            contEnt.add(eLabel);
            add(contEnt);
            addElement(entretiens.get(0));
            if (Statics.USER.getRole().getDescription().equals("Recruteur")) {

                Button modifBtn = new Button("Modifier");
                Button suppBtn = new Button("Supprimer");
                modifBtn.addActionListener((evt) -> {
                    UpdateEntretienForm f = new UpdateEntretienForm(current, entretiens.get(0), false);
                    f.show();
                });

                suppBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent event) {

                        Command ok = new Command("Oui");
                        Command cancel = new Command("Non");
                        Command result = Dialog.show("Confirmation", "Etes-vous sures de vouloir annuler cet entretien?", ok, cancel);
                        if (result == ok) {
                            EntretiensServices.getInstance().deleteEntretien(entretiens.get(0).getId());
                             ListEntretiensForm f = new ListEntretiensForm(current);
                             f.show();
                        }


                    }
                });
                
                 Button newBtn = new Button("Programmer un deuxème entretien");
                        
                        newBtn.addActionListener((evt) -> {
                            NewEntretienForm newF = new NewEntretienForm(current, entretiens.get(0).getCandidature());
                            newF.show();
                        });
                addAll(modifBtn, suppBtn, newBtn);

            }

        } else {
            Container contEnt = new Container(new FlowLayout(Component.CENTER));
            Label eLabel = new Label("Pas d'entretiens programmés.");
            contEnt.add(eLabel);
            add(contEnt);
            if (Statics.USER.getRole().getDescription().equals("Recruteur")) {
                Button newBtn = new Button("Programmer un entretien");
                newBtn.addActionListener((evt) -> {
                            NewEntretienForm newF = new NewEntretienForm(current, RecruteurListCandidaturesForm.recruteurSelectedCandidature);
                            newF.show();
                        });
                add(newBtn);
                 

            }
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, event -> previous.showBack());
        show();
    }

    public void addElement(Entretien e) {
        Label typeLabel = new Label("Type: " + e.getType());
         SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
          String formattedDate = dateFormat.format( e.getDate());
        Label dateLabel = new Label("Date: " + formattedDate );
       
        Label heureLabel = new Label("Horaire: " + e.getHeure());
         addAll(typeLabel, dateLabel, heureLabel);
        if (e.getType().equals("Présentiel")) {
            Label lieuLabel = new Label("Lieu: " + e.getLieu());
            add(lieuLabel);
        }
      

    }

}
