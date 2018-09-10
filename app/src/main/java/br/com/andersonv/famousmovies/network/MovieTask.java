package br.com.andersonv.famousmovies.network;


import android.os.AsyncTask;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import br.com.andersonv.famousmovies.data.Movie;
import br.com.andersonv.famousmovies.data.MovieSearch;
import br.com.andersonv.famousmovies.util.MovieJsonUtils;
import br.com.andersonv.famousmovies.util.NetworkUtils;

public class MovieTask extends AsyncTask<String, Void, List<Movie>> {

    List<Movie> movies = new ArrayList<>();

    private OnTaskCompleted taskCompleted;
    private MovieSearch movieSearch;
    private Integer page;
    private String locale;
    private String apiKey;

    public MovieTask(OnTaskCompleted activityContext, final MovieSearch movieSearch, final Integer page, final String locale, final String apiKey) {
        this.taskCompleted = activityContext;
        this.movieSearch = movieSearch;
        this.page = page;
        this.locale = locale;
        this.apiKey = apiKey;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Movie> doInBackground(String... params) {

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
        taskCompleted.onTaskCompleted(moviesData);
    }
}
