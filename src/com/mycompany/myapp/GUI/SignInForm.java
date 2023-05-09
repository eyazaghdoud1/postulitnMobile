/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.GUI;

import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.ServiceUtilisateur;

/**
 *
 * @author ezine
 */
public class SignInForm extends Form{
      public SignInForm(Resources res) {
          setTitle("Sign In");
          setLayout(BoxLayout.y());
          
        TextField email = new TextField("", "email", 20, TextField.ANY);
        TextField mdp = new TextField("", "mdp", 20, TextField.PASSWORD);
        email.setSingleLineTextArea(false);
        mdp.setSingleLineTextArea(false);
        Button signIn = new Button("Sign In");
        Button signUp = new Button("Sign Up");
          Utilisateur u = new Utilisateur();
          u.setEmail(email.getText());
          u.setMdp(mdp.getText());
        signIn.addActionListener((evt) -> {
             ServiceUtilisateur.getInstance().signin(u);
           
              Dialog.show("Success","You are signed in","OK",null);
             
        });
         signUp.addActionListener(e -> new SignUpForm(res).show());
        signUp.setUIID("Link");
        //Button signUp = new Button("Sign Up");
        add(email);
        add(mdp);
        add(signIn);
        add(signUp);
      }

    
}
