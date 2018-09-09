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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import br.com.andersonv.famousmovies.data.MovieSearch;


public final class NetworkUtils {

    private final static String API_KEY_PARAM = "api_key";
    private final static String LANGUAGE_PARAM = "language";
    private final static String PAGE_PARAM = "page";
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String URL_MOVIES = "https://api.themoviedb.org/3/movie/";
    private static final String TOP_RATED = "top_rated";
    private static final String POPULAR = "popular";
    private static final String URL_MOVIES_TOP_RATED = URL_MOVIES + TOP_RATED;
    private static final String URL_MOVIES_POPULAR = URL_MOVIES + POPULAR;

    private static Uri buildUri(String url, int page, String locale, String apiKey) {
        Uri builtUri = Uri.parse(url).buildUpon()
                .appendQueryParameter(LANGUAGE_PARAM, locale)
                .appendQueryParameter(PAGE_PARAM, Integer.toString(page))
                .appendQueryParameter(API_KEY_PARAM, apiKey)
                .build();

        Log.d(TAG, "builtURI" + builtUri.toString());

        return builtUri;
    }

    private static URL buildURL(Uri uri) {
        URL url = null;
        try {
            url = new URL(uri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.v(TAG, "Built URI " + url);

        return url;
    }

    public static URL buildMovies(MovieSearch movieSearch, int page, String locale, String apiKey) {

        String search = URL_MOVIES_TOP_RATED;

        if (MovieSearch.MOST_POPULAR.equals(movieSearch)) {
            search = URL_MOVIES_POPULAR;
        }

        Uri builtUri = buildUri(search, page, locale, apiKey);
        return buildURL(builtUri);
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }


    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}
