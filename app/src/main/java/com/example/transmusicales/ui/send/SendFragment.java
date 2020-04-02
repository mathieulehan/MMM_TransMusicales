package com.example.transmusicales.ui.send;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.transmusicales.Artist;
import com.example.transmusicales.Comment;
import com.example.transmusicales.R;
import com.example.transmusicales.ui.artists.ArtistFragment;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SendFragment extends Fragment {

    private DatabaseReference mArtisteDatabaseReference;
    private FirebaseRecyclerAdapter<String, SendFragment.ViewHolder> mAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ChildEventListener mChildEventListener;
    private List<Comment> comments;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comments = new ArrayList<>();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_send, container, false);

        String uid = getArguments().getString("uid");

        // STEP 2 : access the DB...
        // Firebase reference
        FirebaseDatabase mFireDataBase = FirebaseDatabase.getInstance();

        // STEP 2.1: and from the DB, get a reference
        Query baseQuery = mFireDataBase.getReference().child("artistes").child(uid).child("comments");

        mArtisteDatabaseReference = mFireDataBase.getReference().child("artistes").child(uid).child("comments");

        // STEP 2.2: get the recycler view
        RecyclerView recyclerView = root.findViewById(R.id.comment_recycler);
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

        TextInputEditText comment = root.findViewById(R.id.inputComment);
        Button button = root.findViewById(R.id.addCommentButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Comment newComment = new Comment(Objects.requireNonNull(comment.getText()).toString());
                comments.add(newComment);
                addCommentToArtiste(newComment);
            }
        });

        Button retour = root.findViewById(R.id.retour);
        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArtistFragment artistFragment = new ArtistFragment();

                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.addToBackStack(null);
                ft.replace(R.id.nav_host_fragment, artistFragment).commit();
            }
        });

        return root;
    }

    private void addCommentToArtiste(Comment comment) {
        String commentKey = mArtisteDatabaseReference.push().getKey();
        mArtisteDatabaseReference.child(commentKey).setValue(comment.getValue());
    }

    // STEP 4: listen to any change on the DB
    public void enableUpdatesFromDB() {
        if (mChildEventListener == null) {
            mChildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    String comment = dataSnapshot.child("").getValue(String.class);
                    comments.add(new Comment(comment));
                    mAdapter.notifyDataSetChanged();
                }
                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                    // TODO : Edit comment rather than adding a new one
                    String comment = dataSnapshot.child("").getValue(String.class);
                    comments.add(new Comment(comment));
                    mAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    // Not implemented yet
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                    // Not implemented yet
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // Not implemented yet
                }
            };
            mArtisteDatabaseReference.addChildEventListener(mChildEventListener);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LinearLayout root;
        private TextView comment;

        ViewHolder(View itemView) {
            super(itemView);
            root = itemView.findViewById(R.id.list_comments);
            comment = itemView.findViewById(R.id.comment);
        }

        void setComment(String commentValue) {
            comment.setText(commentValue);
        }


        public void onCommentAdded(Artist artist, DatabaseReference mArtisteDatabaseReference, Comment newComment) {
            if (comment != null) {
                mArtisteDatabaseReference.child(artist.getUid()).child("comments").setValue(newComment);
            }
        }
    }

    private FirebaseRecyclerAdapter<String, SendFragment.ViewHolder> createFirebaseAdapter(Query baseQuery, View root) {
        FirebaseRecyclerOptions<String> options =
                new FirebaseRecyclerOptions.Builder<String>()
                        .setQuery(baseQuery, String.class)
                        .build();
        mAdapter =
                new FirebaseRecyclerAdapter<String, SendFragment.ViewHolder>(options) {
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
                     */
                    @Override
                    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull String comment) {
                            holder.setComment(comment);
                    }
                };
        return mAdapter;
    }

}