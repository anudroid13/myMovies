package myapp.nigam.com.mymoviesapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myapp.nigam.com.mymoviesapp.interfaces.GetMoviesListener;
import myapp.nigam.com.mymoviesapp.interfaces.OnItemClickListener;
import myapp.nigam.com.mymoviesapp.models.ModelsList;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class MainActivity extends AppCompatActivity implements GetMoviesListener {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    private ProgressDialog pDialog;
    private String category;
    private ArrayList<MovieDetails> movieDetails;
    private CustomAdapter adapter;

    private final OnItemClickListener listener = new OnItemClickListener() {
        @Override
        public void onItemSelected(int position) {
            MovieDetails model = movieDetails.get(position);
            Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
            Bundle bundle = new Bundle();
//            bundle.putString("title", model.getTitle());
//            bundle.putString("synopsis", model.getOverview());
//            bundle.putString("url", model.getPosterPath());
//            bundle.putString("release", model.getReleaseDate());
//            bundle.putString("rating", String.valueOf(model.getVoteAverage()));
//            bundle.putInt("id", model.getId());
            bundle.putInt("position", position);
            intent.putExtra("extras", bundle);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        init();
        if (savedInstanceState != null) {
            category = savedInstanceState.getString("category", getString(R.string.popular));
            getData(category);
        } else {
            getData(getString(R.string.popular));
        }

        if (getSupportActionBar() != null) {
            if (category.equalsIgnoreCase("popular")) {
                getSupportActionBar().setTitle(R.string.pop_movies);
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
    public void onSuccess() {
        hidepDialog();
        movieDetails = ModelsList.getInstance().getArrayList();
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

        outState.putString("category", category);
    }
}
