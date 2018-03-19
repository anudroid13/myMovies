package myapp.nigam.com.mymoviesapp.asynctasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.interfaces.GetReviewsListener;
import myapp.nigam.com.mymoviesapp.models.ReviewModel;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class GetReviews extends AsyncTask<String, Void, String> {

    private final GetReviewsListener listener;

    public GetReviews(GetReviewsListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {
        String res = "";
        try {
            res = NetworkUtil.getResponseFromHttpUrl(new URL(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.optJSONArray("results");

            ArrayList<ReviewModel> reviews = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.optJSONObject(i);
                if (json != null) {
                    Gson gson = new Gson();
                    ReviewModel model = gson.fromJson(json.toString(), ReviewModel.class);
                    reviews.add(model);
                }
            }
            listener.onReviewSuccess(reviews);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
