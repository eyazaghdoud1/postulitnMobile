/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.gui;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.company.entities.Commentaires;
import com.company.services.ServiceCommentaires;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Users
 */
public class AjoutCommentairesForm extends Form {
   
    public AjoutCommentairesForm (Resources res){
        
        setTitle("Commentaire!");
        setLayout(BoxLayout.y());
        TextField Contenu= new TextField("", "contenu", 20, TextField.ANY);
        Contenu.setSingleLineTextArea(false);
        Button Ajout = new Button("commenter");
        Commentaires c = new Commentaires();
        c.setContenu(Contenu.getText());
        Ajout.addActionListener((e) -> {
        ServiceCommentaires.getInstance().ajoutCommentaires(c);
        });
         addAll(Contenu, Ajout);
    }
}
        

