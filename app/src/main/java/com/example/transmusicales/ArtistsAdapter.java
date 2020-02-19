package com.example.transmusicales;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.MyViewHolder> implements Filterable {
    List<Artist> artistList;
    List<Artist> artistListFiltered;
    private ArtistsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView thumbnail;
        public RatingBar mark;
        public CardView cardView ;

        public MyViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.artiste_name);
            thumbnail = view.findViewById(R.id.thumbnail);
            mark = view.findViewById(R.id.mark);
            cardView = view.findViewById(R.id.cardview_id);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // send selected artist in callback
                    listener.onArtistSelected(artistList.get(getAdapterPosition()));
                }
            });

        }
    }


    public ArtistsAdapter(Context context, List<Artist> artistList, ArtistsAdapterListener listener) {
        this.listener = listener;
        this.artistList = artistList;
        this.artistListFiltered = artistList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.artist_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, final int position) {
        if (artistListFiltered.get(position) != null) {
            final Artist artist = artistListFiltered.get(position);
            if (artist.getFields().getArtistes() != null) {
            viewHolder.name.setText(artist.getFields().getArtistes());
            viewHolder.thumbnail.setVisibility(ImageView.GONE);
        }
        viewHolder.mark.setRating(Float.parseFloat(artist.getFields().getMark()));
        }
    }

    @Override
    public int getItemCount() {
        return artistListFiltered.size();
    }

    public void removeArtistWithId(String uid) {

        artistListFiltered.removeIf(artist ->  (artist.getUid().equals(uid)) );
        artistList.removeIf(artist ->  (artist.getUid().equals(uid)) );
    }

    public void updateArtist(Artist updatedArtist) {

        Artist oldArtist = artistListFiltered.stream()
                .filter(c -> (updatedArtist.getUid().equals(c.getUid())))
                .findFirst()
                .orElse(null);
        if (oldArtist != null) {
            artistListFiltered.set(artistListFiltered.indexOf(oldArtist), updatedArtist);
            //Log.i("TAG","updated mark from DB for "+updatedArtist+" = "+ updatedArtist.getFields().getMark());
        }

        oldArtist = artistList.stream()
                .filter(c -> (updatedArtist.getUid().equals(c.getUid())))
                .findFirst()
                .orElse(null);
        if (oldArtist != null)
            artistList.set(artistList.indexOf(oldArtist),updatedArtist);

    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    artistListFiltered = artistList;
                } else {
                    List<Artist> filteredList = new ArrayList<>();
                    for (Artist row : artistList) {

                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        /*if (row.getFields().getArtistes().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }*/
                    }

                    artistListFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = artistListFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                artistListFiltered = (ArrayList<Artist>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface ArtistsAdapterListener {
        void onArtistSelected(Artist artist);
    }
}