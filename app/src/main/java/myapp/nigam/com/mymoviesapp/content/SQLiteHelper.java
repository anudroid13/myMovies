package myapp.nigam.com.mymoviesapp.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Nigam on 3/18/2018.
 */

public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "movies_db";

    public SQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        final String CREATE_TABLE = "CREATE TABLE " + MovieContract.MovieEntry.TABLE_NAME + "("
                + MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + MovieContract.MovieEntry.COL_ID + " TEXT NOT NULL UNIQUE,"
                + MovieContract.MovieEntry.COL_DATE + " TEXT,"
                + MovieContract.MovieEntry.COL_POSTER_PATH + " TEXT,"
                + MovieContract.MovieEntry.COL_RATE + " TEXT,"
                + MovieContract.MovieEntry.COL_SYNOPSIS + " TEXT,"
                + MovieContract.MovieEntry.COL_TITLE + " TEXT NOT NULL );";
        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MovieContract.MovieEntry.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }
}
