package myapp.nigam.com.mymoviesapp.asynctasks;

import android.content.Context;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.content.MovieDBHelper;
import myapp.nigam.com.mymoviesapp.interfaces.GetFavoritesListener;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;

public class GetFavorites extends AsyncTask<Void, Void, ArrayList<MovieDetails>> {

    private final GetFavoritesListener listener;
    private WeakReference<Context> mContext;

    public GetFavorites(WeakReference<Context> context, GetFavoritesListener listener) {
        this.listener = listener;
        this.mContext = context;
    }

    @Override
    protected ArrayList<MovieDetails> doInBackground(Void... args) {
        MovieDBHelper helper = new MovieDBHelper(mContext.get());
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
