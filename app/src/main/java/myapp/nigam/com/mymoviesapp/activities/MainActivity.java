package myapp.nigam.com.mymoviesapp.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myapp.nigam.com.mymoviesapp.R;
import myapp.nigam.com.mymoviesapp.adapters.CustomAdapter;
import myapp.nigam.com.mymoviesapp.asynctasks.GetFavorites;
import myapp.nigam.com.mymoviesapp.asynctasks.GetMovies;
import myapp.nigam.com.mymoviesapp.interfaces.GetFavoritesListener;
import myapp.nigam.com.mymoviesapp.interfaces.GetMoviesListener;
import myapp.nigam.com.mymoviesapp.interfaces.OnItemClickListener;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class MainActivity extends AppCompatActivity implements GetMoviesListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private String category;
    private final String CATEGORY_KEY = "category";
    private final String MOVIE_LIST_KEY = "movie_list";
    private ArrayList<MovieDetails> movieDetails;
    private final int REQ_CODE = 1001;
    private final OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemSelected(int position) {
            MovieDetails model = movieDetails.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            intent.putExtra("model", model);
            startActivityForResult(intent, REQ_CODE);
        }
    };
    private CustomAdapter adapter;
    private GetFavorites task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        init();

        if (savedInstanceState != null) {
            category = savedInstanceState.getString(CATEGORY_KEY, getString(R.string.popular));
            movieDetails = savedInstanceState.getParcelableArrayList(MOVIE_LIST_KEY);
            if (movieDetails != null && !movieDetails.isEmpty()) {
                adapter.setArrayList(movieDetails);
            }
        } else {
            getData(getString(R.string.popular));
        }

        if (getSupportActionBar() != null) {
            if (category.equalsIgnoreCase("popular")) {
                getSupportActionBar().setTitle(R.string.pop_movies);
            } else if (category.equalsIgnoreCase("favorites")) {
                getSupportActionBar().setTitle(R.string.favorites);
            } else {
                getSupportActionBar().setTitle(R.string.top_rated_movies);
            }
        }
    }

    private void getData(final String listType) {

        category = listType;

        showpDialog();

        URL url = NetworkUtil.buildUrl(listType);

        if (url != null) {
            if (NetworkUtil.isNetworkAvailable(MainActivity.this)) {
                GetMovies task = new GetMovies(MainActivity.this);
                task.execute(String.valueOf(url));
            } else {
                hidepDialog();
                Snackbar.make(recyclerView, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                getData(listType);
                            }
                        })
                        .show();
            }
        }
    }

    private void init() {
        pDialog = new ProgressDialog(MainActivity.this, R.style.AppCompatAlertDialogStyle);
        pDialog.setMessage(getString(R.string.wait));
        pDialog.setCancelable(false);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new CustomAdapter(listener);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case R.id.action_select_popular: {
                getData(getString(R.string.popular));
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.pop_movies);
                }
                break;
            }

            case R.id.action_select_top_rated: {
                getData(getString(R.string.top_rated));
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle(R.string.top_rated_movies);
                }
                break;
            }

            case R.id.action_select_favorites: {
                fetchFavoriteMovies(false);
                break;
            }

            default: {
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        hidepDialog();
    }

    @Override
    public void onSuccess(final ArrayList<MovieDetails> arrayList) {
        hidepDialog();
        movieDetails = arrayList;
        adapter.setArrayList(movieDetails);
    }

    @Override
    public void onFailure() {
        hidepDialog();
        Toast.makeText(this, R.string.error_msg, Toast.LENGTH_SHORT).show();
    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString(CATEGORY_KEY, category);
        if (null != movieDetails && !movieDetails.isEmpty()) {
            outState.putParcelableArrayList(MOVIE_LIST_KEY, movieDetails);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE && resultCode == RESULT_OK && category.equalsIgnoreCase("favorites")) {

            fetchFavoriteMovies(true);
        }
    }

    private void fetchFavoriteMovies(final boolean isDataChanged) {
        showpDialog();
        task = new GetFavorites(new WeakReference<Context>(MainActivity.this),
                new GetFavoritesListener() {
            @Override
            public void onSuccess(ArrayList<MovieDetails> arrayList) {
                hidepDialog();
                if (arrayList != null && !arrayList.isEmpty()) {
                    movieDetails = arrayList;
                    adapter.setArrayList(movieDetails);

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setTitle(R.string.favorites);
                    }
                    category = "favorites";
                } else {
                    if (isDataChanged) {
                        movieDetails = arrayList;
                        adapter.setArrayList(movieDetails);
                    }
                    Toast.makeText(MainActivity.this, "You have not added any movie under this category",
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure() {
                hidepDialog();
                Toast.makeText(MainActivity.this, R.string.error_msg, Toast.LENGTH_SHORT).show();
            }
        });
        task.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();

        if (task != null && task.getStatus() == AsyncTask.Status.RUNNING) {
            task.cancel(true);
        }
    }
}
