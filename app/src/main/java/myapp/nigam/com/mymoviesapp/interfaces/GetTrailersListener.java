package myapp.nigam.com.mymoviesapp.interfaces;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.models.TrailerModel;

public interface GetTrailersListener {
    void onTrailerSuccess(ArrayList<TrailerModel> trailers);
}
