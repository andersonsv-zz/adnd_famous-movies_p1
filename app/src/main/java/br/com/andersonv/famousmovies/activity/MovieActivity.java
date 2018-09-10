package br.com.andersonv.famousmovies.activity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.andersonv.famousmovies.R;
import br.com.andersonv.famousmovies.adapter.MovieRecyclerViewAdapter;
import br.com.andersonv.famousmovies.data.Movie;
import br.com.andersonv.famousmovies.data.MovieSearch;
import br.com.andersonv.famousmovies.network.MovieTask;
import br.com.andersonv.famousmovies.network.OnTaskCompleted;
import br.com.andersonv.famousmovies.util.MovieJsonUtils;
import br.com.andersonv.famousmovies.util.NetworkUtils;

public class MovieActivity extends AppCompatActivity implements MovieRecyclerViewAdapter.MovieRecyclerOnClickHandler, OnTaskCompleted{

    //component config
    private static final int NUMBER_OF_COLUMS = 2;
    private static final String SAVE_STATE_OBJECT_NAME = "movies";
    private String locale;

    private List<Movie> movies = new ArrayList<>();
    //components
    private TextView mErrorMessageDisplay;
    private LinearLayout mLlInternetAccessError;
    private ProgressBar mLoadingIndicator;
    private RecyclerView rvMovies;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        this.setTitle(R.string.top_rated);

        mLoadingIndicator = findViewById(R.id.pbIndicador);
        rvMovies = findViewById(R.id.rvMovies);
        mErrorMessageDisplay = findViewById(R.id.tvErrorMessage);
        mLlInternetAccessError = findViewById(R.id.llInternetAccessError);

        if (savedInstanceState == null || !savedInstanceState.containsKey(SAVE_STATE_OBJECT_NAME)) {
            movies = new ArrayList<>(movies);
        } else {
            movies = savedInstanceState.getParcelableArrayList(SAVE_STATE_OBJECT_NAME);
        }

        context = this;
        rvMovies.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMS));
        rvMovies.setHasFixedSize(true);

        loadMovieData(MovieSearch.TOP_RATED);

    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(SAVE_STATE_OBJECT_NAME, (ArrayList<? extends Parcelable>) movies);
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
            this.setTitle(R.string.top_rated);
            loadMovieData(MovieSearch.TOP_RATED);

            return true;
        }

        if (id == R.id.list_movies_mostpopular) {
            this.setTitle(R.string.most_popular);
            loadMovieData(MovieSearch.MOST_POPULAR);

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void showMovieDataView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        rvMovies.setVisibility(View.VISIBLE);
        mLlInternetAccessError.setVisibility(View.INVISIBLE);
    }

    private void showErrorMessage() {
        rvMovies.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    private void loadMovieData(MovieSearch movieSearch) {

        showMovieDataView();

        //clean list
        rvMovies.setAdapter(null);

        //check internet access
        if(!NetworkUtils.isNetworkConnected(this)) {
            mLlInternetAccessError.setVisibility(View.VISIBLE);
            mErrorMessageDisplay.setVisibility(View.INVISIBLE);

          /*  Snackbar snackbar = Snackbar.
                    .make(, "Message is deleted", Snackbar.LENGTH_LONG)
                    .setAction("UNDO", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Snackbar snackbar1 = Snackbar.make(coordinatorLayout, "Message is restored!", Snackbar.LENGTH_SHORT);
                            snackbar1.show();
                        }
                    });

            snackbar.show();*/
        }else {
            final String apiKey = getResources().getString(R.string.moviedb);

            locale = Locale.getDefault().toString().replace("_", "-");

            mLoadingIndicator.setVisibility(View.VISIBLE);
            MovieTask task = new MovieTask(MovieActivity.this, movieSearch, 1, locale, apiKey);
            task.execute();
        }
    }

    @Override
    public void onClick(Movie movie) {

        Context context = this;
        Class destinationClass = MovieDetailActivity.class;

        Intent intent = new Intent(context, destinationClass);
        intent.putExtra(Intent.EXTRA_INTENT, movie);

        startActivity(intent);
    }


    @Override
    public void onTaskCompleted(List<Movie> response) {
        mLoadingIndicator.setVisibility(View.INVISIBLE);

        if (response != null) {
            showMovieDataView();

            MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter(context, response, MovieActivity.this);
            rvMovies.setAdapter(adapter);

        } else {
            showErrorMessage();
        }
    }

    /*private class MovieTask extends AsyncTask<String, Void, List<Movie>> {

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

            MovieSearch movieSearch = MovieSearch.valueOf(params[0]);
            Integer page = Integer.parseInt(params[1]);
            String locale = params[2];
            String apiKey = params[3];

            URL movieRequestUrl = NetworkUtils.buildMovies(movieSearch, page, locale, apiKey);
            try {
                String jsonWeatherResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                movies = MovieJsonUtils.getMovieStringsFromJson(jsonWeatherResponse);

                return movies;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> moviesData) {

            mLoadingIndicator.setVisibility(View.INVISIBLE);

            if (moviesData != null) {
                showMovieDataView();

                MovieRecyclerViewAdapter adapter = new MovieRecyclerViewAdapter(context, moviesData, MovieActivity.this);
                rvMovies.setAdapter(adapter);

            } else {
                showErrorMessage();
            }
        }
    }*/
}