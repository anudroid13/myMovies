package myapp.nigam.com.mymoviesapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import myapp.nigam.com.mymoviesapp.BuildConfig;

public class NetworkUtil {

    private static final String API_KEY = BuildConfig.API_KEY;
    private static final String BASE_URL = "api.themoviedb.org";
    private static final String MOVIE_BANNER_URL = "image.tmdb.org";
    private static final String PARAM1 = "api_key";
    private static final String SCHEME = "http";

    public static URL buildUrl(String listType) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(BASE_URL)
                .appendPath("3")
                .appendPath("movie")
                .appendPath(listType)
                .appendQueryParameter(PARAM1, API_KEY);

        try {
            return new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URL buildImgUrl(String posterPath) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(MOVIE_BANNER_URL)
                .appendPath("t")
                .appendPath("p")
                .appendPath("w185")
                .appendEncodedPath(posterPath)
                .appendQueryParameter(PARAM1, API_KEY);

        try {
            return new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URL buildTrailersUrl(int id) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(BASE_URL)
                .appendPath("3")
                .appendPath("movie")
                .appendPath(String.valueOf(id))
                .appendPath("videos")
                .appendQueryParameter(PARAM1, API_KEY);

        try {
            return new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static URL buildReviewsUrl(int id) {

        Uri.Builder builder = new Uri.Builder();
        builder.scheme(SCHEME)
                .authority(BASE_URL)
                .appendPath("3")
                .appendPath("movie")
                .appendPath(String.valueOf(id))
                .appendPath("reviews")
                .appendQueryParameter(PARAM1, API_KEY);

        try {
            return new URL(builder.build().toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
