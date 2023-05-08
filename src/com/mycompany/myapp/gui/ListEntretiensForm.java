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
import com.mycompany.myapp.entities.Entretien;
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.CandidatureServices;
import com.mycompany.myapp.services.EntretiensServices;
import com.mycompany.myapp.utils.Statics;
import java.util.ArrayList;

/**
 *
 * @author HP I5
 */
public class ListEntretiensForm extends Form{
     public static Entretien selectedEntretien;
     ListEntretiensForm current;
     Utilisateur user = Statics.USER;
  
     
      public ListEntretiensForm(Form previous) {
        current = this;
        /* recruteur */
        
      
        
        
        
        setTitle("Vos entretiens");
        setLayout(BoxLayout.y());

        
        ArrayList<Entretien> entretiens = EntretiensServices.getInstance().getAllEntretiens(user.getRole().getDescription(), user.getId());
        for (Entretien e : entretiens) {
            addElement(e);
            
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        show();
       
    }

    
    public void addElement(Entretien e) {
        Button btn = new Button();
        if(user.getRole().getDescription().equals("Recruteur")) {
            
       
       btn = new Button("" + e.getCandidature().getIdOffre().getPoste() + " - " +
                e.getCandidature().getIdCandidat().getNom() + " " + e.getCandidature().getIdCandidat().getPrenom());
       }
        else {
        btn = new Button("" + e.getCandidature().getIdOffre().getPoste() + " - " +
                e.getCandidature().getIdOffre().getEntreprise());
        }
        add(btn);
        btn.addActionListener(event -> 
        {   selectedEntretien = e;
           if(user.getRole().getDescription().equals("Recruteur")) {
            new DetailsEntretienForm(current, selectedEntretien).show();
           } else {
           new CandidatDetailsEntretienForm(current, selectedEntretien).show();
           }
        
        });
        
     
    }
 
    
}
