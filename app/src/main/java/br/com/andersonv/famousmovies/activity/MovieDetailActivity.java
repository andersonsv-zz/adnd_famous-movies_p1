package br.com.andersonv.famousmovies.activity;


import android.content.Intent;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import br.com.andersonv.famousmovies.R;
import br.com.andersonv.famousmovies.data.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";
    private static final String IMAGE_BACKDROP_URL = "http://image.tmdb.org/t/p/w342/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ImageView ivBackdrop = findViewById(R.id.ivBackdrop);
        ImageView ivPoster = findViewById(R.id.ivPoster);
        TextView tvTitle = findViewById(R.id.tvTitle);
        TextView tvRelase = findViewById(R.id.tvRelease);
        TextView tvVoteAverage = findViewById(R.id.tvVoteAverage);
        TextView tvOverview = findViewById(R.id.tvOverview);

        Intent intent = getIntent();

        if(intent.hasExtra(Intent.EXTRA_INTENT)){
            Movie movie = intent.getParcelableExtra(Intent.EXTRA_INTENT);

            Picasso.with(this)
                    .load(IMAGE_BACKDROP_URL + movie.getBackdropPath())
                    .into(ivBackdrop);

            Picasso.with(this)
                    .load(IMAGE_URL + movie.getPosterPath())
                    .into(ivPoster);

            tvTitle.setText(movie.getTitle());
            tvRelase.setText(movie.getReleaseDate());
            tvVoteAverage.setText(String.valueOf(movie.getVoteAverage()));
            tvOverview.setText(movie.getOverview());
        }

    }
}
