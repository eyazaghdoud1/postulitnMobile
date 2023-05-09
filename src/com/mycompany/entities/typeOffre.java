/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author Aziz Ben Guirat
 */
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.Map;

/**
 *
 * @author Lenovo
 */
public class typeOffre {
    //nemchio taw nchofo entity fi symfony
    
    private int idtype;
    private String description;
    
    

    public typeOffre() {
    }

    public typeOffre(int idtype, String description) {
        this.idtype = idtype;
        this.description = description;
    }

    public int getIdtype() {
        return idtype;
    }

    public String getDescription() {
        return description;
    }

    public void setIdtype(int idtype) {
        this.idtype = idtype;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "typeOffre{" + "idtype=" + idtype + ", description=" + description + '}';
    }
    
    public static typeOffre fromJsonString(String jsonString) {
        JSONParser parser = new JSONParser();
        try {
            Map<String, Object> map = parser.parseJSON(new InputStreamReader(new ByteArrayInputStream(jsonString.getBytes("UTF-8")), "UTF-8"));
            int id = ((Double) ((Map<String, Object>) map.get("idtype")).get("idtype")).intValue();
            String description = (String) ((Map<String, Object>) map.get("idtype")).get("description");
            return new typeOffre(id, description);
        } catch (IOException ex) {
            Log.e(ex);
            return null;
        }
    }

    
    
    
    
    
    
    
    
    }


