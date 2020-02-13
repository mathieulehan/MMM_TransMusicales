package com.example.transmusicales.ui.artists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transmusicales.Artist;
import com.example.transmusicales.ArtistsAdapter;
import com.example.transmusicales.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistFragment extends Fragment implements ArtistsAdapter.ArtistsAdapterListener{

    // Firebase reference
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mArtistsDatabaseReference;
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
//        artists.add(new Artist());
  //      artists.add(new Artist());
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_artists, container, false);

        artists = new ArrayList<>();

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference
        mArtistsDatabaseReference = mFireDataBase.getReference().child("artistes");

        mArtistsDatabaseReference.addValueEventListener(new ValueEventListener() {
                                                            @Override
                                                            public void onDataChange(DataSnapshot dataSnapshot) {
                                                                ArrayList<Artist> a = (ArrayList<Artist>) dataSnapshot.getValue();
                                                                for (Artist newArtist: a) {
                                                                    artists.add(newArtist);
                                                                }
                                                            }

                                                            @Override
                                                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                                                System.out.println("The read failed: " + databaseError.getCode());
                                                            }
                                                        });
                                                            // STEP 2.2: get the recycler view
        recyclerView = root.findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);

        // STEP 2.3: create and set the adapter
        mAdapter = new ArtistsAdapter(getContext(), artists, this);
        recyclerView.setAdapter(mAdapter);

        // STEP 4: listen to any change on the DB
        enableUpdatesFromDB();

        return root;
    }

    // Updating an artist
    @Override
    public void onArtistSelected(Artist artist) {

        // STEP 6.1: Updating the field in the class
        // TODO mettre la note donnée par l'utilisateur en calculant la motenne
       /* artist.getFields().setMark("6.04");

        // STEP 6.2: Updating the field on the Firebase DB
        mArtistsDatabaseReference.child(artist.getUid()).child("mark").setValue(artist.getFields().getMark());*/

    }

    // STEP 4: listen to any change on the DB
    public void enableUpdatesFromDB() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    Artist artist = dataSnapshot.getValue(Artist.class);
                    // don't forget to set the key to identify the Artist!
                    artist.setUid(dataSnapshot.getKey());
                    artists.add(artist);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    Artist artist = dataSnapshot.getValue(Artist.class);
                    artist.setUid(dataSnapshot.getKey());
                    mAdapter.updateArtist(artist);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    //Artist msg = dataSnapshot.getValue(Artist.class);
                    // don't forget to set the key to identify the Artist!
                    mAdapter.removeArtistWithId(dataSnapshot.getKey());
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mArtistsDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }
}
