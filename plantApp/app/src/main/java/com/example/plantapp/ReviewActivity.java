package com.example.plantapp;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.checkerframework.checker.units.qual.A;

import java.util.ArrayList;
import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    private EditText etReview;
    private EditText etNota;
    private Button btnClear;
    private Button btnSave;
    private Button btnDelete;
    private ListView lvReviews;
    private List<Review> listaReviews = new ArrayList<>();
    private FirebaseService firebaseService;
    private int indexProdusSelectat = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_review);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponente();
        firebaseService = FirebaseService.getInstance();
        firebaseService.addReviewListener(dataChangeCallback());
    }

    private Callback<List<Review>> dataChangeCallback() {
        return rezultat -> {
            listaReviews.clear();
            listaReviews.addAll(rezultat);
            ArrayAdapter<Review> adapter = (ArrayAdapter<Review>) lvReviews.getAdapter();
            adapter.notifyDataSetChanged();
            golireText();
        };
    }

    private void initComponente() {
        etReview = findViewById(R.id.etReview);
        etNota = findViewById(R.id.etNota);
        btnClear = findViewById(R.id.btnClearData);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);
        lvReviews = findViewById(R.id.lvReviews);
        ArrayAdapter<Review> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,listaReviews);
        lvReviews.setAdapter(adapter);

        btnClear.setOnClickListener(golireDateEventListener());
        btnSave.setOnClickListener(saveEventListener());
        btnDelete.setOnClickListener(deleteEventListener());
        lvReviews.setOnItemClickListener(reviewSelectatEventListener());
    }

    private AdapterView.OnItemClickListener reviewSelectatEventListener() {
        return (adapterView, view, position, param) -> {
            indexProdusSelectat = position;
            Review review = listaReviews.get(position);
            etReview.setText(review.getText());
            etNota.setText(String.valueOf(review.getNota()));
        };
    }

    private View.OnClickListener deleteEventListener() {
        return view -> {
            if(indexProdusSelectat != -1){
                firebaseService.delete(listaReviews.get(indexProdusSelectat));
            }
        };
    }

    private View.OnClickListener saveEventListener() {
        return view ->{
            if(validare()){
                if(indexProdusSelectat == -1){
                    Review review = actualizareReviewFromUI(null);
                    firebaseService.insert(review);
                }
                else{
                    Review review = actualizareReviewFromUI(listaReviews.get(indexProdusSelectat).getId());
                    firebaseService.update(review);
                }
            }
        };
    }

    private Review actualizareReviewFromUI(String id) {
        Review review = new Review();
        review.setId(id);
        review.setText(etReview.getText().toString());
        review.setNota(Integer.valueOf(etNota.getText().toString()));
        return review;

    }

    private boolean validare() {
        if(etReview.getText() == null || etReview.getText().toString().isEmpty() || etNota.getText() == null || etNota.getText().toString().isEmpty()){
            Toast.makeText(this, "Validarea nu a trecut", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private View.OnClickListener golireDateEventListener() {
        return view -> golireText();
    }

    private void golireText() {
        etReview.setText(null);
        etNota.setText(null);
        indexProdusSelectat = -1;
    }
}