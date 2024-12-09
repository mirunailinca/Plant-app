package com.example.plantapp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserParser {
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String GENDER = "gender";
    private static final String BIRTH_DATE = "birthDate";

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    public static List<User> parsareJson(String json){
        try{
            JSONArray jsonArray= new JSONArray(json);
            return parsareUsers(jsonArray);
        }catch(JSONException e){
            throw new RuntimeException(e);
        }
    }

    private static List<User> parsareUsers(JSONArray jsonArray) throws JSONException{
        List<User> users = new ArrayList<>();
        for(int i = 0; i< jsonArray.length(); i++){
            users.add(parsareUser(jsonArray.getJSONObject(i)));
        }

        return users;
    }

    private static User parsareUser(JSONObject jsonObject) throws JSONException{
        String firstName = jsonObject.getString(FIRSTNAME);
        String lastName = jsonObject.getString(LASTNAME);
        String email = jsonObject.getString(EMAIL);
        String password = jsonObject.getString(PASSWORD);
        Gender gender = Gender.valueOf(jsonObject.getString(GENDER).toUpperCase());
        Date birthDate = null;
        try{
            birthDate = DATE_FORMAT.parse(jsonObject.getString(BIRTH_DATE));

        }catch (ParseException e){
            throw new JSONException("Invalid date format for birthdate: " + e.getMessage());
        }

        return new User(firstName,lastName,password,email,gender,birthDate);
    }


}
