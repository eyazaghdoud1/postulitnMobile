/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import static com.codename1.io.Util.split;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.FlowLayout;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Offre;
import com.mycompany.myapp.entities.Utilisateur;
import com.mycompany.myapp.services.CandidatureServices;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/**
 *
 * @author HP I5
 */
public class PostulerForm extends Form {

   /* String accountSID = "AC330a9eebe30cd2d3b993bbcc07995093";
    String authToken = "98a01aca5e09416f7b60980479ffd038";
    String fromPhone = "+16315199904";
  
*/
      PostulerForm current;
     String[] cv;
     String[] lettre;
     File cvF;
     File lettreF;
    public PostulerForm(Form previous) {
        current = this;
        Candidature c = new Candidature();
        Offre o = new Offre();
        o.setId(99);
        c.setIdOffre(o);
        Utilisateur candidat = new Utilisateur();
        candidat.setId(68);
        c.setIdCandidat(candidat);

        setTitle("Postuler");
        setLayout(new FlowLayout(CENTER, CENTER));

        /*  Button message = new Button("message");
     message.addActionListener(e -> {
      
     });
     add(message);*/
        Label l1 = new Label("CV");

        Button browse = new Button("Browse CV");
        browse.addActionListener(e -> {
            
                  if (FileChooser.isAvailable()) {

            FileChooser.showOpenDialog(
                            ".pdf,application/pdf",
                    e2-> {
        String file = (String)e2.getSource();
        if (file == null) {
            add("No file was selected");
            revalidate();
        } else {
           FileSystemStorage fs = FileSystemStorage.getInstance();
                   cvF= new File(file);
                   cv = split(file, "/");
                   
              
           }
        } );
       

            }});

      
         
         
      
        Container cn = new Container(new FlowLayout(CENTER));
        cn.addAll(l1, browse);
       

        Label l2 = new Label("Lettre de motivation");
        //TextField fc2 = new TextField();
        Button browse1 = new Button("Browse lettre de motivation");
        browse1.addActionListener(e -> {
            
                  if (FileChooser.isAvailable()) {

            FileChooser.showOpenDialog(
                            ".pdf,application/pdf",
                    e2-> {
        String file1 = (String)e2.getSource();
        if (file1 == null) {
            add("No file was selected");
            revalidate();
        } else {
           FileSystemStorage fs1 = FileSystemStorage.getInstance();
           lettreF= new File(file1);
            
                   lettre = split(file1, "/");
                   
             
          
           }
        } );
       

            }});
        Container cn1 = new Container(new FlowLayout(CENTER));
        cn1.addAll(l2, browse1);

        Button btnSave = new Button("Postuler");
        Container cn2 = new Container(new FlowLayout(CENTER));
        cn1.addAll(btnSave);

        add(cn);
        add(cn1);
        add(cn2);

        btnSave.addActionListener(e -> {

            c.setCv(cv[cv.length-1]);
           
            //c.setCv(fc1.getText());
            c.setLettre(lettre[lettre.length-1]);
            try {
                copyFileUsingStream(cvF, new File("C:\\xampp\\htdocs\\postulitn\\cv"+cv[cv.length-1]));
            } catch (IOException ex) {
             
            }

           // CandidatureServices.getInstance().addCandidature(c);
           // ListCandiaturesForm f = new ListCandiaturesForm(current);
           // f.show();

        });
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());

        show();

    }
    private static void copyFileUsingStream(File source, File dest) throws IOException {
    InputStream is = null;
        OutputStream os = null;
    try {
        is = new FileInputStream(source);
        os = new FileOutputStream(dest);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    } finally {
        is.close();
        os.close();
    }
}

}
