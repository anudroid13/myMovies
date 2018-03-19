package myapp.nigam.com.mymoviesapp.interfaces;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.models.MovieDetails;

/**
 * Created by Nigam on 3/18/2018.
 */

public interface GetFavoritesListener {

    void onSuccess(ArrayList<MovieDetails> arrayList);

    void onFailure();
}
