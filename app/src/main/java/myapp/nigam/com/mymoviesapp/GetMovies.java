package myapp.nigam.com.mymoviesapp;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.interfaces.GetMoviesListener;
import myapp.nigam.com.mymoviesapp.models.ModelsList;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

class GetMovies extends AsyncTask<String, Void, String> {

    private final GetMoviesListener listener;

    GetMovies(GetMoviesListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... strings) {

        String res = "";
        try {
            res = NetworkUtil.getResponseFromHttpUrl(new URL(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
            listener.onFailure();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {

        if (s == null) {
            listener.onFailure();
            return;
        }
        ModelsList modelsList = ModelsList.getInstance();
        ArrayList<MovieDetails> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);

            JSONArray jsonArray = jsonObject.optJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json = jsonArray.optJSONObject(i);
                MovieDetails details = new MovieDetails();

                details.setVoteCount(json.optInt("vote_count"));
                details.setId(json.optInt("id"));
                details.setVideo(json.optBoolean("video"));
                details.setVoteAverage(json.optInt("vote_average"));
                details.setTitle(json.optString("title"));
                details.setPopularity(json.optDouble("popularity"));
                details.setPosterPath(json.optString("poster_path"));
                details.setOriginalLanguage(json.optString("original_language"));
                details.setOriginalTitle(json.optString("original_title"));
                details.setBackdropPath(json.optString("backdrop_path"));
                details.setAdult(json.optBoolean("adult"));
                details.setOverview(json.optString("overview"));
                details.setReleaseDate(json.optString("release_date"));

                arrayList.add(details);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFailure();
        }

        if (!arrayList.isEmpty()) {
            modelsList.setArrayList(arrayList);
            listener.onSuccess();
        } else {
            listener.onFailure();
        }
    }
}