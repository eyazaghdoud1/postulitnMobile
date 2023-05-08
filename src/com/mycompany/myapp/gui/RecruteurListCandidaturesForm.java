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
import com.mycompany.myapp.services.CandidatureServices;
import java.util.ArrayList;

/**
 *
 * @author HP I5
 */
public class RecruteurListCandidaturesForm extends Form{
     public static Candidature recruteurSelectedCandidature;
     
      public RecruteurListCandidaturesForm(Form previous) {
        setTitle("Liste des candidatures");
        setLayout(BoxLayout.y());

        
        ArrayList<Candidature> candidatures = CandidatureServices.getInstance().getAllCandidatures("Recruteur", 99);
        for (Candidature c : candidatures) {
            addElement(c);
            
        }

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
       

    }


    public void addElement(Candidature cand) {
        Button btn = new Button("" + cand.getIdCandidat().getNom() + " " + cand.getIdCandidat().getPrenom());
      
        add(btn);
        btn.addActionListener(e -> 
        {   recruteurSelectedCandidature = cand;
            new RecruteurDetailsCandidatureForm(this, cand).show();
        
        });
        

    }
    
}
