package com.niit.project;

public class PopularFoods {
    private int id;
    private String name;
    private double rating;
    private int image;

    public PopularFoods(int id, String name, double rating, int image) {
        this.id = id;
        this.name = name;
        this.rating = rating;
        this.image = image;
    }

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}