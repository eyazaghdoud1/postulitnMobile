/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.offre;

/**
 *
 * @author Aziz Ben Guirat
 */
import com.codename1.components.InfiniteProgress;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.Constraint;
import com.codename1.ui.validation.Validator;
import com.mycompany.entities.Offre;
import com.mycompany.entities.typeOffre;
import com.mycompany.services.OffreService;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;

public class UpdateOffre extends Form {

    private OffreService offreService = OffreService.getInstance();
    private ArrayList<String> typeOffreList = new ArrayList<>();
    private Picker typeOffrePicker = new Picker();
    private TextField posteTextField = new TextField();
    private TextField entrepriseTextField = new TextField();
    private TextField specialiteTextField = new TextField();
    private TextField lieuTextField = new TextField();
    private TextArea descriptionTextArea = new TextArea();
    private Picker dateExpirationPicker = new Picker();
    private Button updateButton = new Button("Update");
    private Resources theme;

    public UpdateOffre(Offre o, Resources res) throws ParseException {
        super("Update Offer", BoxLayout.y());

        typeOffrePicker.setType(Display.PICKER_TYPE_STRINGS);
        //typeOffrePicker.setSelectedString(typeOffreList.get(0));

        dateExpirationPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        posteTextField.setHint("Poste");
        entrepriseTextField.setHint("Entreprise");
        specialiteTextField.setHint("Spécialité");
        lieuTextField.setHint("Lieu");
        descriptionTextArea.setHint("Description");

        this.addAll(
                new SpanLabel("Type d'offre"),
                typeOffrePicker,
                posteTextField,
                entrepriseTextField,
                specialiteTextField,
                lieuTextField,
                descriptionTextArea,
                new SpanLabel("Date d'expiration"),
                dateExpirationPicker,
                updateButton
        );

        ArrayList<String> typeOffreList = offreService.getAllTypesOffres();
        // Populate the typeOffreList with values from the server

        // Set default values based on data from Offre o
        typeOffrePicker.setStrings(typeOffreList.toArray(new String[0]));
        typeOffrePicker.setSelectedString(o.getIdtype().getDescription());

        posteTextField.setText(o.getPoste());
        entrepriseTextField.setText(o.getEntreprise());
        specialiteTextField.setText(o.getSpecialite());
        lieuTextField.setText(o.getLieu());
        descriptionTextArea.setText(o.getDescription());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format.parse(o.getDateExpiration());
        
        dateExpirationPicker.setDate(date);
        // Validate the form before submitting
        updateButton.addActionListener((e) -> {

            try {

                InfiniteProgress ip = new InfiniteProgress();; //Loading  after insert data

                final Dialog iDialog = ip.showInfiniteBlocking();

                //njibo iduser men session (current user)
                Offre newOffre = new Offre();
                String idtype = typeOffrePicker.getSelectedString();
                //newOffre.setIdtype(typeOffreList.indexOf(typeOffrePicker.getSelectedString()) + 1);
                newOffre.setPoste(posteTextField.getText());
                newOffre.setEntreprise(entrepriseTextField.getText());
                newOffre.setSpecialite(specialiteTextField.getText());
                newOffre.setLieu(lieuTextField.getText());
                newOffre.setDescription(descriptionTextArea.getText());
                newOffre.setDateExpiration(dateExpirationPicker.getDate().toString());

                System.out.println("data   == " + newOffre);

                //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                offreService.getInstance().updateOffre(newOffre, idtype ,o.getIdOffre());

                iDialog.dispose(); //na7io loading ba3d ma3mlna ajout

                //ba3d ajout net3adaw lel ListREclamationForm
                //ba3d ajout net3adaw lel ListREclamationForm
                Recruteur_MesOffresForm f = new Recruteur_MesOffresForm(res);
                f.show();

                

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

    }

}
