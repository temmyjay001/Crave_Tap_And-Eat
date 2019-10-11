package com.niit.project;

public class Foods {

    private int id;
    private String name, shortDesc;
    private double rating;
    private int image,heart;

    public Foods(int id, String name, String shortDesc, double rating, int image) {
        this.id = id;
        this.name = name;
        this.shortDesc = shortDesc;
        this.rating = rating;
        this.image = image;
        this.heart = heart;
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

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
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
