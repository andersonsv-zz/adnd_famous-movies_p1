package br.com.andersonv.famousmovies.data;


import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements  Parcelable {
    //id
    private final Long id;
    //post_path
    private final String posterPath;
    //title
    private final String title;
    //backdrop_path
    private final String backdropPath;
    //release_date
    private final String releaseDate;
    //overview
    private final String overview;
    //vote_average
    private final Double voteAverage;

    public Movie(Long id, String posterPath, String title, String backdropPath, String releaseDate, String overview, Double voteAverage) {
        this.id = id;
        this.posterPath = posterPath;
        this.title = title;
        this.backdropPath = backdropPath;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.voteAverage = voteAverage;
    }

    private Movie(Parcel in) {
        this.id = in.readLong();
        this.posterPath = in.readString();
        this.title = in.readString();
        this.backdropPath = in.readString();
        this.releaseDate = in.readString();
        this.overview = in.readString();
        this.voteAverage = in.readDouble();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(posterPath);
        parcel.writeString(title);
        parcel.writeString(backdropPath);
        parcel.writeString(releaseDate);
        parcel.writeString(overview);
        parcel.writeDouble(voteAverage);
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getTitle() {
        return title;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
}