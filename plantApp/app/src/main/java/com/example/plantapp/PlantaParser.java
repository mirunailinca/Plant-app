package com.example.plantapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PlantaParser {
    private static final String NAME = "name";
    private static final String SPECIES = "species";
    private static final String WATERING_FREQUENCY = "wateringFrequency";
    private static final String LAST_WATERED_DATE = "lastWateredDate";

    public static List<Plant> parsareJson(String json){
        try {
            JSONArray jsonArray = new JSONArray(json);
            return parsarePlante(jsonArray);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    private static List<Plant> parsarePlante(JSONArray jsonArray) throws JSONException {
        List<Plant> plante = new ArrayList<>();
        for(int i=0; i< jsonArray.length(); i++){
            plante.add(parsarePlanta(jsonArray.getJSONObject(i)));
        }

        return plante;
    }
    private static Plant parsarePlanta(JSONObject jsonObject) throws JSONException {
        String name = jsonObject.getString(NAME);
        String species = jsonObject.getString(SPECIES);
        int wateringFrequency = jsonObject.getInt(WATERING_FREQUENCY);
        String lastWateredDate = jsonObject.getString(LAST_WATERED_DATE);
        return new Plant(name,species,wateringFrequency,lastWateredDate);
    }
}
