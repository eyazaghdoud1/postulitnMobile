/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

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
import com.mycompany.myapp.entities.Role;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;

/**
 *
 * @author ezine
 */
public class ModifProfileForm extends Form{
    public ModifProfileForm(Resources res){
        setTitle("Modifier profil!");
        setLayout(BoxLayout.y());
        Role r = new Role();
        TextField nom= new TextField("", "Nom", 20, TextField.ANY);
        TextField prenom= new TextField("", "Prénom", 20, TextField.ANY);
        TextField email = new TextField("", "E-Mail", 20, TextField.EMAILADDR);
        TextField tel = new TextField("", "Tel", 20, TextField.ANY);
        TextField adresse = new TextField("", "Adresse", 50, TextField.ANY);
         TextField mdp = new TextField("", "Mot de passe", 20, TextField.PASSWORD);
         Picker datenaissance = new Picker();
         datenaissance.setType(Display.PICKER_TYPE_DATE);
         ComboBox<String> combo = new ComboBox<>("Candidat", "Recruteur");
        
        nom.setSingleLineTextArea(false);
        prenom.setSingleLineTextArea(false);
        email.setSingleLineTextArea(false);
        tel.setSingleLineTextArea(false);
        adresse.setSingleLineTextArea(false);
        mdp.setSingleLineTextArea(false);
        Button modif = new Button("Modifier");
        
      modif.addActionListener(e -> new ProfileForm(res).show());
        modif.setUIID("Link");
        
       
        //Label alreadHaveAnAccount = new Label("Already have an account?");
        
        Utilisateur u = new Utilisateur();
        u.setSalt("1234");
        u.setCode("0000");
        u.setNom(nom.getText());
        u.setPrenom(prenom.getText());
        u.setEmail(email.getText());
        u.setTel(tel.getText());
        u.setAdresse(adresse.getText());
        u.setMdp(mdp.getText());
        combo.addActionListener((evt) -> {
            String selectedRole = combo.getSelectedItem();
            r.setDescription(selectedRole);
            u.setRole(r);
        });
       
        
    //    signUp.requestFocus();
        modif.addActionListener((e) -> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(datenaissance.getDate());
        ServiceUtilisateur.getInstance().modifierUtilisateur(u, formattedDate);
        Dialog.show("Success","account is saved","OK",null);
            //new SignInForm(res).show();
        });
        
         addAll(nom, prenom, email, tel, adresse, mdp, datenaissance, combo, modif);
    }
    }
    

