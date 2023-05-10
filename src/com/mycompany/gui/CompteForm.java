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
import com.mycomany.entities.Comptes;
import com.mycompany.services.ComptesServices;
import java.text.SimpleDateFormat;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
/**
 *
 * @author ezine
 */
public class CompteForm extends Form {
  
    public CompteForm (Resources res, int id){
        Comptes c = ComptesServices.getInstance().showComptes(id);
        setTitle("Mon profil!");
        setLayout(BoxLayout.y());
      
        Label experience =new Label("experience : " + c.getExperience());
        // to change to displying the image
        Label photo =new Label("Photo: "+ c.getPhoto());
        Label diplome =(new Label("Diplome : "+ c.getDiplome()));
        Label domaine = new Label("Domaine : "+ c.getDomaine());
        Label entreprise = new Label( "Entreprise : " + c.getEntreprise());
        Label poste = new Label("Poste : "+c.getPoste());
        SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
          String formattedDate = dateFormat.format( c.getDateDiplome());
        Label dateDiplome = new Label("Date du diplome : "+formattedDate);
        
            System.out.println(c);
      Button Modifier = new Button("Modifier");
      Modifier.addActionListener(e->{
        ModifierCompteForm f = new ModifierCompteForm(res, c.getIdCompte());
        f.show();
      });
      
     
        Button Supprimer = new Button("Supprimer");
        Supprimer.addActionListener(e-> {
             Command ok = new Command("Oui");
                Command cancel = new Command("Non");
                Command result = Dialog.show("Confirmation", "Etes-vous sures de vouloir supprimer votre compte?", ok, cancel);
                if (result == ok) {
           ComptesServices.getInstance().deleteCompte(id);
                }
        });
        
      
        
        addAll(experience, photo, diplome, domaine, entreprise, poste, dateDiplome,  Modifier, Supprimer);
    }
}