package myapp.nigam.com.mymoviesapp.content;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.models.MovieDetails;

/**
 * Created by Nigam on 3/18/2018.
 */

public class MovieDBHelper {

    private Context mContext;

    public MovieDBHelper(Context context) {
        this.mContext = context;
    }

    public long insert(MovieDetails details) {

        SQLiteHelper helper = new SQLiteHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MovieContract.MovieEntry.COL_ID, String.valueOf(details.getId()));
        values.put(MovieContract.MovieEntry.COL_DATE, String.valueOf(details.getReleaseDate()));
        values.put(MovieContract.MovieEntry.COL_POSTER_PATH, String.valueOf(details.getPosterPath()));
        values.put(MovieContract.MovieEntry.COL_RATE, String.valueOf(details.getVoteAverage()));
        values.put(MovieContract.MovieEntry.COL_SYNOPSIS, String.valueOf(details.getOverview()));
        values.put(MovieContract.MovieEntry.COL_TITLE, String.valueOf(details.getTitle()));
        try {
            return db.insert(MovieContract.MovieEntry.TABLE_NAME, null, values);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int delete(String id) {

        if (id == null || id.isEmpty()) {
            return 0;
        }

        SQLiteHelper helper = new SQLiteHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        String whereClause = MovieContract.MovieEntry.COL_ID + "= ?";
        String[] whereArgs = new String[]{id};

        try {
            return db.delete(MovieContract.MovieEntry.TABLE_NAME, whereClause, whereArgs);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public ArrayList<MovieDetails> fetch() {

        SQLiteHelper helper = new SQLiteHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        ArrayList<MovieDetails> details = new ArrayList<>();
        Cursor cursor = null;
        try {
            cursor = db.query(MovieContract.MovieEntry.TABLE_NAME, null,
                    null, null, null, null, null);

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
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return details;
    }

    public boolean isMovieExists(String id) {

        SQLiteHelper helper = new SQLiteHelper(mContext);
        SQLiteDatabase db = helper.getWritableDatabase();

        String[] columns = new String[]{MovieContract.MovieEntry._ID};
        String whereClause = MovieContract.MovieEntry.COL_ID + "= ?";
        String[] whereArgs = new String[]{id};
        Cursor cursor = null;
        try {
            cursor = db.query(MovieContract.MovieEntry.TABLE_NAME, columns,
                    whereClause, whereArgs, null, null, null);

            if (cursor != null && cursor.getCount() > 0) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != cursor) {
                cursor.close();
            }
        }
        return false;
    }
}
