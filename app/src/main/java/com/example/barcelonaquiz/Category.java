package com.example.barcelonaquiz;

import android.app.Application;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

public class Category extends Application {
    public static final int UEFA_CHAMPIONS_LEAGUE = 1;
    public static final int UEFA_EUROPE_LEAGUE = 2;
    public static final int UEFA_SUPER_CUP = 3;
    public static final int FIFA_CLUB_WORLD_CUP = 4;
    public static final int LA_LIGA = 5;
    public static final int SPANISH_CUP = 6;
    public static final int SPANISH_SUPER_CUP = 7;
    public static final int HISTORY = 8;
    public static final int STADIUM = 9;
    public static final int RECORDS = 10;

    private static final Map<Integer, String> ID_TO_NAME = new HashMap<>();
    private static final Map<String, Integer> NAME_TO_ID = new HashMap<>();

    static {
        ID_TO_NAME.put(UEFA_CHAMPIONS_LEAGUE, "UEFA Champions League");
        ID_TO_NAME.put(UEFA_EUROPE_LEAGUE, "UEFA Europe League");
        ID_TO_NAME.put(UEFA_SUPER_CUP, "UEFA Super Cup");
        ID_TO_NAME.put(FIFA_CLUB_WORLD_CUP, "FIFA Club World Cup");
        ID_TO_NAME.put(LA_LIGA, "La Liga");
        ID_TO_NAME.put(SPANISH_CUP, "Spanish Cup");
        ID_TO_NAME.put(SPANISH_SUPER_CUP, "Spanish Super Cup");
        ID_TO_NAME.put(HISTORY, "History");
        ID_TO_NAME.put(STADIUM, "Stadium");
        ID_TO_NAME.put(RECORDS, "Records");

        for (Map.Entry<Integer, String> entry : ID_TO_NAME.entrySet()) {
            NAME_TO_ID.put(entry.getValue(), entry.getKey());
        }
    }

    private int id;
    private String name;

    public Category(){}

    public Category(int id) {
        setId(id);
    }

    public Category(String name) {
        setName(name);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        this.name = ID_TO_NAME.get(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        Integer resolvedId = NAME_TO_ID.get(name);
        this.id = resolvedId != null ? resolvedId : 0;
    }

    @NonNull
    @Override
    public String toString() {
        return name != null ? name : "";
    }
}
