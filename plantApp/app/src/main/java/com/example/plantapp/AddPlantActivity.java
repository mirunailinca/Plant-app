package com.example.plantapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SharedMemory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;

public class AddPlantActivity extends AppCompatActivity {

    private boolean isEditing = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_plant);
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });


        EditText editTextPlantName = findViewById(R.id.etPlantName);
        EditText editTextPlantSpecies = findViewById(R.id.etPlantSpecies);
        EditText editTextWateringFrequency = findViewById(R.id.etWateringFrequency);
        EditText editTextLastWateredDate = findViewById(R.id.etLastWateredDate);

        Button submitButton = findViewById(R.id.savePlantButton);

        Intent editIntent = getIntent();
        if(editIntent.hasExtra("edit")){
            isEditing = true;
            Plant plant = (Plant) editIntent.getSerializableExtra("edit");

            editTextPlantName.setText(plant.getName());
            editTextPlantSpecies.setText(plant.getSpecies());
            editTextWateringFrequency.setText(String.valueOf(plant.getWateringFrequency()));
            editTextLastWateredDate.setText(String.valueOf(plant.getLastWateredDate()));

        }

        SharedPreferences sharedPreferences = getSharedPreferences("local",MODE_PRIVATE);
        String token = sharedPreferences.getString("token","Valoare default");
        Toast.makeText(this,token,Toast.LENGTH_SHORT).show();

        submitButton.setOnClickListener(v->{
            String name = editTextPlantName.getText().toString();
            String species = editTextPlantSpecies.getText().toString();
            int wateringFreq = Integer.parseInt(editTextWateringFrequency.getText().toString());
            String lastWatered = editTextLastWateredDate.getText().toString();

            Plant plant = new Plant(name,species,wateringFreq,lastWatered);

            Intent intent = getIntent();

            if(isEditing){
                intent.putExtra("edit",plant);
                isEditing=false;
            }
            else{
                intent.putExtra("plantFromIntent",plant);
            }

            setResult(RESULT_OK,intent);
            finish();
        });
    }
}