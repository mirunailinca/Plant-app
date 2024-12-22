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

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {
    private EditText etQuestion;
    private Button btnClear;
    private Button btnSave;
    private Button btnDelete;
    private ListView lvQuestions;
    private List<Question> listaQuestions = new ArrayList<>();
    private FirebaseService firebaseService;
    private int indexProdusSelectat = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        initComponente();
        firebaseService = FirebaseService.getInstance();
        firebaseService.addListenerQuestion(dataChangeCallback());
    }


    private Callback<List<Question>> dataChangeCallback() {
        return rezultat -> {
            listaQuestions.clear();
            listaQuestions.addAll(rezultat);
            ArrayAdapter<Question> adapter = (ArrayAdapter<Question>) lvQuestions.getAdapter();
            adapter.notifyDataSetChanged();
            golireText();
        };
    }

    private void initComponente() {
        etQuestion = findViewById(R.id.etQuestion);
        btnClear = findViewById(R.id.btnClearDataQuestion);
        btnDelete = findViewById(R.id.btnDeleteQuestion);
        btnSave = findViewById(R.id.btnSaveQuestion);
        lvQuestions = findViewById(R.id.lvQuestions);

        ArrayAdapter<Question> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1,listaQuestions);
        lvQuestions.setAdapter(adapter);

        btnClear.setOnClickListener(golireDateEventListener());
        btnSave.setOnClickListener(saveEventListener());
        btnDelete.setOnClickListener(deleteEventListener());
        lvQuestions.setOnItemClickListener(questionSelectatEventListener());
    }

    private View.OnClickListener golireDateEventListener() {
        return view -> golireText();

    }

    private void golireText() {
        etQuestion.setText(null);
        indexProdusSelectat = -1;
    }

    private View.OnClickListener saveEventListener() {
        return view -> {
            if(validare()){
                if(indexProdusSelectat == -1){
                    Question question = actualizareQuestionFromUI(null);
                    firebaseService.insertQuestion(question);
                }
            }
        };
    }

    private boolean validare() {
        if(etQuestion.getText() == null || etQuestion.getText().toString().isEmpty()){
            Toast.makeText(this, "Validarea nu a trecut", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;

    }

    private Question actualizareQuestionFromUI(String id) {
        Question question = new Question();
        question.setId(id);
        question.setText(etQuestion.getText().toString());

        return question;
    }

    private View.OnClickListener deleteEventListener() {
        return view -> {
            if(indexProdusSelectat != -1){
                firebaseService.deleteQuestion(listaQuestions.get(indexProdusSelectat));
            }
        };
    }

    private AdapterView.OnItemClickListener questionSelectatEventListener() {
        return (adapterView, view, position, param) -> {
            indexProdusSelectat = position;
            Question question = listaQuestions.get(position);
            etQuestion.setText(question.getText());
        };
    }
}