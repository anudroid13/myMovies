package myapp.nigam.com.mymoviesapp.asynctasks;

import android.os.AsyncTask;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.interfaces.GetTrailersListener;
import myapp.nigam.com.mymoviesapp.models.TrailerModel;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class GetTrailers extends AsyncTask<String, Void, String> {

    private final GetTrailersListener listener;

    public GetTrailers(GetTrailersListener listener) {
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

            ArrayList<TrailerModel> trailers = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.optJSONObject(i);
                if (json != null) {
                    Gson gson = new Gson();
                    TrailerModel model = gson.fromJson(json.toString(), TrailerModel.class);
                    trailers.add(model);
                }
            }
            listener.onTrailerSuccess(trailers);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

