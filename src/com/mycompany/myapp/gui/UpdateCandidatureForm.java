/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.services.CandidatureServices;

/**
 *
 * @author HP I5
 */
public class UpdateCandidatureForm extends Form {

    UpdateCandidatureForm current;

    public UpdateCandidatureForm(Form previous) {

        setTitle("Modifier votre candidature");
        setLayout(new FlowLayout(CENTER, CENTER));

         Label l1= new Label("CV");
        TextField fc1 = new TextField();
        Container cn = new Container(new FlowLayout(CENTER));
        cn.addAll(l1,fc1);
        Label l2= new Label("Lettre de motivation");
        TextField fc2 = new TextField();
        Container cn1 = new Container(new FlowLayout(CENTER));
        cn1.addAll(l2,fc2);
        
        Button btnSave = new Button("Postuler");
        Container cn2 = new Container(new FlowLayout(CENTER));
        cn1.addAll(btnSave);
       
        add(cn);
        add(cn1);
        add(cn2);

        btnSave.addActionListener(e -> {

            ListCandiaturesForm.selectedCandidature.setCv(fc1.getText());
            ListCandiaturesForm.selectedCandidature.setLettre(fc2.getText());
            CandidatureServices.getInstance().updateCandidature(ListCandiaturesForm.selectedCandidature);
            DetailsCandidatureForm f = new DetailsCandidatureForm(current, ListCandiaturesForm.selectedCandidature);
            f.show();

        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        show();

    }

}
