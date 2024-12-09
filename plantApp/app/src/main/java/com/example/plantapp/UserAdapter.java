package com.example.plantapp;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class UserAdapter extends ArrayAdapter<User> {
    private Context context;
    private List<User> userList;
    private int layoutId;
    private LayoutInflater inflater;

    public UserAdapter(@NonNull Context context, int resource, @NonNull List<User> objects, LayoutInflater inflater) {
        super(context,resource,objects);

        this.context = context;
        this.layoutId = resource;
        this.userList = objects;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(layoutId,parent,false);
        User user = userList.get(position);

        TextView tvFirstName = view.findViewById(R.id.textView);
        TextView tvLastName = view.findViewById(R.id.textView2);
        TextView tvPassword = view.findViewById(R.id.textView3);
        TextView tvEmail = view.findViewById(R.id.textView4);
        TextView tvGender = view.findViewById(R.id.textView5);
        TextView tvBirthDate = view.findViewById(R.id.textView6);

        tvFirstName.setText(user.getFirstName());
        tvLastName.setText(user.getLastName());
        tvPassword.setText(user.getPassword());
        tvEmail.setText(user.getEmail());
        tvGender.setText(String.valueOf(user.getGender()));
        tvBirthDate.setText(String.valueOf(user.getBirthDate()));

        return view;
    }

}
