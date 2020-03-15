package com.example.transmusicales.ui.artists;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.transmusicales.Artist;
import com.example.transmusicales.ArtistsAdapter;
import com.example.transmusicales.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ArtistFragment extends Fragment implements ArtistsAdapter.ArtistsAdapterListener{

    // Firebase reference
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mArtistsDatabaseReference;
    private RecyclerView recyclerView;
    private FirebaseRecyclerAdapter  mAdapter;
    private SearchView searchView;
    List<Artist> artists;
    //STEP 4: child event lister.
    private ChildEventListener mChildEventListener;
    int pastVisiblesItems, visibleItemCount, totalItemCount;
    boolean userScrolled = false;
    private boolean loading = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artists = new ArrayList<>();
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
                artists.clear();
                    for (DataSnapshot artist : dataSnapshot.getChildren()) {
                        Artist newArtist = artist.getValue(Artist.class);
                        artists.add(newArtist);
                    }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }
        });

        // STEP 2.2: get the recycler view
        recyclerView = root.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        // STEP 2.3: create and set the adapter
        recyclerView.setAdapter(mAdapter);
        fetch();
        recyclerView.setHasFixedSize(true);
        mAdapter.startListening();
        // STEP 4: listen to any change on the DB
        enableUpdatesFromDB();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                // Here get the child count, item count and visibleitems
                // from layout manager

                visibleItemCount = linearLayoutManager.getChildCount();
                totalItemCount = linearLayoutManager.getItemCount();
                pastVisiblesItems = linearLayoutManager
                        .findFirstVisibleItemPosition();

                // Now check if userScrolled is true and also check if
                // the item is end then update recycler view and set
                // userScrolled to false
                if (userScrolled && (visibleItemCount + pastVisiblesItems) == totalItemCount) {
                    userScrolled = false;
                }
            }
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {

                super.onScrollStateChanged(recyclerView, newState);

                // If scroll state is touch scroll then set userScrolled
                // true
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    userScrolled = true;
                }
            }
        });

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        mAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        mAdapter.stopListening();
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
                    //mAdapter.updateArtist(artist);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    //Artist msg = dataSnapshot.getValue(Artist.class);
                    // don't forget to set the key to identify the Artist!
                    //mAdapter.removeArtistWithId(dataSnapshot.getKey());
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

    private void fetch() {

        Query query = FirebaseDatabase.getInstance()
                .getReference()
                .child("artistes");

        FirebaseRecyclerOptions<Artist> options =
                new FirebaseRecyclerOptions.Builder<Artist>()
                        .setQuery(query, Artist.class)
                        .build();

        mAdapter = new FirebaseRecyclerAdapter<Artist, ViewHolder>(options) {

            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.artist_item, parent, false);

                return new ViewHolder(view);
            }

            @Override
            public void onDataChanged() {
                System.out.println("Data changed");
            }

            @Override
            public void onError(@NonNull DatabaseError databaseError) {
                System.out.println("The read failed: " + databaseError.getCode());
            }

            /**
             * @param holder
             * @param position
             * @param artist    the model object containing the data that should be used to populate the view.
             */
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Artist artist) {
                holder.setArtist(artist);
            }
        };
        recyclerView.setAdapter(mAdapter);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        private final TextView artistName;
        private final RatingBar artisteMark;

        ViewHolder(View itemView) {
            super(itemView);
        root = itemView.findViewById(R.id.list_root);
        artistName = itemView.findViewById(R.id.artiste_name);
        artisteMark = itemView.findViewById(R.id.mark);
        }

        void setArtist(Artist artist) {
            artistName.setText(artist.getFields().getArtistes());
            artisteMark.setRating(1);
        }
    }
}