package com.example.plantapp;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface PlantDAO {

    @Insert
    void insertPlant(Plant plant);

    @Query("SELECT * FROM PLANTS")
    List<Plant> getAllPlants();

    @Query("SELECT COUNT(*) FROM PLANTS")
    int getCountPlants();

    @Delete
    void deletePlant(Plant plant);
}
