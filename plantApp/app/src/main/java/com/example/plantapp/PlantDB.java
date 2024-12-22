package com.example.plantapp;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Plant.class},version = 1,exportSchema = false)
public abstract class PlantDB extends RoomDatabase{

    private static PlantDB instance;
    private static String dbName = "myPlants.db";

    public static PlantDB getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context,PlantDB.class,dbName)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
    public abstract PlantDAO getPlantDAO();
}
