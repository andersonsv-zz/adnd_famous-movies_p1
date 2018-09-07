package br.com.andersonv.famousmovies.util;

import java.net.URL;
import java.util.Locale;

public interface Network {
    URL buildURIMovies(String service, int page, Locale locale, String apiKey);
}
