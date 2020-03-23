package com.example.transmusicales.ui.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

class ArtistGeoInfo implements ClusterItem {
    private double lat;
    private double lng;
    private String artisteName;
    private String premiereSalle;

    public ArtistGeoInfo(double lat, double lng, String artisteName, String premiereSalle) {
        this.lat = lat;
        this.lng = lng;
        this.artisteName = artisteName;
        this.premiereSalle = premiereSalle;
    }

    public ArtistGeoInfo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public LatLng getPosition() {
        return new LatLng(lat, lng);
    }

    @Override
    public String getTitle() {
        return artisteName;
    }

    @Override
    public String getSnippet() {
        return null;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public void setArtisteName(String artisteName) {
        this.artisteName = artisteName;
    }

    public String getArtisteName() {
        return artisteName;
    }

    public String getPremiereSalle() {
        return premiereSalle;
    }

    public void setPremiereSalle(String premiereSalle) {
        this.premiereSalle = premiereSalle;
    }
}
