package com.example.plantapp;

public class WateringSchedule {
    private Plant plant;
    private String nextWateringDate;

    public WateringSchedule(Plant plant, String nextWateringDate) {
        this.plant = plant;
        this.nextWateringDate = nextWateringDate;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String getNextWateringDate() {
        return nextWateringDate;
    }

    public void setNextWateringDate(String nextWateringDate) {
        this.nextWateringDate = nextWateringDate;
    }

    @Override
    public String toString() {
        return "WateringSchedule{" +
                "plant=" + plant +
                ", nextWateringDate='" + nextWateringDate + '\'' +
                '}';
    }
}
