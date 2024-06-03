package com.example.myapplication.Model;

public class Theme {
    private int id;
    private String name;
    private int packetsCount;
    private String description = "Тут описание темы";

    public int getId() {
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPacketsCount() {
        return packetsCount;
    }

    public void setPacketsCount(int packetsCount) {
        this.packetsCount = packetsCount;
    }
}
