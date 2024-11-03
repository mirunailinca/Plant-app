package com.example.plantapp;

import android.content.Intent;
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

    ListView lvPlants;
    List<Plant> plants = new ArrayList<>();
    ActivityResultLauncher<Intent> launcher;


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

        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),result ->{
            if(result.getResultCode() == RESULT_OK){

                Intent intent = result.getData();
                Plant plant = (Plant) intent.getSerializableExtra("plantFromIntent");
                if(plant!=null){
                    plants.add(plant);
                }
                PlantAdapter adapter = new PlantAdapter(getApplicationContext(),R.layout.plant_view,plants,getLayoutInflater());
                lvPlants.setAdapter(adapter);

                Toast.makeText(this, plants.toString(),Toast.LENGTH_SHORT).show();
            }
        });
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