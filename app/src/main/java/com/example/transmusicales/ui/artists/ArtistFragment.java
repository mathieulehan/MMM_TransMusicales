package com.example.transmusicales.ui.artists;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.transmusicales.Artist;
import com.example.transmusicales.R;
import com.firebase.ui.database.paging.DatabasePagingOptions;
import com.firebase.ui.database.paging.FirebaseRecyclerPagingAdapter;
import com.firebase.ui.database.paging.LoadingState;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class ArtistFragment extends Fragment {

    // Firebase reference
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mArtisteDatabaseReference;
    private RecyclerView recyclerView;
    private SearchView searchView;
    List<Artist> artists;
    //STEP 4: child event lister.
    private FirebaseRecyclerPagingAdapter<Artist, ViewHolder> mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ChildEventListener mChildEventListener;
    private int pastVisiblesItems, visibleItemCount, totalItemCount;
    private boolean userScrolled = false;
    private boolean loading = true;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        artists = new ArrayList<>();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_artists, container, false);

        //Test recherche
        //SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = root.findViewById(R.id.search_artists);
        //searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                setFilter(query, root);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                setFilter(query, root);
                return false;
            }
        });

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference
        Query baseQuery = mFireDataBase.getReference().child("artistes");

        mArtisteDatabaseReference = mFireDataBase.getReference().child("artistes");

        // STEP 2.2: get the recycler view
        recyclerView = root.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(recyclerView.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        mSwipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);

        mAdapter = createFirebaseAdapter(baseQuery, root);

        // STEP 2.3: create and set the adapter
        recyclerView.setAdapter(mAdapter);
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

    private FirebaseRecyclerPagingAdapter<Artist, ViewHolder> createFirebaseAdapter(Query baseQuery, View root) {
        // This configuration comes from the Paging Support Library
        // https://developer.android.com/reference/android/arch/paging/PagedList.Config.html
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setPrefetchDistance(10)
                .setPageSize(20)
                .build();
        // The options for the adapter combine the paging configuration with query information
        // and application-specific options for lifecycle, etc.
        DatabasePagingOptions<Artist> options = new DatabasePagingOptions.Builder<Artist>()
                .setLifecycleOwner(this)
                .setQuery(baseQuery, config, Artist.class)
                .build();
        mAdapter =
                new FirebaseRecyclerPagingAdapter<Artist, ViewHolder>(options) {
                    @NonNull
                    @Override
                    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.artist_item, parent, false));
                    }

                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder,
                                                    int position,
                                                    @NonNull Artist artiste) {
                        for (Artist a: artists) {
                            if(artiste.getRecordid().equals(a.getRecordid())){
                                artiste.setUid(a.getUid());
                            }
                        }

                        holder.setArtist(artiste);
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Dialog d = new Dialog(root.getContext());
                                d.setContentView(R.layout.selected_artist_info);
                                TextView artistInfos = d.findViewById(R.id.infos);
                                artistInfos.setText(artiste.toString());
                                d.show();
                            }
                        });

                        holder.onUpdateMark(artiste,mArtisteDatabaseReference);
                    }

                    @Override
                    protected void onError(@NonNull DatabaseError databaseError) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        databaseError.toException().printStackTrace();
                        // Handle Error

                    }

                    /**
                     * Called whenever the loading state of the adapter changes.
                     * <p>
                     * When the state is {@link LoadingState#ERROR} the adapter will stop loading any data
                     *
                     * @param state
                     */
                    @Override
                    protected void onLoadingStateChanged(@NonNull LoadingState state) {
                        mSwipeRefreshLayout = root.findViewById(R.id.swipe_refresh_layout);
                        switch (state) {
                            case LOADING_INITIAL:
                            case LOADING_MORE:
                                // Do your loading animation
                                mSwipeRefreshLayout.setRefreshing(true);
                                break;

                            case LOADED:
                            case FINISHED:
                                //Reached end of Data set
                                // Stop Animation
                                mSwipeRefreshLayout.setRefreshing(false);
                                break;

                            case ERROR:
                                retry();
                                break;
                        }
                    }
                };
        return mAdapter;
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
                    updateArtist(artist);
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            };
            mArtisteDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    private void updateArtist(Artist updatedArtist) {
        if (artistsFiltred != null) {
            Artist oldArtist = artistsFiltred.stream()
                    .filter(c -> (updatedArtist.getUid().equals(c.getUid())))
                    .findFirst()
                    .orElse(null);
            if (oldArtist != null) {
                artistsFiltred.set(artistsFiltred.indexOf(oldArtist), updatedArtist);
                Log.i("TAG", "updated from DB for " + updatedArtist.getFields().getArtistes().trim() + " = " + updatedArtist.getMark());
            }

        Artist oldArtist = artists.stream()
                .filter(c -> (updatedArtist.getUid().equals(c.getUid())))
                .findFirst()
                .orElse(null);
        if (oldArtist != null) {
            artists.set(artists.indexOf(oldArtist), updatedArtist);
            Log.i("TAG","updated likes from DB for "+updatedArtist.getFields().getArtistes().trim()+" = "+ updatedArtist.getMark());
        }
    }

    public void setFilter(String searchText, View root) {

        Query baseQuery = mFireDataBase.getReference("fields").child("artistes").startAt(searchText).endAt(searchText+"\uf8ff");

        mAdapter = createFirebaseAdapter(baseQuery, root);

        mAdapter.startListening();
        mAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(mAdapter);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        private TextView artistName;
        private RatingBar artisteMark;
        private TextView artistePremiereSalle;
        private ImageView artisteSpotify;
        private ImageView artisteDeezer;
        private ImageView artisteGMaps;
        private TextView artisteMoyenne;
        private SearchView searchView;
        private boolean drap = true;

        ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_root);
            artistName = itemView.findViewById(R.id.artiste_name);
            artisteMark = itemView.findViewById(R.id.mark);
            artistePremiereSalle = itemView.findViewById(R.id.premiere_salle);
            artisteSpotify = itemView.findViewById(R.id.spotify);
            artisteDeezer = itemView.findViewById(R.id.deezer);
            artisteGMaps = itemView.findViewById(R.id.gmaps);
            artisteMoyenne = itemView.findViewById(R.id.avg);
        }

        void setArtist(Artist artist) {
            artistName.setText(artist.getFields().getArtistes().trim());
            if(drap){
                if(artist.fields.getMark().length()>=4){
                    artisteMoyenne.setText(artist.fields.getMark().substring(0,4));
                }else{
                    artisteMoyenne.setText(artist.fields.getMark());
                }
                drap = false;
            }
            artistePremiereSalle.setText(artist.getFields().getPremiere_salle().trim());
            if (artist.getFields().getSpotify()!= null && !artist.getFields().getSpotify().isEmpty()) {
                artisteSpotify.setVisibility(View.VISIBLE);
                artisteSpotify.setTag(artist.getFields().getSpotify());
            }
            else { artisteSpotify.setVisibility(View.GONE); }

            if (artist.getFields().getDeezer()!= null && !artist.getFields().getDeezer().isEmpty()) {
                artisteDeezer.setVisibility(View.VISIBLE);
                artisteDeezer.setTag(artist.getFields().getDeezer());
            }
            else { artisteDeezer.setVisibility(View.GONE); }

            if (artist.getGeometry()!= null && artist.getGeometry().getCoordinates().get(0) != null && artist.getGeometry().getCoordinates().get(1) != null) {
                artisteGMaps.setVisibility(View.VISIBLE);
                artisteGMaps.setTag(artist.getGeometry().getCoordinates().get(1) + "," + artist.getGeometry().getCoordinates().get(0));
            }
            else { artisteGMaps.setVisibility(View.GONE); }
        }

        public void onUpdateMark(Artist artist, DatabaseReference mArtisteDatabaseReference) {
            RatingBar myMarkStar = artisteMark;
            myMarkStar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                    if(rating != 0.0){
                        artist.fields.setNbpersonne(String.valueOf(Integer.parseInt(artist.getNbPersonne())+1));
                        Float moy = (Float.parseFloat(artist.fields.getMark())*Float.parseFloat(artist.fields.getNbpersonne())+rating)/(Float.parseFloat(artist.fields.getNbpersonne())+1);
                        artist.fields.setMark(String.valueOf(rating));
                        System.out.println("Artiste : "+rating);
                        mArtisteDatabaseReference.child(artist.getUid()).child("fields").child("mark").setValue(String.valueOf(moy));
                        mArtisteDatabaseReference.child(artist.getUid()).child("fields").child("nbpersonne").setValue(artist.fields.getNbpersonne());
                        if(String.valueOf(moy).length()>=4){
                            artisteMoyenne.setText(String.valueOf(moy).substring(0,4));
                        }else{
                            artisteMoyenne.setText(String.valueOf(moy));
                        }
                    }
                }
            });
        }
    }
}