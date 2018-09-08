package br.com.andersonv.famousmovies.activity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import br.com.andersonv.famousmovies.R;
import br.com.andersonv.famousmovies.data.Movie;

public class MovieDetailActivity extends AppCompatActivity {

    private ImageView ivBackdrop;
    private ImageView ivPoster;
    private TextView tvTitle;
    private TextView tvRelase;
    private TextView tvVoteAverage;

    private static String IMAGE_URL = "http://image.tmdb.org/t/p/w185/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ivBackdrop = (ImageView) findViewById(R.id.ivBackdrop);
        ivPoster = (ImageView) findViewById(R.id.ivPoster);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvRelase = (TextView) findViewById(R.id.tvRelease);
        tvVoteAverage = (TextView) findViewById(R.id.tvVoteAverage);

        Intent intent = getIntent();

        if(intent.hasExtra(Intent.EXTRA_INTENT)){
            Movie movie = intent.getParcelableExtra(Intent.EXTRA_INTENT);

            Picasso.with(this)
                    .load(IMAGE_URL + movie.backdropPath)
                    .into(ivBackdrop);

            Picasso.with(this)
                    .load(IMAGE_URL + movie.posterPath)
                    .into(ivPoster);

            tvTitle.setText(movie.title);
            tvRelase.setText(movie.releaseDate);
            tvVoteAverage.setText(movie.voteAverage.toString());
        }

    }
}
