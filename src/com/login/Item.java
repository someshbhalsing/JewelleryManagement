package com.login;

public class Item {

    private int id;
    private String name;
    private String category;
    private String weight;
    private String status;

    public Item(int id, String name, String category, String weight, String status) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.weight = weight;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getWeight() {
        return weight;
    }

    public String getStatus() {
        return status;
    }
}

