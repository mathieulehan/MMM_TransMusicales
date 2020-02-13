package com.example.transmusicales;

public class Artist {

    // this uid is required if we want to identify which contact to remove/update
    // it's value is provided by Firebase
    String uid;
    String name;
    String image;
    double mark;

    public Artist() {
    }

    public Artist(String name, String image, double mark) {
        this.name = name;
        this.image = image;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

}
