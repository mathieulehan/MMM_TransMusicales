package com.example.transmusicales.ui.map;

import android.location.Geocoder;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.transmusicales.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;

import org.json.JSONException;

import java.io.InputStream;
import java.util.List;
import java.util.Objects;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class MapFragment extends Fragment implements OnMapReadyCallback {

    MapView mMapView;
    private GoogleMap googleMap;
    LatLngBounds.Builder builder  = new LatLngBounds.Builder();
    private ClusterManager<ArtistGeoInfo> mClusterManager;
    GoogleMap gMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

            // Permission is not granted
            View rootView = inflater.inflate(R.layout.fragment_gallery, container, false);

            mMapView = rootView.findViewById(R.id.mapView);
            mMapView.onCreate(savedInstanceState);



            try {
                MapsInitializer.initialize(getActivity().getApplicationContext());
            } catch (Exception e) {
                e.printStackTrace();
            }

            // Needed to configure the map
            mMapView.getMapAsync(this);
            // Needed to get the map to display immediately
            mMapView.onResume();

            return rootView;
        }

        @Override
        public void onResume () {
            super.onResume();
            mMapView.onResume();
        }

        @Override
        public void onPause () {
            super.onPause();
            mMapView.onPause();
        }

        @Override
        public void onDestroy () {
            super.onDestroy();
            mMapView.onDestroy();
        }

        @Override
        public void onLowMemory () {
            super.onLowMemory();
            mMapView.onLowMemory();
        }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.gMap = googleMap;
        // Custom map styling
        boolean success = googleMap.setMapStyle(new MapStyleOptions(getResources()
                .getString(R.string.style_json)));

        if (!success) {
            Log.e(TAG, "Style parsing failed.");
        }
        // Position the map's camera near Rennes, France.
        LatLng latLng = new LatLng(48.0833, -1.6833);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        // Need to call MapsInitializer before doing any CameraUpdateFactory calls
        MapsInitializer.initialize(Objects.requireNonNull(this.getActivity()));

        Geocoder geocoder = new Geocoder(getActivity());

        Marker marker = googleMap.addMarker(new MarkerOptions().position(latLng).title("Rennes"));
        builder.include(marker.getPosition());

        LatLngBounds bounds = builder.build();
        int padding = 0;
        CameraUpdate cu = CameraUpdateFactory.newLatLngZoom(new MarkerOptions().position(latLng).title("Rennes").getPosition(),5F);
        googleMap.animateCamera(cu);

        this.configureClustering();
    }

    private void configureClustering() {
        mClusterManager = new ClusterManager<>(Objects.requireNonNull(getContext()), gMap);

        gMap.setOnCameraIdleListener(mClusterManager);
        try {
            readMarkers();
        } catch (JSONException e) {
            Toast.makeText(getContext(), "Problem reading list of markers.", Toast.LENGTH_LONG).show();
        }
    }

    private void readMarkers() throws JSONException {
        InputStream inputStream = getResources().openRawResource(R.raw.transmusicales);
        List<ArtistGeoInfo> items = new ArtistInfoReader().read(inputStream);
        for (ArtistGeoInfo item : items) {
            LatLng position = item.getPosition();
            double lat = position.latitude;
            double lng = position.longitude;
            ArtistGeoInfo offsetItem = new ArtistGeoInfo(lat, lng, item.getArtisteName());
            mClusterManager.addItem(offsetItem);
        }
    }
}