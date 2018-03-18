package myapp.nigam.com.mymoviesapp.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.content.MovieDBHelper;
import myapp.nigam.com.mymoviesapp.interfaces.GetFavoritesListener;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;

/**
 * Created by Nigam on 3/18/2018.
 */

public class GetFavorites extends AsyncTask<String, Void, ArrayList<MovieDetails>> {

    private final GetFavoritesListener listener;
    private Context mContext;

    public GetFavorites(Context context, GetFavoritesListener listener) {
        this.listener = listener;
        this.mContext = context;
    }

    @Override
    protected ArrayList<MovieDetails> doInBackground(String... strings) {
        MovieDBHelper helper = new MovieDBHelper(mContext);

        return helper.fetch();
    }

    @Override
    protected void onPostExecute(ArrayList<MovieDetails> arrayList) {
        if (arrayList == null) {
            listener.onFailure();
            return;
        }

        listener.onSuccess(arrayList);
    }
}
