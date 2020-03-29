package com.example.transmusicales.ui.send;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.transmusicales.Artist;
import com.example.transmusicales.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.LinkedList;

public class SendFragment extends Fragment {

    // Firebase reference
    private FirebaseDatabase mFireDataBase;
    private DatabaseReference mArtisteDatabaseReference;
    private FirebaseRecyclerAdapter<Artist, SendFragment.ViewHolder> mAdapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ChildEventListener mChildEventListener;
    private Artist artist;
    private LinkedList<String> comments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comments = new LinkedList<>();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_send, container, false);

        // STEP 2 : access the DB...
        mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference
        Query baseQuery = mFireDataBase.getReference().child("artistes");

        mArtisteDatabaseReference = mFireDataBase.getReference().child("artistes");

        // STEP 2.2: get the recycler view
        recyclerView = root.findViewById(R.id.comment_recycler);
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

        return root;
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
                    updateArtist(artist);
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
        if (comments != null) {
            if (updatedArtist != null) {
                updatedArtist.setComments(comments);
                artist = updatedArtist;
                Log.i("TAG", "updated from DB for " + updatedArtist.getFields().getName().trim());
            }
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        private TextView comment;
        private boolean drap = true;

        ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_comments);
            comment = itemView.findViewById(R.id.comment);
        }

        void setComment(String commentValue) {
            comment.setText(commentValue);
        }

        public void onCommentAdded(Artist artist, DatabaseReference mArtisteDatabaseReference, String newComment) {
            if (comment != null) {
                artist.addComment(newComment);
                mArtisteDatabaseReference.child(artist.getUid()).child("comments").setValue(artist.getComments());
            }
        }
    }

    private FirebaseRecyclerAdapter<Artist, SendFragment.ViewHolder> createFirebaseAdapter(Query baseQuery, View root) {
        FirebaseRecyclerOptions<Artist> options =
                new FirebaseRecyclerOptions.Builder<Artist>()
                        .setQuery(baseQuery, Artist.class)
                        .build();
        mAdapter =
                new FirebaseRecyclerAdapter<Artist, SendFragment.ViewHolder>(options) {
                    @NonNull
                    @Override
                    public SendFragment.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        return new SendFragment.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_item, parent, false));
                    }

                    @Override
                    public void onError(@NonNull DatabaseError databaseError) {
                        mSwipeRefreshLayout.setRefreshing(false);
                        databaseError.toException().printStackTrace();
                        // Handle Error

                    }

                    /**
                     * @param holder
                     * @param position
                     * @param model    the model object containing the data that should be used to populate the view.
                     */
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Artist model) {
                        for (String comment : artist.getComments()) {
                            // TODO
                            holder.setComment(comment);
                        }
                    }
                };
        return mAdapter;
    }

}