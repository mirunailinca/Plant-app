package com.example.plantapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class UsersListActivity extends AppCompatActivity {
    protected void OnCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);

        ListView listView = findViewById(R.id.listView);

        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        Set<String> usersSet = sharedPreferences.getStringSet("usersSet",new HashSet<>());

        Log.d("UsersListActivity", "AICI REGEEEE Retrieved Users: " + usersSet);

        ArrayList<User> users = new ArrayList<>();
        for(String userString : usersSet){
            String[] parts = userString.split(",");
            String firstName = parts[0];
            String lastName = parts[1];
            String email = parts[2];
            String password = parts[3];
            Gender gender = Gender.valueOf(parts[4]);
            String birthDateString = parts[5];

            Date birthDate = null;
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                birthDate = sdf.parse(birthDateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            User user = new User(firstName,lastName,email,password,gender,birthDate);
            users.add(user);
        }

        ArrayAdapter<User> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,users);
        listView.setAdapter(adapter);

        Log.d("UsersListActivity", "Adapter set to ListView with " + users.size() + " users.");
    }
}
