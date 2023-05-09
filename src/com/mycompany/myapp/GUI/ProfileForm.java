/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;
/**
 *
 * @author ezine
 */
public class ProfileForm extends Form {
    public ProfileForm(Resources res, int id){
        Utilisateur u = ServiceUtilisateur.getInstance().showUser(id);
        setTitle("Mon profil!");
        setLayout(BoxLayout.y());
        //Utilisateur u = new Utilisateur();
        Label nom =(new Label("nom : " + u.getNom()));
        Label prenom =(new Label("Prénom: "+ u.getPrenom()));
        Label Email =(new Label("Email : "+ u.getEmail()));
        Label tel = new Label("Tel : "+ u.getTel());
        Label adresse = new Label( "Adresse : " + u.getAdresse());
        Label mdp = new Label("Mot de passe : "+u.getMdp());
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
         String formattedDate = dateFormat.format( u.getDateNaissance());
        Label datenaissance = new Label("Date de naissance : " + formattedDate );
       // Label datenaissance = new Label("Date de naissance : "+u.getDateNaissance().toString());
        Label role = new Label("Role : Candidat" );
        System.out.println(u.getDateNaissance());
            
      Button Modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        Modifier.addActionListener(e -> new ModifProfileForm(res, id).show());
        Modifier.setUIID("Link");
        Supprimer.addActionListener((evt) -> {
             Command ok = new Command("Oui");
                Command cancel = new Command("Non");
                Command result = Dialog.show("Confirmation", "Etes-vous sures de vouloir annuler cette candidature?", ok, cancel);
                
                if (result == ok) {
             ServiceUtilisateur.getInstance().deleteUtilisateur(id);
             SignUpForm f = new SignUpForm(res);
             f.show();
                }
        });
        
        
        
        addAll(nom, prenom, Email, tel, adresse, mdp, datenaissance, role, Modifier, Supprimer);
    }
}

