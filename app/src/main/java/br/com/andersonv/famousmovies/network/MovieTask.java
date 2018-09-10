package br.com.andersonv.famousmovies.network;


import android.os.AsyncTask;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import br.com.andersonv.famousmovies.BuildConfig;
import br.com.andersonv.famousmovies.data.Movie;
import br.com.andersonv.famousmovies.data.MovieSearch;
import br.com.andersonv.famousmovies.data.Movies;
import retrofit2.Call;

public class MovieTask extends AsyncTask<String, Void, List<Movie>> {

    private OnTaskCompleted taskCompleted;
    private MovieSearch movieSearch;
    private Integer page;

    public MovieTask(OnTaskCompleted activityContext, final MovieSearch movieSearch, final Integer page) {
        this.taskCompleted = activityContext;
        this.movieSearch = movieSearch;
        this.page = page;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected List<Movie> doInBackground(String... params) {
        String locale = Locale.getDefault().toString().replace("_", "-");

        MovieService service = RetrofitClientInstance.getRetrofitInstance().create(MovieService.class);
        Call<Movies> call;

        if (MovieSearch.TOP_RATED.equals(movieSearch)) {
            call = service.getTopRated(page, locale, BuildConfig.API_MOVIE_DB_KEY);
        } else {
            call = service.getPopular(page, locale, BuildConfig.API_MOVIE_DB_KEY);
        }

        try {
            return call.execute().body().getMovies();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(List<Movie> moviesData) {
        taskCompleted.onTaskCompleted(moviesData);
    }
}