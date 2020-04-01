package com.example.transmusicales;

public class Comment {

    private String value;

    public Comment() {}

    public Comment(String comment) {
        this.value = comment;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}