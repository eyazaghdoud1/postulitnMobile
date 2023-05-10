/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.projets;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.company.entities.ProjetFreelance;
import com.company.entities.Secteur;
import com.mycompany.services.ServiceProjets;

/**
 *
 * @author Users
 */
public class AjoutProjetForm extends Form {

    //AjoutProjetForm current;
    ProjetFreelance pf;
    String selectedSecteur;

    public AjoutProjetForm(Resources res, Form previous) {

        setTitle("Ajouter un Projet!");
        setLayout(BoxLayout.y());
        Secteur s = new Secteur();
       

     
        TextField nom = new TextField("", "Nom", 20, TextField.ANY);
        TextField duree = new TextField("", "duree", 20, TextField.ANY);
        TextField theme = new TextField("", "theme", 20, TextField.ANY);
        TextField description = new TextField("", "description", 20, TextField.ANY);
        Picker dateDebut = new Picker();
        dateDebut.setType(Display.PICKER_TYPE_DATE);
        Picker dateFin = new Picker();
        dateDebut.setType(Display.PICKER_TYPE_DATE);
        ComboBox<String> combo = new ComboBox<>("Finance", "informatique", "securité");
        combo.setSelectedItem("Finance");
        nom.setSingleLineTextArea(false);
        duree.setSingleLineTextArea(false);
        theme.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        Button ajout = new Button("ajout");

        pf = new ProjetFreelance();

        combo.addActionListener((evt) -> {

            selectedSecteur = combo.getSelectedItem();
            s.setDescription(selectedSecteur);
            pf.setS(s);
        });

        ajout.addActionListener((e) -> {
            //     
            pf.setNote(0);
            pf.setNom(nom.getText());
            pf.setDuree(Integer.parseInt(duree.getText()));
            pf.setTheme(theme.getText());
            pf.setDescription(description.getText());
            pf.setDateDebut(dateDebut.getText());
            pf.setDateFin(dateFin.getText());
            pf.setDuree(Integer.parseInt(duree.getText()));
    
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDatedebut = dateFormat.format(dateDebut.getDate());
            String formattedDatefin = dateFormat.format(dateFin.getDate());
            ServiceProjets.getInstance().addProjets(pf, formattedDatedebut, formattedDatefin, 68);
            Dialog.show("Success", "project", "OK", null);
            new ListeProjets(res).show();
            //new SignInForm(res).show();
        });

        addAll(nom, theme, duree, description, dateDebut, dateFin, combo, ajout);
   
     getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        show();
    }
    
     
}
