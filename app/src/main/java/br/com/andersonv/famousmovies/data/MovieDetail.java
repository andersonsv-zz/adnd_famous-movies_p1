package br.com.andersonv.famousmovies.data;

import android.os.Parcel;
import android.os.Parcelable;

/**
 *
 The moviebd - [API KEY] must be inside the file values/apikey.xml
 https://api.themoviedb.org/3/movie/424?api_key=[mivebd]&language=en-US

 {
 "adult": false,
 "backdrop_path": "/cTNYRUTXkBgPH3wP3kmPUB5U6dA.jpg",
 "belongs_to_collection": null,
 "budget": 22000000,
 "genres": [
 {
 "id": 18,
 "name": "Drama"
 },
 {
 "id": 36,
 "name": "History"
 },
 {
 "id": 10752,
 "name": "War"
 }
 ],
 "homepage": "http://www.schindlerslist.com/",
 "id": 424,
 "imdb_id": "tt0108052",
 "original_language": "en",
 "original_title": "Schindler's List",
 "overview": "The true story of how businessman Oskar Schindler saved over a thousand Jewish lives from the Nazis while they worked as slaves in his factory during World War II.",
 "popularity": 18.894,
 "poster_path": "/yPisjyLweCl1tbgwgtzBCNCBle.jpg",
 "production_companies": [
 {
 "id": 33,
 "logo_path": "/8lvHyhjr8oUKOOy2dKXoALWKdp0.png",
 "name": "Universal Pictures",
 "origin_country": "US"
 },[...]
 ],
 "production_countries": [
 {
 "iso_3166_1": "US",
 "name": "United States of America"
 }
 ],
 "release_date": "1993-12-15",
 "revenue": 321365567,
 "runtime": 195,
 "spoken_languages": [
 {
 "iso_639_1": "en",
 "name": "English"
 }, [...]
 ],
 "status": "Released",
 "tagline": "Whoever saves one life, saves the world entire.",
 "title": "Schindler's List",
 "video": false,
 "vote_average": 8.5,
 "vote_count": 6328
 }


 *
 */
public class MovieDetail implements Parcelable{
    //id
    private Long id;
    //title
    private String title;
    //backdrop_path
    private String topImage;
    //poster_path
    private String imagePath;
    //release_date
    private String releaseDate;
    //overview
    private String overview;

    public MovieDetail(String title, String topImage, String imagePath, String releaseDate, String overview) {
        this.title = title;
        this.topImage = topImage;
        this.imagePath = imagePath;
        this.releaseDate = releaseDate;
        this.overview = overview;
    }

    private MovieDetail(Parcel in){
        this.id = in.readLong();
        this.title = in.readString();
        this.topImage = in.readString();
        this.imagePath = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(title);
        parcel.writeString(topImage);
        parcel.writeString(imagePath);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
    }

    public final Parcelable.Creator<MovieDetail> CREATOR = new Parcelable.Creator<MovieDetail>() {
        @Override
        public MovieDetail createFromParcel(Parcel parcel) {
            return new MovieDetail(parcel);
        }

        @Override
        public MovieDetail[] newArray(int i) {
            return new MovieDetail[i];
        }
    };

}
