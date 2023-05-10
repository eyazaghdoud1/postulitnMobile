/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui.projets;

/**
 *
 * @author Users
 */
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
import com.company.entities.ProjetFreelance;
import com.mycompany.services.ServiceProjets;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListeProjets extends Form {
    Form current;

    public ListeProjets(Resources res) {
        super("Mes Projets", BoxLayout.y());
        current = this;

        // Create button group menu at the top
        ButtonGroup barGroup = new ButtonGroup();

        RadioButton mesListes = RadioButton.createToggle("Mes Projet", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton ajouterProjet = RadioButton.createToggle("Ajouter Projet", barGroup);
        ajouterProjet.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        mesListes.addActionListener((e) -> {
            InfiniteProgress ip = new InfiniteProgress();
            final Dialog ipDlg = ip.showInifiniteBlocking();

            new ListeProjets(res).show();

            refreshTheme();
            ipDlg.dispose();
        });
        ajouterProjet.addActionListener((e) -> {

            new AjoutProjetForm(res, current).show();

        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, ajouterProjet),
                FlowLayout.encloseBottom(arrow)
        ));

        mesListes.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(mesListes, arrow);
        });

        bindButtonSelection(ajouterProjet, arrow);
        bindButtonSelection(mesListes, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

        // Display list 
        ArrayList<ProjetFreelance> projets = ServiceProjets.getInstance().getAllProjets(68);
        for (ProjetFreelance p : projets) {
            Container projetContainer = new Container(new BorderLayout());

            Container labelsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label nomLabel = new Label(p.getNom());
            Label themeLabel = new Label(p.getTheme());
            String dateStringdebut = p.getDateDebut().toString();
            String dateStringfin = p.getDateFin().toString();
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");
            Date datedebut = null;
            Date datefin = null;
            try {
                datedebut = inputFormat.parse(dateStringdebut);
                datefin = inputFormat.parse(dateStringfin);
            } catch (ParseException ex) {
            }
            String formattedDatefin = outputFormat.format(datedebut);
            String formattedDatedebut = outputFormat.format(datefin);
            Label datefinLabel = new Label(formattedDatefin);
            Label datedebutLabel = new Label(formattedDatedebut);
            Label secteurLabel = new Label(p.getS().getDescription());

            labelsContainer.add(nomLabel);
            labelsContainer.add(themeLabel);
            labelsContainer.add(formattedDatefin);
            labelsContainer.add(formattedDatedebut);
            labelsContainer.add(secteurLabel);

            projetContainer.add(BorderLayout.CENTER, labelsContainer);

            Button ldetail = new Button(" ");
            ldetail.setUIID("NewsTopLine");
            Style detailStyle = new Style(ldetail.getUnselectedStyle());
            detailStyle.setFgColor(0xf7ad02);
            FontImage dFontImage = FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, detailStyle);
            ldetail.setIcon(dFontImage);
            ldetail.setTextPosition(LEFT);

            ldetail.addPointerPressedListener(l -> {

             //   new DetailsProjets(p, res).show();

            });
            Button lmodifier = new Button(" ");
            lmodifier.setUIID("NewsTopLine");
            Style modifierStyle = new Style(ldetail.getUnselectedStyle());
            modifierStyle.setFgColor(0xf7ad02);
            FontImage mFontImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, modifierStyle);
            lmodifier.setIcon(mFontImage);
            lmodifier.setTextPosition(LEFT);

            lmodifier.addPointerPressedListener(l -> {

                new UpdateProjet(p, res,current).show();

            });
            
            
            Button lsupprimer = new Button(" ");
            lsupprimer.setUIID("NewsTopLine");
            Style supprimerStyle = new Style(ldetail.getUnselectedStyle());
            supprimerStyle.setFgColor(0xf7ad02);
            FontImage sFontImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, supprimerStyle);
            lsupprimer.setIcon(sFontImage);
            lsupprimer.setTextPosition(LEFT);
           //   Button Cand = new Button("Voir candidatures");
           
            
            
            lsupprimer.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce Projet ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
                }
            
                
                ServiceProjets.getInstance().deleteProjet(p.getIdProjet());
                    new ListeProjets(res).show();
                
           
        });
            
            Container actionsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            actionsContainer.add(ldetail);
            actionsContainer.add(lmodifier);
            actionsContainer.add(lsupprimer);
          //    actionsContainer.add(Cand);
     
            actionsContainer.getAllStyles().setMarginBottom(10);

            projetContainer.add(BorderLayout.SOUTH, actionsContainer);
            projetContainer.getAllStyles().setBorder(Border.createLineBorder(1, 0x555555));

            add(projetContainer);

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
