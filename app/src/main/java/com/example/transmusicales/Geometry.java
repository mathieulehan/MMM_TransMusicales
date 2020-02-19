package com.example.transmusicales;

import java.util.ArrayList;

public class Geometry {
    private String type;
    ArrayList<Object> coordinates = new ArrayList<Object>();

    public Geometry(String type, ArrayList<Object> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }

    public Geometry() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public ArrayList<Object> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Object> coordinates) {
        this.coordinates = coordinates;
    }
}
