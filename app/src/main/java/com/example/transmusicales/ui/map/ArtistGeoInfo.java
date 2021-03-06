package com.example.transmusicales.ui.map;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

class ArtistGeoInfo implements ClusterItem {
    private double lat;
    private double lng;
    private String artisteName;

    public ArtistGeoInfo(double lat, double lng, String artisteName) {
        this.lat = lat;
        this.lng = lng;
        this.artisteName = artisteName;
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

    String getArtisteName() {
        return artisteName;
    }
}
