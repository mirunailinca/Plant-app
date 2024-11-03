package com.example.plantapp;

import java.io.Serializable;

public class Plant implements Serializable {

    private String name;
    private String species;
    private String imageUrl;
    private int wateringFrequency;
    private String lastWateredDate;

    public Plant(String name, String species, int wateringFrequency, String lastWateredDate) {
        this.name = name;
        this.species = species;
        this.wateringFrequency = wateringFrequency;
        this.lastWateredDate = lastWateredDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getWateringFrequency() {
        return wateringFrequency;
    }

    public void setWateringFrequency(int wateringFrequency) {
        this.wateringFrequency = wateringFrequency;
    }

    public String getLastWateredDate() {
        return lastWateredDate;
    }

    public void setLastWateredDate(String lastWateredDate) {
        this.lastWateredDate = lastWateredDate;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", wateringFrequency=" + wateringFrequency +
                ", lastWateredDate='" + lastWateredDate + '\'' +
                '}';
    }
}
