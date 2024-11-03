package com.example.plantapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.ParseException;

public class AddPlantActivity extends AppCompatActivity {

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

        submitButton.setOnClickListener(v->{
            String name = editTextPlantName.getText().toString();
            String species = editTextPlantSpecies.getText().toString();
            int wateringFreq = Integer.parseInt(editTextWateringFrequency.getText().toString());
            String lastWatered = editTextLastWateredDate.getText().toString();

            Plant plant = new Plant(name,species,wateringFreq,lastWatered);

            Intent intent = getIntent();
            intent.putExtra("plantFromIntent",plant);
            setResult(RESULT_OK,intent);
            finish();
        });
    }
}