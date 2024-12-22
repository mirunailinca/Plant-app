package com.example.plantapp;

import java.io.Serializable;

public class Review implements Serializable {
    private String id;
    private String text;
    private int nota;
    public Review(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    @Override
    public String toString() {
        return "Review{" +
                "text='" + text + '\'' +
                ", nota=" + nota +
                '}';
    }

}
