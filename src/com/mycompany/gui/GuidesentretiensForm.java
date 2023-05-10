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
import java.text.SimpleDateFormat;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
/**
 *
 * @author ezine
 */

public class GuidesentretiensForm extends Form {
    
    public GuidesentretiensForm (Resources res, int id){
        Guidesentretiens c = GuidesentretiensServices.getInstance().showGuides(id);
        setTitle("Mon guide!");
        setLayout(BoxLayout.y());
      
        Label domaine =new Label("domaine : " + c.getDomaine());
        // to change to displying the image
        Label specialite =new Label("specialite: "+ c.getSpecialite());
        Label support =(new Label("support : "+ c.getSupport()));
       
        
        
            System.out.println(c);
      Button Modifier = new Button("Modifier");
     
        Button Supprimer = new Button("Supprimer");
        Supprimer.addActionListener(e-> {
             Command ok = new Command("Oui");
                Command cancel = new Command("Non");
                Command result = Dialog.show("Confirmation", "Etes-vous sures de vouloir supprimer le guide?", ok, cancel);
                if (result == ok) {
           GuidesentretiensServices.getInstance().deleteGuide(id);
                }
        });
      
        
        addAll(domaine, specialite, support,   Modifier, Supprimer);
    }
}