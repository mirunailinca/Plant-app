package com.example.plantapp;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseService {
    private final DatabaseReference reference;
    private static FirebaseService firebaseService;

    private FirebaseService(){
        reference = FirebaseDatabase.getInstance().getReference();
    }
    public static FirebaseService getInstance(){
        if(firebaseService == null){
            synchronized (FirebaseService.class){
                if(firebaseService == null){
                    firebaseService = new FirebaseService();
                }
            }
        }
        return firebaseService;
    }

    public void insert(Review review){
        if(review == null || review.getId() != null){
            return;
        }
        String id = reference.child("Reviews").push().getKey(); // Salvează în nodul "Reviews"
        review.setId(id);

        reference.child("Reviews").child(review.getId()).setValue(review);
    }

    public void update(Review review){
        if(review == null || review.getId() == null){
            return;
        }

        reference.child("Reviews").child(review.getId()).setValue(review);
    }

    public void delete(Review review){
        if(review == null || review.getId() == null){
            return;
        }

        reference.child("Reviews").child(review.getId()).removeValue();
    }

    public void addReviewListener(Callback<List<Review>> callback){
        reference.child("Reviews").addValueEventListener(new ValueEventListener() { // Ascultă doar nodul "Reviews"
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Review> reviews = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Review review = data.getValue(Review.class);
                    if (review != null) {
                        reviews.add(review);
                    }
                }
                callback.runOnUI(reviews);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("firebase","Eroare la accesarea review-urilor!");
            }
        });
    }

    //////////////

    public void insertQuestion(Question question){
        if(question == null || question.getId() != null){
            return;
        }
        String id = reference.child("Questions").push().getKey(); // Salvează în nodul "Questions"
        question.setId(id);

        reference.child("Questions").child(question.getId()).setValue(question);
    }

    public void updateQuestion(Question question){
        if(question == null || question.getId() == null){
            return;
        }

        reference.child("Questions").child(question.getId()).setValue(question);
    }

    public void deleteQuestion(Question question){
        if(question == null || question.getId() == null){
            return;
        }

        reference.child("Questions").child(question.getId()).removeValue();
    }

    public void addListenerQuestion(Callback<List<Question>> callback){
        reference.child("Questions").addValueEventListener(new ValueEventListener() { // Ascultă doar nodul "Questions"
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<Question> questions = new ArrayList<>();
                for (DataSnapshot data : snapshot.getChildren()) {
                    Question question = data.getValue(Question.class);
                    if (question != null) {
                        questions.add(question);
                    }
                }
                callback.runOnUI(questions);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("firebase","Eroare la accesarea întrebărilor!");
            }
        });
    }

}
