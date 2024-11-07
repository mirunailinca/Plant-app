package com.example.plantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;
//import android.widget.Toolbar;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class MainPageActivity extends AppCompatActivity {

    private ListView lvPlants;
    List<Plant> plants = new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_page);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fabAdd = findViewById(R.id.fabAdd);

        fabAdd.setOnClickListener(v -> {
            Intent intent =new Intent(getApplicationContext(),AddPlantActivity.class);
            launcher.launch(intent);
        });

        lvPlants = findViewById(R.id.lvPlants);

        lvPlants.setOnItemClickListener((adapterView, view, position,l) -> {
            this.position = position;
            Intent intent = new Intent(getApplicationContext(),com.example.plantapp.AddPlantActivity.class);
            intent.putExtra("edit",plants.get(position));
            launcher.launch(intent);
        });

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if(result.getData().hasExtra("plantFromIntent")){

                Intent intent = result.getData();
                Plant plant = (Plant) intent.getSerializableExtra("plantFromIntent");
                if(plant!=null){
                    plants.add(plant);
                }
                PlantAdapter adapter = new PlantAdapter(getApplicationContext(),R.layout.plant_view,plants,getLayoutInflater());
                lvPlants.setAdapter(adapter);

                Toast.makeText(this, plants.toString(),Toast.LENGTH_SHORT).show();
            }
            else if(result.getData().hasExtra("edit")){
                Intent intent = result.getData();
                Plant editedPlant = (Plant) intent.getSerializableExtra("edit");

                if(editedPlant != null){
                    Plant plant = plants.get(position);

                    plant.setName(editedPlant.getName());
                    plant.setSpecies(editedPlant.getSpecies());
                    plant.setWateringFrequency(editedPlant.getWateringFrequency());
                    plant.setLastWateredDate(editedPlant.getLastWateredDate());

                    PlantAdapter adapter = (PlantAdapter) lvPlants.getAdapter();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        SharedPreferences sharedPreferences = getSharedPreferences("local",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token","Token1234");
        editor.apply();

    }


    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if (id == R.id.action_add_plant) {
            Intent intent = new Intent(MainPageActivity.this, AddPlantActivity.class);
            launcher.launch(intent);
            Toast.makeText(this, "Add plant", Toast.LENGTH_SHORT).show();

            return true;
        } else if (id == R.id.action_calendar) {
            Toast.makeText(this, "Calendar", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_profile) {
            Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }


}