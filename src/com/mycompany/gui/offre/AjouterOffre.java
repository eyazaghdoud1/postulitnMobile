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

import java.util.ArrayList;

public class AjouterOffre extends Form {

    
    private OffreService offreService = OffreService.getInstance();
    private ArrayList<String> typeOffreList = new ArrayList<>();
    private Picker typeOffrePicker = new Picker();
    private TextField posteTextField = new TextField();
    private TextField entrepriseTextField = new TextField();
    private TextField specialiteTextField = new TextField();
    private TextField lieuTextField = new TextField();
    private TextField descriptionTextArea = new TextField();
    private Picker dateExpirationPicker = new Picker();
    private Button ajouterButton = new Button("Ajouter");
    private Resources theme;

    public AjouterOffre(Resources res) {
        super("Ajouter une offre", BoxLayout.y());
        
       
        
        ButtonGroup barGroup = new ButtonGroup();

        
        RadioButton mesListes = RadioButton.createToggle("Mes Offres", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton ajouterOffre = RadioButton.createToggle("Ajouter Offre", barGroup);
        ajouterOffre.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");
        
        
        mesListes.addActionListener((e) -> {
            

            new Recruteur_MesOffresForm(res).show();
           
           
        });
        ajouterOffre.addActionListener((e) -> {
            
            new AjouterOffre(res).show();

        });
        
        ajouterOffre.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });

        bindButtonSelection(ajouterOffre, arrow);
        bindButtonSelection(mesListes, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });
        

        typeOffrePicker.setType(Display.PICKER_TYPE_STRINGS);
        //typeOffrePicker.setSelectedString(typeOffreList.get(0));

        dateExpirationPicker.setType(Display.PICKER_TYPE_DATE_AND_TIME);

        posteTextField.setHint("Poste");
        entrepriseTextField.setHint("Entreprise");
        specialiteTextField.setHint("Spécialité");
        lieuTextField.setHint("Lieu");
        descriptionTextArea.setHint("Description");

        this.addAll(
                LayeredLayout.encloseIn(
                GridLayout.encloseIn(2,mesListes, ajouterOffre),
                FlowLayout.encloseBottom(arrow)
        ),
                new SpanLabel("Type d'offre"),
                typeOffrePicker,
                posteTextField,
                entrepriseTextField,
                specialiteTextField,
                lieuTextField,
                descriptionTextArea,
                new SpanLabel("Date d'expiration"),
                dateExpirationPicker,
                ajouterButton
        );

        ArrayList<String> typeOffreList = offreService.getAllTypesOffres();
        // Populate the typeOffreList with values from the server

        typeOffrePicker.setStrings(typeOffreList.toArray(new String[0]));
        typeOffrePicker.setSelectedString(typeOffreList.get(0));

        // Validate the form before submitting
        ajouterButton.addActionListener((e) -> {

            try {

                if (posteTextField.getText().equals("") || lieuTextField.getText().equals("") || descriptionTextArea.getText().equals("") || entrepriseTextField.getText().equals("") || specialiteTextField.getText().equals("")) {
                    Dialog.show("Veuillez vérifier les données", "", "Annuler", "OK");
                } ///zedna hethyy controle de saisie aal nom
                else {
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

                    System.out.println("data  reclamation == " + newOffre);

                    //appelle methode ajouterReclamation mt3 service Reclamation bch nzido données ta3na fi base 
                    offreService.getInstance().addOffre(newOffre,idtype);

                    iDialog.dispose(); //na7io loading ba3d ma3mlna ajout

                    //ba3d ajout net3adaw lel ListREclamationForm
                    //ba3d ajout net3adaw lel ListREclamationForm
                    new Recruteur_MesOffresForm(res).show();

                    refreshTheme();//Actualisation

                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        
    }
    
    public void bindButtonSelection(Button btn, Label l) {

        btn.addActionListener(e -> {
            if (btn.isSelected()) {
                updateArrowPosition(btn, l);
            }
        });
    }

    private void updateArrowPosition(Button btn, Label l) {

        l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
        l.getParent().repaint();
    }
}
