/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.company.entities.Commentaires;

/**
 *
 * @author Users
 */
public class DetailsCommentaires extends Form {
 public DetailsCommentaires(Resources res){
   setTitle("Commentaire !");
   setLayout(BoxLayout.y());
     Commentaires c = new Commentaires(); 
        Label commentaire =(new Label("commentaire : " + c.getContenu()));
        Button Modifier = new Button("Modifier");
        Button Supprimer = new Button("Supprimer");
        Modifier.addActionListener(e -> new UpdateCommentare(res).show());
        Modifier.setUIID("Link");
        addAll(commentaire, Modifier, Supprimer);
    }
 }
    
    

