/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Comptes;
import com.mycompany.services.ComptesServices;

/**
 *
 * @author ezine
 */
public class ModifierCompteForm extends Form{
   
    public Comptes u ;
    public ModifierCompteForm(Resources res, int id){
        
        setTitle("Modifier compte!");
        setLayout(BoxLayout.y());
       
        TextField experience= new TextField("", "Experience", 20, TextField.ANY);
        TextField photo= new TextField("", "Photo", 20, TextField.ANY);
        TextField diplome = new TextField("", "Diplome", 20, TextField.EMAILADDR);
        TextField entreprise = new TextField("", "Entreprise", 20, TextField.ANY);
        TextField domaine = new TextField("", "Domaine", 50, TextField.ANY);
         TextField poste = new TextField("", "Poste", 20, TextField.PASSWORD);
         
         
         
         
         Picker dateDiplome = new Picker();
         dateDiplome.setType(Display.PICKER_TYPE_DATE);
       
        
        experience.setSingleLineTextArea(false);
        photo.setSingleLineTextArea(false);
        diplome.setSingleLineTextArea(false);
        entreprise.setSingleLineTextArea(false);
        domaine.setSingleLineTextArea(false);
        poste.setSingleLineTextArea(false);
        Button modif = new Button("Modifier");
        
       
        //Label alreadHaveAnAccount = new Label("Already have an account?");
        
        
        
       
        
    //    signUp.requestFocus();
        modif.addActionListener((e) -> {
        u = new Comptes();
        u.setIdCompte(id);
       
        u.setExperience(experience.getText());
        u.setPhoto(photo.getText());
        u.setDiplome(diplome.getText());
        u.setEntreprise(entreprise.getText());
        u.setDomaine(domaine.getText());
        u.setPoste(poste.getText());
        u.setIdUtilisateur(1);
     
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        String formattedDate = dateFormat.format(dateDiplome.getDate());
        ComptesServices.getInstance().modifierCompte(u, formattedDate);
        Dialog.show("Success","account is saved","OK",null);
            //new SignInForm(res).show();
        });
        
         addAll(experience, photo, diplome, entreprise, domaine, poste, dateDiplome,  modif);
    }
    }