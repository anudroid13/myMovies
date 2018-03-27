package myapp.nigam.com.mymoviesapp.content;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import static myapp.nigam.com.mymoviesapp.content.MovieContract.MovieEntry.TABLE_NAME;

/**
 * Created by Nigam on 3/23/2018.
 */

public class MoviesContentProvider extends ContentProvider {

    private SQLiteHelper sqLiteHelper;
    public static final int MOVIES = 100;
    public static final int MOVIES_WITH_ID = 101;
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    private static final String TAG = "MoviesContentProvider";

    public static UriMatcher buildUriMatcher() {
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH_MOVIES, MOVIES);
        uriMatcher.addURI(MovieContract.AUTHORITY, MovieContract.PATH_MOVIES + "/#", MOVIES_WITH_ID);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        sqLiteHelper = new SQLiteHelper(context);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] strings, @Nullable String s,
                        @Nullable String[] strings1, @Nullable String s1) {

        final SQLiteDatabase db = sqLiteHelper.getReadableDatabase();
        int match = sUriMatcher.match(uri);


        Cursor retCursor = null;

        try {
            switch (match) {
                case MOVIES:
                    retCursor = db.query(TABLE_NAME,
                            null,
                            null,
                            null,
                            null,
                            null,
                            null);
                    break;

                case MOVIES_WITH_ID:
                    String[] columns = new String[]{MovieContract.MovieEntry._ID};
                    String whereClause = MovieContract.MovieEntry.COL_ID + "= ?";
                    String id = uri.getPathSegments().get(1);
                    String[] whereArgs = new String[]{id};

                    retCursor = db.query(TABLE_NAME,
                            columns,
                            whereClause,
                            whereArgs,
                            null,
                            null,
                            null);
                    break;
            }
            if (null != retCursor && getContext() != null) {
                retCursor.setNotificationUri(getContext().getContentResolver(), uri);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in query data " + e.getLocalizedMessage());
        }
        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {

        final SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        Uri returnUri = null;
        try {
            switch (match) {
                case MOVIES:


                    long id = db.insert(TABLE_NAME, null, contentValues);
                    if (id > 0) {
                        returnUri = ContentUris.withAppendedId(MovieContract.MovieEntry.CONTENT_URI, id);
                    } else {
                        throw new android.database.SQLException("Failed to insert row into " + uri);
                    }
                    if (getContext() != null) {
                        getContext().getContentResolver().notifyChange(uri, null);
                    }
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in query data " + e.getLocalizedMessage());
        }
        return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String s, @Nullable String[] strings) {

        final SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
        int match = sUriMatcher.match(uri);

        int tasksDeleted = 0; // starts as 0

        try {
            switch (match) {

                case MOVIES_WITH_ID:
                    String id = uri.getPathSegments().get(1);
                    tasksDeleted = db.delete(TABLE_NAME, MovieContract.MovieEntry.COL_ID
                            + "=?", new String[]{id});
                    break;
            }
        } catch (Exception e) {
            Log.e(TAG, "Error in query data " + e.getLocalizedMessage());
            getContext().getContentResolver().notifyChange(uri, null);
        }
        return tasksDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s,
                      @Nullable String[] strings) {
        return 0;
    }
}
