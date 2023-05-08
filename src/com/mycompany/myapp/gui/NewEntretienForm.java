/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.myapp.entities.Candidature;
import com.mycompany.myapp.entities.Entretien;
import com.mycompany.myapp.services.EntretiensServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author HP I5
 */
public class NewEntretienForm extends Form {

    NewEntretienForm current;

    public NewEntretienForm(Form previous, Candidature c) {

        setTitle("Programmer un entretien");
        setLayout(BoxLayout.y());

        current = this;

        Container cn = new Container(new FlowLayout(CENTER));
        Label typeLabel = new Label("Type");
        ComboBox<String> comboBox = new ComboBox<>("Présentiel", "Téléphone");

        add(typeLabel);
        add(comboBox);
        
        Label lieuLabel = new Label("Lieu ");
        TextField lieuTf = new TextField();
        add(lieuLabel);
        add(lieuTf);

        //date
        Label dateLabel = new Label("Date");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        cn.addAll(dateLabel, datePicker);

        //heure
        Label timeLabel = new Label("Horaire");
        Picker timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);

        cn.addAll(timeLabel, timePicker);

        Button btnSave = new Button("Enregistrer");

        add(cn);
        add(btnSave);

        btnSave.addActionListener(event -> {

            Entretien e = new Entretien();
            e.setCandidature(RecruteurListCandidaturesForm.recruteurSelectedCandidature);
            e.setGuideId(1);
            e.setType((String) comboBox.getSelectedItem());
            e.setLieu(lieuTf.getText());
            
           
            SimpleDateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
            String formattedDate = dateFormat.format(datePicker.getDate());
           
            
            
            long selectedTimeMillis = (long) timePicker.getTime() * 1000; // convert to milliseconds
            Date selectedTime = new Date(selectedTimeMillis);
            SimpleDateFormat timeFormat = new SimpleDateFormat("MM:HH");
            String formattedTime = timeFormat.format(selectedTime);
            
           
            //e.setDate(selectedDate);
            e.setHeure(formattedTime);
            System.out.println(e);
            EntretiensServices.getInstance().addEntretien(e, 
                    RecruteurListCandidaturesForm.recruteurSelectedCandidature, formattedDate);
            EntretiensCandidatureForm f = new EntretiensCandidatureForm(current, RecruteurListCandidaturesForm.recruteurSelectedCandidature);
            f.show();

        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, event -> previous.showBack());

        show();

    }

}
