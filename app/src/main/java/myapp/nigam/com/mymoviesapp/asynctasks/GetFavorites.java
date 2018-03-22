package myapp.nigam.com.mymoviesapp.asynctasks;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.content.MovieContract;
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

        Uri uri = MovieContract.MovieEntry.CONTENT_URI;
        Cursor cursor = mContext.get().getContentResolver().query(uri, null,
                null, null, null);

        ArrayList<MovieDetails> details = new ArrayList<>();

        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                MovieDetails detail = new MovieDetails();
                detail.setTitle(cursor.getString(cursor.getColumnIndex
                        (MovieContract.MovieEntry.COL_TITLE)));
                detail.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex
                        (MovieContract.MovieEntry.COL_ID))));
                detail.setReleaseDate(cursor.getString(cursor.getColumnIndex
                        (MovieContract.MovieEntry.COL_DATE)));
                detail.setPosterPath(cursor.getString(cursor.getColumnIndex
                        (MovieContract.MovieEntry.COL_POSTER_PATH)));
                detail.setVoteAverage(Float.parseFloat(cursor.getString(cursor.getColumnIndex
                        (MovieContract.MovieEntry.COL_RATE))));
                detail.setOverview(cursor.getString(cursor.getColumnIndex
                        (MovieContract.MovieEntry.COL_SYNOPSIS)));

                details.add(detail);
            }
        }
        return details;
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
