package myapp.nigam.com.mymoviesapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;

import myapp.nigam.com.mymoviesapp.models.TrailerModel;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class GetTrailers extends AsyncTask<String, Void, String> {

    @Override
    protected String doInBackground(String... strings) {
        String res = "";
        try {
            Log.d("anudroid133", strings[0]);
            res = NetworkUtil.getResponseFromHttpUrl(new URL(strings[0]));
        } catch (IOException e) {
            e.printStackTrace();
//            listener.onFailure();
        }
        return res;
    }

    @Override
    protected void onPostExecute(String s) {
        Log.d("anudroid", s);
        try {
            JSONObject jsonObject = new JSONObject(s);
            JSONArray jsonArray = jsonObject.optJSONArray("results");

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json = jsonArray.optJSONObject(i);
                if (json != null) {
                    TrailerModel model = new TrailerModel();
                    model.setId(json.optString("id"));
                    model.setIso_639_1(json.optString("iso_639_1"));
                    model.setIso_3166_1(json.optString("iso_3166_1"));
                    model.setKey(json.optString("key"));
                    model.setName(json.optString("name"));
                    model.setSite(json.optString("site"));
                    model.setSize(json.optString("size"));
                    model.setType(json.optString("type"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
