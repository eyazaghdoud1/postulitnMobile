/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import static com.codename1.push.PushContent.setTitle;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.company.entities.ProjetFreelance;
        /**
 *
 * @author Users
 */
public class DetailsProjets extends Form {
    
      public DetailsProjets(Resources res){
        setTitle("Details Projet");
        setLayout(BoxLayout.y());
        ProjetFreelance pf = new ProjetFreelance();
        Label nom =(new Label("nom : " + pf.getNom()));
        Label Theme =(new Label("Theme: "+ pf.getTheme()));
        Label Description =(new Label("Description : "+ pf.getDescription()));
        Label Duree = new Label("Duree : "+ pf.getDuree());
        Label datedebut = new Label("Date debut : "+pf.getDateDebut());
        Label datefin = new Label("Date de fin : "+ pf.getDateFin());
       
            
      Button Modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        Modifier.addActionListener(e -> new UpdateProjet(res).show());
        Modifier.setUIID("Link");
        addAll(nom,Theme, Description, Duree, datedebut, datefin, Modifier, Supprimer);
    }
    
}
