/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Guidesentretiens;
import com.mycompany.services.GuidesentretiensServices;
/**
 *
 * @author ezine
 */
public class GuideForm extends Form {
    
    public GuideForm (Resources res, int id){
      /*  Guidesentretiens c = GuidesentretiensServices.getInstance().DetailGuide(id);
        setTitle("Les details du guide!");
        setLayout(BoxLayout.y());
        //Utilisateur u = new Utilisateur();
        Label domaine =(new Label("domaine : " + c.getDomaine()));
        Label specialite =(new Label("Specialite: "+ c.getSpecialite()));
        Label support =(new Label("Support : "+ c.getSupport()));
        
        
            
      Button Modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
      
        
        addAll(domaine, specialite, support,   Modifier, Supprimer);*/
    }
}