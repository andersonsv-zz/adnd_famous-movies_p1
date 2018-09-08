package br.com.andersonv.famousmovies.adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.andersonv.famousmovies.R;
import br.com.andersonv.famousmovies.data.Movie;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<MovieRecyclerViewAdapter.ViewHolder> {

    private static String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    private List<Movie> mData;
    private LayoutInflater mInflater;
    private final MovieRecyclerOnClickHandler mClickHandler;

    public interface MovieRecyclerOnClickHandler {
        void onClick(Movie movie);
    }
    //
    public MovieRecyclerViewAdapter(Context context, List<Movie> data, MovieRecyclerOnClickHandler clickHandler) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.mClickHandler = clickHandler;
    }


    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.movie_item, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each cell
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = mData.get(position);
        Picasso.with(mInflater.getContext())
                .load(IMAGE_URL + movie.posterPath)
                .into(holder.ivMovieImage);

    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ivMovieImage;

        ViewHolder(View itemView) {
            super(itemView);
            ivMovieImage = itemView.findViewById(R.id.ivMovieImage);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
                int adapterPosition = getAdapterPosition();
                Movie movie = mData.get(adapterPosition);
                mClickHandler.onClick(movie);
        }
    }
}
