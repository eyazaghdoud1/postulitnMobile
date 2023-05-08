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
import com.mycompany.myapp.entities.Entretien;
import com.mycompany.myapp.services.CandidatureServices;
import com.mycompany.myapp.services.EntretiensServices;
import java.text.SimpleDateFormat;

/**
 *
 * @author HP I5
 */
public class DetailsEntretienForm extends Form{
    DetailsEntretienForm current;
    public DetailsEntretienForm(Form previous, Entretien e) {
        setTitle("Détails entretien");
        setLayout(BoxLayout.y());
        
        current= this;
        
        Container cont1 = new Container(new FlowLayout(Component.CENTER));
        Label posteLabel = new Label("Poste: " + e.getCandidature().getIdOffre().getPoste());
        cont1.add(posteLabel);
        Container cont2 = new Container(new FlowLayout(Component.CENTER));
        Label candidatLabel = new Label("Candidat: " + e.getCandidature().getIdCandidat().getNom() + " " 
                + e.getCandidature().getIdCandidat().getPrenom());
        cont2.add(candidatLabel);
        addAll(cont1, cont2);
        Button candBtn = new Button("Voir la candidature"); 
        add(candBtn);
        candBtn.addActionListener((evt) -> {
           RecruteurDetailsCandidatureForm f = new RecruteurDetailsCandidatureForm(current, e.getCandidature());
           f.show();
        });
        
        Label typeLabel = new Label("Type: " + e.getType());
          SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
          String formattedDate = dateFormat.format( e.getDate());
        Label dateLabel = new Label("Date: " + formattedDate );
        Label heureLabel = new Label("Horaire: " + e.getHeure());
        if (e.getType().equals("Présentiel")) {
        Label lieuLabel = new Label("Date: " + e.getDate());
        add(lieuLabel);
        }
        addAll(typeLabel, dateLabel, heureLabel);
        
        Button modifBtn = new Button("Modifier");
        Button suppBtn = new Button("Supprimer");
        modifBtn.addActionListener((evt) -> {
           UpdateEntretienForm f = new UpdateEntretienForm(current, e, true);
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
                }
                ListEntretiensForm f = new ListEntretiensForm(current);
                f.show();
            }
        });
        addAll(modifBtn, suppBtn);
        
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, event -> previous.showBack());
        show();
    }
    
}
