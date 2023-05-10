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
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.company.entities.ProjetFreelance;
import com.company.entities.Secteur;
import com.mycompany.services.ServiceProjets;
import java.text.SimpleDateFormat;

/**
 *
 * @author Users
 */
public class UpdateProjet extends Form {
 Form current;
    public UpdateProjet(ProjetFreelance p, Resources res, Form previous) {

        setTitle("Update un Projet!");
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

        
        nom.setText(p.getNom());
        duree.setText(""+p.getDuree());
        theme.setText(p.getTheme());
        description.setText(p.getDescription());
        dateDebut.setText(p.getDateDebut());
        dateFin.setText(p.getDateFin());
        combo.setSelectedItem(p.getS().getDescription());
        
        
        nom.setSingleLineTextArea(false);
        duree.setSingleLineTextArea(false);
        theme.setSingleLineTextArea(false);
        description.setSingleLineTextArea(false);
        Button update = new Button("Modifier");

       

        combo.addActionListener((evt) -> {
            String selectedSecteur = combo.getSelectedItem();
            s.setDescription(selectedSecteur);
            
        });

        

        update.addActionListener((e) -> {
             ProjetFreelance pf = new ProjetFreelance();
            // pf.setIdResponsable(67);
            pf.setNote(0);
            pf.setNom(nom.getText());
            pf.setDuree(Integer.parseInt(duree.getText()));
            pf.setTheme(theme.getText());
            pf.setDescription(description.getText());
            pf.setDateDebut(dateDebut.getText());
            pf.setDateFin(dateFin.getText());
            pf.setDuree(Integer.parseInt(duree.getText()));
            pf.setS(s);
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String formattedDatedebut = dateFormat.format(dateDebut.getDate());
            String formattedDatefin = dateFormat.format(dateFin.getDate());
            ServiceProjets.getInstance().modifierProjets(pf, formattedDatefin, formattedDatefin, p.getIdProjet());
            Dialog.show("Success", "project", "OK", null);
            new ListeProjets(res).show();
        });

        addAll(nom, theme, duree, description, dateDebut, dateFin, combo, update);
   
         getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        show();
    }
    

}
