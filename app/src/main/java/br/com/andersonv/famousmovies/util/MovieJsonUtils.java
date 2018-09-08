/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package br.com.andersonv.famousmovies.util;

import android.content.Context;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

import br.com.andersonv.famousmovies.data.Movie;

/**
 * Utility functions to handle OpenWeatherMap JSON data.
 */
public final class MovieJsonUtils {


    public static List<Movie> getMovieStringsFromJson(Context context, String movieJsonStr)
            throws JSONException {

        final String RESULTS = "results";
        final String ID = "id";
        final String IMAGE_PATH = "poster_path";

        final String MESSAGE_CODE = "status_code";


        JSONObject movieJson = new JSONObject(movieJsonStr);

        /* Is there an error? */
        if (movieJson.has(MESSAGE_CODE)) {
            int errorCode = movieJson.getInt(MESSAGE_CODE);

            if(errorCode != 0){
                return null;
            }
        }

        JSONArray movieArray = movieJson.getJSONArray(RESULTS);
        List<Movie> movies = new ArrayList<>(movieArray.length());

        for (int i = 0; i < movieArray.length(); i++) {

            Long id;
            String posterPath;

            JSONObject movieObject = movieArray.getJSONObject(i);

            id = movieObject.getLong(ID);
            posterPath = movieObject.getString(IMAGE_PATH);

            movies.add(new Movie(id, posterPath));

        }
        return movies;
    }
}