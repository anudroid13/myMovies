package myapp.nigam.com.mymoviesapp.interfaces;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.models.ReviewModel;

public interface GetReviewsListener {

    void onReviewSuccess(ArrayList<ReviewModel> reviews);
}
