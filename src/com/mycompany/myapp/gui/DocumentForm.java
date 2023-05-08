/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.BrowserComponent;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.entities.Candidature;

/**
 *
 * @author HP I5
 */
public class DocumentForm extends Form{

    public DocumentForm(Form previous, String doc, Candidature cand) {
        
        setTitle(doc);
        setLayout(BoxLayout.y());
        
        BrowserComponent browser = new BrowserComponent();
        if (doc.equals("CV")) {
        browser.setURL("http://localhost/postulitn/cv/" + cand.getCv());
      
        } else {
             browser.setURL("http://localhost/postulitn/lettres/" + cand.getLettre());
        }
        browser.setPreferredSize(new Dimension(getWidth(), getHeight()));
        
        add(browser);
        
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
   
    
}
