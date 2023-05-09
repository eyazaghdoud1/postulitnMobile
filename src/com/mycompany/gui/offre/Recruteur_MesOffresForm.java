/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.offre;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UITimer;
import com.mycompany.entities.Offre;
import com.mycompany.services.OffreService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


/**
 *
 * @author Aziz Ben Guirat
 */
public class Recruteur_MesOffresForm extends Form {

    public Recruteur_MesOffresForm(Resources res) {
        super("Mes Offres", BoxLayout.y());

        // Create button group menu at the top
        ButtonGroup barGroup = new ButtonGroup();

        
        RadioButton mesListes = RadioButton.createToggle("Mes Offres", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton ajouterOffre = RadioButton.createToggle("Ajouter Offre", barGroup);
        ajouterOffre.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        
        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            new Recruteur_MesOffresForm(res).show();
           
            refreshTheme();
            ipDlg.dispose();
        });
        ajouterOffre.addActionListener((e) -> {

            new AjouterOffre(res).show();

        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, ajouterOffre),
                FlowLayout.encloseBottom(arrow)
        ));

        mesListes.setSelected(true);
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

        // Display list of Offre objects
        ArrayList<Offre> offres = OffreService.getInstance().affichageMesOffres(69);
        for (Offre offre : offres) {
            Container offreContainer = new Container(new BorderLayout());

            Container labelsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label posteLabel = new Label(offre.getPoste());
            Label entrepriseLabel = new Label(offre.getEntreprise());
            String dateString = offre.getDateExpiration().toString();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
            Date date = null;
            try {
                date = inputFormat.parse(dateString);
            } catch (ParseException ex) {
            }
            String formattedDate = outputFormat.format(date);
            Label dateExpirationLabel = new Label(formattedDate);
            Label lieuLabel = new Label(offre.getLieu());

            labelsContainer.add(posteLabel);
            labelsContainer.add(entrepriseLabel);
            labelsContainer.add(dateExpirationLabel);
            labelsContainer.add(lieuLabel);

            offreContainer.add(BorderLayout.CENTER, labelsContainer);

            Label lModifier = new Label(" ");
            lModifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(lModifier.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lModifier.setIcon(mFontImage);
            lModifier.setTextPosition(LEFT);

            
            
            lModifier.addPointerPressedListener(l -> {
            
           
            
             
                //n3ayto l suuprimer men service Reclamation
                System.out.println(offre.getIdOffre());
                
                try {
                    new UpdateOffre(offre,res).show();
                } catch (ParseException ex) {
                }
                
           
        });
            Label supp = new Label(" ");
            supp.setUIID("NewsTopLine");
            Style suppStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf7ad02);
            FontImage sFontImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, suppStyle);
            supp.setIcon(sFontImage);
            supp.setTextPosition(LEFT);
            supp.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer cette offre ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                }
                //n3ayto l suuprimer men service Reclamation
                System.out.println(offre.getIdOffre());
                OffreService.getInstance().deleteOffre(offre.getIdOffre());
                    new Recruteur_MesOffresForm(res).show();
                
           
        });
            Container actionsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            actionsContainer.add(lModifier);
            actionsContainer.add(supp);
            actionsContainer.getAllStyles().setMarginBottom(10);

            offreContainer.add(BorderLayout.SOUTH, actionsContainer);
            offreContainer.getAllStyles().setBorder(Border.createLineBorder(1, 0x555555));

            add(offreContainer);

            
        }

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
