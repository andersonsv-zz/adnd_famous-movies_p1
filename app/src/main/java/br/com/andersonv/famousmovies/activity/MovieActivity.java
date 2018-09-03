package br.com.andersonv.famousmovies.activity;

import android.os.Parcelable;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.andersonv.famousmovies.R;
import br.com.andersonv.famousmovies.adapter.MovieRecyclerViewAdapter;
import br.com.andersonv.famousmovies.data.Movie;

public class MovieActivity extends AppCompatActivity  implements MovieRecyclerViewAdapter.ItemClickListener {

    private RecyclerView rvMovies;
    private static final int NUMBER_OF_COLUMNS = 2;
    private MovieRecyclerViewAdapter adapter;
    private List<Movie> movies = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        if(savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            movies = new ArrayList<Movie>(movies);
        }
        else {
            movies = savedInstanceState.getParcelableArrayList("movies");
        }

        rvMovies = (RecyclerView) findViewById(R.id.rvMovies);

        movies.add(new Movie(1L,  "http://image.tmdb.org/t/p/w185/yPisjyLweCl1tbgwgtzBCNCBle.jpg"));
        movies.add(new Movie(2L, "http://image.tmdb.org/t/p/w185/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"));
        movies.add(new Movie(1L,  "http://image.tmdb.org/t/p/w185/7WsyChQLEftFiDOVTGkv3hFpyyt.jpg"));
        movies.add(new Movie(2L, "http://image.tmdb.org/t/p/w185/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"));
        movies.add(new Movie(1L , "http://image.tmdb.org/t/p/w185/yPisjyLweCl1tbgwgtzBCNCBle.jpg"));
        movies.add(new Movie(2L , "http://image.tmdb.org/t/p/w185/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"));
        movies.add(new Movie(1L , "http://image.tmdb.org/t/p/w185/yPisjyLweCl1tbgwgtzBCNCBle.jpg"));
        movies.add(new Movie(2L , "http://image.tmdb.org/t/p/w185/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"));
        movies.add(new Movie(1L, "http://image.tmdb.org/t/p/w185/yPisjyLweCl1tbgwgtzBCNCBle.jpg"));
        movies.add(new Movie(2L, "http://image.tmdb.org/t/p/w185/uxzzxijgPIY7slzFvMotPv8wjKA.jpg"));

        rvMovies.setLayoutManager(new GridLayoutManager(this, NUMBER_OF_COLUMNS));
        adapter = new MovieRecyclerViewAdapter(this, movies);
      //  adapter.setClickListener(this);
        rvMovies.setAdapter(adapter);
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
    public void onItemClick(View view, int position) {

    }
}
