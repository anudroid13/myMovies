package myapp.nigam.com.mymoviesapp.content;

import android.provider.BaseColumns;

/**
 * Created by Nigam on 3/18/2018.
 */

public class MovieContract {

    public static final class MovieEntry implements BaseColumns {

        public static final String TABLE_NAME = "mymovies";

        public static final String COL_ID = "id";
        public static final String COL_TITLE = "title";
        public static final String COL_DATE = "releasedate";
        public static final String COL_RATE = "voteaverage";
        public static final String COL_POSTER_PATH = "posterpath";
        public static final String COL_SYNOPSIS = "overview";
    }
}
