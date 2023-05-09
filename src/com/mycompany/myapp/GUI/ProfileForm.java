/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
/**
 *
 * @author ezine
 */
public class ProfileForm extends Form {
    public ProfileForm(Resources res){
        setTitle("Mon profil!");
        setLayout(BoxLayout.y());
        Utilisateur u = new Utilisateur();
        Label nom =(new Label("nom : " + u.getNom()));
        Label prenom =(new Label("Prénom: "+ u.getPrenom()));
        Label Email =(new Label("Email : "+ u.getEmail()));
        Label tel = new Label("Tel : "+ u.getTel());
        Label adresse = new Label( "Adresse : " + u.getAdresse());
        Label mdp = new Label("Mot de passe : "+u.getMdp());
        Label datenaissance = new Label("Date de naissance : "+u.getDateNaissance());
        Label role = new Label("Role : Candidat" );
            
      Button Modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        Modifier.addActionListener(e -> new ModifProfileForm(res).show());
        Modifier.setUIID("Link");
        addAll(nom, prenom, Email, tel, adresse, mdp, datenaissance, role, Modifier, Supprimer);
    }
}

