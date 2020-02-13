package com.example.transmusicales.ui.artists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transmusicales.Artist;
import com.example.transmusicales.ArtistsAdapter;
import com.example.transmusicales.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ArtistFragment extends Fragment implements ArtistsAdapter.ArtistsAdapterListener{

    // Firebase reference
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mContactsDatabaseReference;
    private RecyclerView recyclerView;
    private ArtistsAdapter mAdapter;
    private SearchView searchView;
    List<Artist> artists;
    //STEP 4: child event lister.
    private ChildEventListener mChildEventListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artists = new ArrayList<>();
        artists.add(new Artist("toto", null, 4.0));
        artists.add(new Artist("toto2", null, 3.0));
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_artists, container, false);

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference on the child node "artists"
        mContactsDatabaseReference = mFireDataBase.getReference().child("transmusicales-7f9d8");

        // STEP 2.2: get the recycler view
        recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // STEP 2.3: create and set the adapter
        artists = new ArrayList<>();
        mAdapter = new ArtistsAdapter(getContext(), artists, this);
        recyclerView.setAdapter(mAdapter);
        return root;
    }

    // Updating an artist
    @Override
    public void onArtistSelected(Artist artist) {

        // STEP 6.1: Updating the field in the class
        // TODO mettre la note donnée par l'utilisateur en calculant la motenne
        artist.setMark(6.04);

        // STEP 6.2: Updating the field on the Firebase DB
        mContactsDatabaseReference.child(artist.getUid()).child("mark").setValue(artist.getMark());

    }
}
