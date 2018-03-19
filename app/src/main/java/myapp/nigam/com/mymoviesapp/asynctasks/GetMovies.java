package myapp.nigam.com.mymoviesapp.asynctasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.interfaces.GetMoviesListener;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class GetMovies extends AsyncTask<String, Void, String> {

    private final GetMoviesListener listener;

    public GetMovies(GetMoviesListener listener) {
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

        if (s == null) {
            listener.onFailure();
            return;
        }
        ArrayList<MovieDetails> arrayList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(s);

            JSONArray jsonArray = jsonObject.optJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {

                JSONObject json = jsonArray.optJSONObject(i);

                Gson gson = new Gson();
                MovieDetails details = gson.fromJson(json.toString(), MovieDetails.class);
                arrayList.add(details);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            listener.onFailure();
        }

        if (!arrayList.isEmpty()) {
            listener.onSuccess(arrayList);
        } else {
            listener.onFailure();
        }
    }
}