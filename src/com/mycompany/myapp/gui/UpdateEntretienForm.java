/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.gui;

import com.codename1.ui.Button;
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
import com.mycompany.myapp.entities.Entretien;
import com.mycompany.myapp.services.CandidatureServices;
import com.mycompany.myapp.services.EntretiensServices;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author HP I5
 */
public class UpdateEntretienForm extends Form {

    UpdateEntretienForm current;

    public UpdateEntretienForm(Form previous, Entretien e, boolean b) {

        setTitle("Modifier votre entretien");
        setLayout(BoxLayout.y());

        current = this;

        Container cn = new Container(new FlowLayout(CENTER));
        Label typeLabel = new Label("Type: " + e.getType());
        add(typeLabel);
        Label lieuLabel = new Label("Lieu ");
        TextField lieuTf = new TextField();
        if (e.getType().equals("Présentiel")) {
            lieuTf.setText(e.getLieu());
            add(lieuLabel);
            add(lieuTf);
        }

        //date
        Label dateLabel = new Label("Date");
        Picker datePicker = new Picker();
        datePicker.setType(Display.PICKER_TYPE_DATE);
        datePicker.setDate(e.getDate());
        cn.addAll(dateLabel, datePicker);

        //heure
        Label timeLabel = new Label("Horaire");
        Picker timePicker = new Picker();
        timePicker.setType(Display.PICKER_TYPE_TIME);

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Date heureDate = null;
        try {
            heureDate = dateFormat.parse(e.getHeure());
        } catch (ParseException ex) {

        }
        
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(heureDate);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minutes = calendar.get(Calendar.MINUTE);

        timePicker.setTime(hour, minutes);
        cn.addAll(timeLabel, timePicker);

        Button btnSave = new Button("Enregistrer");

        add(cn);
        add(btnSave);

        btnSave.addActionListener(event -> {

            if (e.getType().equals("Présentiel")) {
                e.setLieu(lieuTf.getText());
            }
            Date selectedDate = datePicker.getDate();
            int time = timePicker.getTime();
            String timeString = time + "";
            SimpleDateFormat dateFormat1 = new SimpleDateFormat("YYYY-MM-dd");
            String formattedDate = dateFormat1.format(datePicker.getDate());
            
            long selectedTimeMillis = (long) timePicker.getTime() * 1000; // convert to milliseconds
            Date selectedTime = new Date(selectedTimeMillis);
            SimpleDateFormat timeFormat = new SimpleDateFormat("MM:HH");
            String formattedTime = timeFormat.format(selectedTime);
            e.setHeure(formattedTime);
            EntretiensServices.getInstance().updateEntretien(e, formattedDate);
            
            if(b) {
            DetailsEntretienForm f = new DetailsEntretienForm(current, e);
            f.show();
            } else {
            EntretiensCandidatureForm f = new EntretiensCandidatureForm(current, e.getCandidature());
            f.show();
            }
            
            

        });

        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, event -> previous.showBack());

        show();

    }

}
