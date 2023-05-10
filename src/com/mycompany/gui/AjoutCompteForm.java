/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycomany.entities.Comptes;
import com.mycompany.services.ComptesServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author HP I5
 */
public class AjoutCompteForm extends Form {

    AjoutCompteForm current;
    Comptes e ;

    public AjoutCompteForm(Resources res) {

        setTitle("ajoutez un compte");
        setLayout(BoxLayout.y());

        current = this;

        Container cn = new Container(new FlowLayout(CENTER));
        Label experienceLabel = new Label("Experience");
        TextField experienceTf = new TextField();

        add(experienceLabel);
         add(experienceTf);
        
        Label photoLabel = new Label("Photo ");
        TextField photoTf = new TextField();
        add(photoLabel);
        add(photoTf);
        
        
         Label diplomeLabel = new Label("Diplome ");
        TextField diplomeTf = new TextField();
        add(diplomeLabel);
        add(diplomeTf);
        
         Label entrepriseLabel = new Label("Entreprise ");
        TextField entrepriseTf = new TextField();
        add(entrepriseLabel);
        add(entrepriseTf);
        
         Label domaineLabel = new Label("Domaine ");
        TextField domaineTf = new TextField();
        add(domaineLabel);
        add(domaineTf);
        
         Label posteLabel = new Label("Poste ");
        TextField posteTf = new TextField();
        add(posteLabel);
        add(posteTf);
         
        //date
        Label dateLabel = new Label("Date");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        cn.addAll(dateLabel, datePicker);

        

        Button btnSave = new Button("Enregistrer");

        add(cn);
        add(btnSave);

        btnSave.addActionListener(event -> {

             e = new Comptes();
           
            e.setExperience(experienceTf.getText());
            e.setPhoto(photoTf.getText());
             e.setDiplome(diplomeTf.getText());
              e.setEntreprise(entrepriseTf.getText());
               e.setDomaine(domaineTf.getText());
                e.setPoste(posteTf.getText());
                e.setIdUtilisateur(1);
           
            
           
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String formattedDate = dateFormat.format(datePicker.getDate());
           
            
            
  
           
            //e.setDate(selectedDate);
           
            ComptesServices.getInstance().ajoutCompte(e,  formattedDate);
             Dialog.show("Success","account added","OK",null);
            
        });

        //getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, event -> previous.showBack());

        show();

    }

}