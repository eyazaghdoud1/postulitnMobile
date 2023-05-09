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
public class AllOffresForm extends Form {

    public AllOffresForm(Resources res) {
        super("Offres", BoxLayout.y());

        // Create button group menu at the top
       

       

        // Display list of Offre objects
        ArrayList<Offre> offres = OffreService.getInstance().affichageOffres();
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

            

            Label supp = new Label("Postuler ");
            supp.setUIID("NewsTopLine");
            Style suppStyle = new Style(supp.getUnselectedStyle());
            suppStyle.setFgColor(0xf7ad02);
            FontImage sFontImage = FontImage.createMaterial(FontImage.MATERIAL_NOTE_ADD, suppStyle);
            supp.setIcon(sFontImage);
            supp.setTextPosition(LEFT);

            Container actionsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
            actionsContainer.add(supp);
            actionsContainer.getAllStyles().setMarginBottom(10);

            offreContainer.add(BorderLayout.SOUTH, actionsContainer);
            offreContainer.getAllStyles().setBorder(Border.createLineBorder(1, 0x555555));

            add(offreContainer);

            
        }

    }

    

}
