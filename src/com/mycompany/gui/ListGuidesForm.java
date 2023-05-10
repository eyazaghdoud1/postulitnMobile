/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.CN.execute;
import com.codename1.ui.Command;
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
import com.mycomany.entities.Guidesentretiens;
import com.mycompany.services.GuidesentretiensServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Aziz Ben Guirat
 */
public class ListGuidesForm extends Form {
public Guidesentretiens ge ;

    public ListGuidesForm(Resources res) {
        super("Guides", BoxLayout.y());
      //  Guidesentretiens test = GuidesentretiensServices.getInstance().showGuides(ge.getIdguide());
        

        // Create button group menu at the top
       

       

        // Display list of Offre objects
        ArrayList<Guidesentretiens> offres = GuidesentretiensServices.getInstance().affichageGuides();
        for (Guidesentretiens offre : offres) {
           
            Container offreContainer = new Container(new BorderLayout());

            Container labelsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            Label domaineLabel = new Label(offre.getDomaine());
            Label specialiteLabel = new Label(offre.getSpecialite());
            Label supportLabel = new Label(offre.getSupport());
           Button detail = new Button("Voir plus");
             detail.addActionListener(e-> {
                 ge=offre;
             GuidesentretiensForm f = new GuidesentretiensForm(res, ge.getIdguide());
        f.show();
                }
        );
           

            labelsContainer.add(domaineLabel);
            labelsContainer.add(specialiteLabel);
            labelsContainer.add(supportLabel);
            labelsContainer.add(detail);
           

            offreContainer.add(BorderLayout.CENTER, labelsContainer);

            

           

            Container actionsContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            
           
            actionsContainer.getAllStyles().setMarginBottom(10);

            offreContainer.add(BorderLayout.SOUTH, actionsContainer);
            offreContainer.getAllStyles().setBorder(Border.createLineBorder(1, 0x555555));

            add(offreContainer);

            
        }

    }

    

}