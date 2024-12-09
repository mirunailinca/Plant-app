package com.example.plantapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class AllUsersActivity extends AppCompatActivity {
    private static final String jsonUrlUsers = "https://www.jsonkeeper.com/b/SASN";
    private ListView lvUsers;
    List<User> users = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_users);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvUsers = findViewById(R.id.lvUsers);

        initComponenteUsers();
        incarcareUsers();
    }

    private void incarcareUsers(){
        Thread thread = new Thread(){
            @Override
            public void run() {
                HttpsManager manager = new HttpsManager(jsonUrlUsers);
                String json = manager.procesare();
                new Handler(getMainLooper()).post(()->{
                    getJsonFromHttps(json);
                });
            }
        };
        thread.start();
    }

    private void getJsonFromHttps(String json){
        if (json == null || json.isEmpty()) {
            Log.e("JSON_ERROR", "Received empty or null JSON");
            Toast.makeText(this, "Error: No data received from server", Toast.LENGTH_SHORT).show();
            return;
        }

        users.addAll(UserParser.parsareJson(json));
        ((ArrayAdapter<User>)lvUsers.getAdapter()).notifyDataSetChanged();
}

    private void initComponenteUsers(){
        lvUsers = findViewById(R.id.lvUsers);
        UserAdapter adapter = new UserAdapter(getApplicationContext(),R.layout.user_view, users,getLayoutInflater());
        lvUsers.setAdapter(adapter);
    }


}