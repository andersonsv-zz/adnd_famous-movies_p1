package br.com.andersonv.famousmovies.data;


import android.os.Parcel;
import android.os.Parcelable;

/** -

 The moviebd - [API KEY] must be inside the file values/apikey.xml

 https://api.themoviedb.org/3/movie/popular?api_key=[moviebd]&language=en-US
 https://api.themoviedb.org/3/movie/top_rated?api_key=[moviebd]&language=en-US
{
    "page": 1,
    "total_results": 6157,
    "total_pages": 308,
    "results": [
        {
            "vote_count": 1863,
            "id": 19404,
            "video": false,
            "vote_average": 9.3,
            "title": "Dilwale Dulhania Le Jayenge",
            "popularity": 12.095,
            "poster_path": "/uC6TTUhPpQCmgldGyYveKRAu8JN.jpg",
            "original_language": "hi",
            "original_title": "दिलवाले दुल्हनिया ले जायेंगे",
            "genre_ids": [
                35,
                18,
                10749
            ],
            "backdrop_path": "/nl79FQ8xWZkhL3rDr1v2RFFR6J0.jpg",
            "adult": false,
            "overview": "Raj is a rich, carefree, happy-go-lucky second generation NRI. Simran is the daughter of Chaudhary Baldev Singh, who in spite of being an NRI is very strict about adherence to Indian values. Simran has left for India to be married to her childhood fiancé. Raj leaves for India with a mission at his hands, to claim his lady love under the noses of her whole family. Thus begins a saga.",
            "release_date": "1995-10-20"
        },
        [...]
      ]
}
 */
public class Movie implements Parcelable {
    //id
    public Long id;
    //post_path
    public String imagePath;

    public Movie(Long id,  String imagePath) {
        this.id = id;
        this.imagePath = imagePath;
    }

    private Movie(Parcel in){
        this.id = in.readLong();
        this.imagePath = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeLong(id);
        parcel.writeString(imagePath);
    }

    public final Parcelable.Creator<Movie> CREATOR = new Parcelable.Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel parcel) {
            return new Movie(parcel);
        }

        @Override
        public Movie[] newArray(int i) {
            return new Movie[i];
        }
    };
}