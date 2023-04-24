package com.example.task2628;

import jakarta.persistence.*;

import java.util.ArrayList;

@Entity
public class Cargo {
    private int id;
    private String name;
    private String content;
    private String citySend;
    private String dateSend;
    private String cityArrive;
    private String dateArrive;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId(){
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getCitySend() {
        return citySend;
    }

    public void setCitySend(String citySend) {
        this.citySend = citySend;
    }

    public String getDateSend() {
        return dateSend;
    }

    public void setDateSend(String dateSend) {
        this.dateSend = dateSend;
    }

    public String getCityArrive() {
        return cityArrive;
    }

    public void setCityArrive(String cityArrive) {
        this.cityArrive = cityArrive;
    }

    public String getDateArrive() {
        return dateArrive;
    }

    public void setDateArrive(String dateArrive) {
        this.dateArrive = dateArrive;
    }
    public static ArrayList<String> numFields() {
        ArrayList<String> res = new ArrayList<>();
        res.add("id");
        res.add("name");
        res.add("content");
        res.add("citySend");
        res.add("dateSend");
        res.add("cityArrive");
        res.add("dateArrive");

        return res;
    }
}
