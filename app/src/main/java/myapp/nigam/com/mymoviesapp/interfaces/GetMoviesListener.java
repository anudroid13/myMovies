package myapp.nigam.com.mymoviesapp.interfaces;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.models.MovieDetails;

public interface GetMoviesListener {

    void onSuccess(ArrayList<MovieDetails> arrayList);

    void onFailure();
}
