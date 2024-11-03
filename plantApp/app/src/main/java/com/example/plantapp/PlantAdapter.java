package com.example.plantapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.sql.Array;
import java.util.List;

public class PlantAdapter extends ArrayAdapter<Plant> {
    private Context context;
    private List<Plant> plantList;
    private int layoutId;
    private LayoutInflater inflater;

    public PlantAdapter(@NonNull Context context, int resource,@NonNull List<Plant> objects, LayoutInflater inflater) {
        super(context,resource,objects);
        this.context = context;
        this.layoutId = resource;
        this.plantList = objects;
        this.inflater = inflater;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(layoutId,parent,false);
        Plant plant = plantList.get(position);
        TextView tvPlantName = view.findViewById(R.id.tvPlantName);
        TextView tvPlantSpecies = view.findViewById(R.id.tvPlantSpecies);
        TextView tvWateringFrequency = view.findViewById(R.id.tvWateringFrequency);
        TextView tvLastWateredDate = view.findViewById(R.id.tvLastWateredDate);

        tvPlantName.setText(plant.getName());
        tvPlantSpecies.setText(plant.getSpecies());
        tvWateringFrequency.setText(plant.getWateringFrequency());
        tvLastWateredDate.setText(plant.getLastWateredDate());

        return view;
    }


}
