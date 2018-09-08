package br.com.andersonv.famousmovies.activity;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import br.com.andersonv.famousmovies.R;
import br.com.andersonv.famousmovies.adapter.MovieRecyclerViewAdapter;
import br.com.andersonv.famousmovies.data.Movie;
import br.com.andersonv.famousmovies.util.MovieJsonUtils;
import br.com.andersonv.famousmovies.util.NetworkUtils;

public class MovieActivity extends AppCompatActivity  implements MovieRecyclerViewAdapter.ItemClickListener {

    private MovieRecyclerViewAdapter adapter;
    private List<Movie> movies = new ArrayList<>();

    //components
    private TextView mErrorMessageDisplay;
    private ProgressBar mLoadingIndicator;
    private RecyclerView rvMovies;
    private Context context;

    //component config
    private static final int NUMBER_OF_COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        mLoadingIndicator = (ProgressBar) findViewById(R.id.pbIndicador);

        if(savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            movies = new ArrayList<Movie>(movies);
        }
        else {
            movies = savedInstanceState.getParcelableArrayList("movies");
        }

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);

        mErrorMessageDisplay = (TextView) findViewById(R.id.tvErroMessage);

        context = this;
        rvMovies.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        rvMovies.setHasFixedSize(true);

        loadMovieData();
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList("movies", (ArrayList<? extends Parcelable>) movies);
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_list_movies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.list_movies_toprated) {
            loadMovieData();
            return true;
        }

        if (id == R.id.list_movies_mostpopular) {
            loadMovieData();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onItemClick(View view, int position) {

    }

    private void showMovieDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        rvMovies.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        rvMovies.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void loadMovieData() {

        showMovieDataView();

        //clean lista
        rvMovies.setAdapter(null);

        final  String apiKey = getResources().getString(R.string.moviedb);

        new MovieTask().execute("1", Locale.getDefault().getDisplayLanguage(), apiKey);
    }

    public class MovieTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {

            if (params.length == 0) {
                return null;
            }

            //TODO - receive movie category search

            Integer page = Integer.parseInt(params[0]);
            String locale = params[1];
            String apiKey = params[2];

            URL movieRequestUrl = NetworkUtils.buildMoviesTopRatedUrl(page,locale,apiKey);
            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                List<Movie> movies = MovieJsonUtils.getMovieStringsFromJson(MovieActivity.this, jsonWeatherResponse);

                return movies;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> weatherData) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (weatherData != null) {
                showMovieDataView();

                adapter = new MovieRecyclerViewAdapter(context, weatherData);
                //  adapter.setClickListener(this);
                rvMovies.setAdapter(adapter);

            } else {
                showErrorMessage();
            }
        }
    }
}
