/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.projets;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.company.entities.ProjetFreelance;
import com.company.entities.Secteur;
import java.text.SimpleDateFormat;

/**
 *
 * @author Users
 */
public class UpdateProjet extends Form {
    
        
    public UpdateProjet (Resources res){
        
        setTitle("Ajouter un Projet!");
        setLayout(BoxLayout.y());
        Secteur s = new Secteur();
        // current=this; 
        
 
       /* Container cn = new Container(new FlowLayout(CENTER));
        Label NomLabel = new Label("Nom");
        TextField NomTf = new TextField();
        add(NomLabel);
        add(NomTf);
        
        
        Label themLabel = new Label("Theme");
        TextField themeTf = new TextField();
        add(themLabel);
        add(themeTf);
        
        
        Label dureeLabel = new Label("Duree");
        TextField dureeTf = new TextField();
        add(dureeLabel);
        add(dureeTf);
        
        
        Label descriLabel = new Label("Description");
        TextField descriTf = new TextField();
        add(descriLabel);
        add(descriTf);
        
         //date
        Label datedebutLabel = new Label("Datedebut");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        cn.addAll(datedebutLabel, datePicker);
        
            //date
        Label datefinLabel = new Label("Datefin");
        Picker datePicker1 = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        cn.addAll(datedebutLabel, datePicker);
        
               
      
        Label typeLabel = new Label("secteur");
        ComboBox<String> comboBox = new ComboBox<>("15", "147");
        add(typeLabel);
        add(comboBox);
        
        
        
        Button btnSave = new Button("Enregistrer");

        add(cn);
        add(btnSave);

      btnSave.addActionListener(event -> {
            ProjetFreelance pf = new ProjetFreelance();
        pf.setIdResponsable(67);
        pf.setNote(0);
        pf.setNom(NomTf.getText());
        pf.setDuree(Integer.parseInt(dureeTf.getText()));
        pf.setTheme(themeTf.getText());
        pf.setDescription(descriTf.getText());
        pf.setDescription(datePicker.getText());
        pf.setDescription(datePicker1.getText());
     
        comboBox.addActionListener((evt) -> {
            String selectedSecteur = comboBox.getSelectedItem();
            pf.s.setDescription(selectedSecteur);
          //  pf.s.setDescription(selectedSecteur);
        });
        
           SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String formattedDate = dateFormat.format(datePicker.getDate());
            
             System.out.println(pf);
          ServiceProjets.getInstance().addProjets(pf, formattedDate); 
             }); 
        show();  
                }*/  
        
        TextField nom= new TextField("", "Nom", 20, TextField.ANY);
        TextField duree= new TextField("", "duree", 20, TextField.ANY);
        TextField theme = new TextField("", "theme", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.ANY);
         Picker dateDebut = new Picker();
         dateDebut.setType(Display.PICKER_TYPE_DATE);
         Picker dateFin = new Picker();
         dateDebut.setType(Display.PICKER_TYPE_DATE);
         ComboBox<String> combo = new ComboBox<>("Finance", "informatique","securité");
        
        nom.setSingleLineTextArea(false);
        duree.setSingleLineTextArea(false);
        theme.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        Button ajout = new Button("ajout");
  
       ProjetFreelance pf = new ProjetFreelance();
       // pf.setIdResponsable(67);
        pf.setNote(0);
        pf.setNom(nom.getText());
        pf.setDuree(Integer.parseInt(duree.getText()));
        pf.setTheme(theme.getText());
        pf.setDescription(description.getText());
        pf.setDescription(dateDebut.getText());
        pf.setDescription(dateFin.getText());
     
        combo.addActionListener((evt) -> {
            String selectedSecteur = combo.getSelectedItem();
            s.setDescription(selectedSecteur);
            pf.setS(s);
        });
         
   /* ajout.addActionListener((e) -> {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDatedebut = dateFormat.format(dateDebut.getDate());
        String formattedDatefin = dateFormat.format(dateFin.getDate());
        ServiceProjets.getInstance().ajoutProjets(pf);
        Dialog.show("Success","project","OK",null);
            //new SignInForm(res).show();
        });
        
         addAll(nom, theme, duree, description, dateDebut, dateFin, combo , ajout);
    }*/
}}
