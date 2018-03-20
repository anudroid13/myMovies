package myapp.nigam.com.mymoviesapp.activities;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myapp.nigam.com.mymoviesapp.R;
import myapp.nigam.com.mymoviesapp.adapters.ReviewAdapter;
import myapp.nigam.com.mymoviesapp.adapters.TrailerAdapter;
import myapp.nigam.com.mymoviesapp.asynctasks.GetReviews;
import myapp.nigam.com.mymoviesapp.asynctasks.GetTrailers;
import myapp.nigam.com.mymoviesapp.content.MovieDBHelper;
import myapp.nigam.com.mymoviesapp.interfaces.GetReviewsListener;
import myapp.nigam.com.mymoviesapp.interfaces.GetTrailersListener;
import myapp.nigam.com.mymoviesapp.interfaces.OnItemClickListener;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.models.ReviewModel;
import myapp.nigam.com.mymoviesapp.models.TrailerModel;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class DetailsActivity extends AppCompatActivity implements
        GetTrailersListener, GetReviewsListener {

    @BindView(R.id.txt_synopsis)
    TextView txtSynopsis;
    @BindView(R.id.img_banner)
    ImageView imgBanner;
    @BindView(R.id.txt_release_year)
    TextView txtYearOfRelease;
    @BindView(R.id.txt_rating)
    TextView txtRating;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recycler_view_trailers)
    RecyclerView recyclerViewTrailers;
    @BindView(R.id.recycler_view_reviews)
    RecyclerView recyclerViewReviews;
    @BindView(R.id.view_trailers)
    LinearLayout llTrailers;
    @BindView(R.id.view_reviews)
    LinearLayout llReviews;
    @BindView(R.id.chk_fav)
    AppCompatCheckBox chkFav;
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private ArrayList<TrailerModel> trailerModels;
    private ArrayList<ReviewModel> reviewModels;
    private MovieDetails details;
    private final String MODEL_KEY = "model";
    private final String TRAILERS_LIST = "trailers_list";
    private final String REVIEWS_LIST = "reviews_list";

    private final OnItemClickListener trailerListener = new OnItemClickListener() {
        @Override
        public void onItemSelected(int position) {
            TrailerModel model = trailerModels.get(position);

            Intent appIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("vnd.youtube:" + model.getKey()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + model.getKey()));
            try {
                startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                startActivity(webIntent);
            }
        }
    };

    private final OnItemClickListener reviewListener = new OnItemClickListener() {
        @Override
        public void onItemSelected(int position) {
            ReviewModel model = reviewModels.get(position);

            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(model.getUrl()));
            startActivity(browserIntent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(DetailsActivity.this);
        init();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        Intent intent = getIntent();
        if (intent.hasExtra(MODEL_KEY)) {
            details = intent.getParcelableExtra(MODEL_KEY);
            txtTitle.setText(details.getTitle());
            setData();
        }

        if (savedInstanceState == null) {
            getData();
        }

        chkFav.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                MovieDBHelper helper = new MovieDBHelper(DetailsActivity.this);
                if (b) {
                    if (helper.insert(details) != 0) {
                        Toast.makeText(DetailsActivity.this,
                                getString(R.string.added_to_favourite_label),
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    if (helper.delete(String.valueOf(details.getId())) > 0) {
                        Toast.makeText(DetailsActivity.this,
                                getString(R.string.add_to_favourite_label),
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void getData() {
        URL urlTrailers = NetworkUtil.buildTrailersUrl(details.getId());
        URL urlReviews = NetworkUtil.buildReviewsUrl(details.getId());

        if (NetworkUtil.isNetworkAvailable(DetailsActivity.this)) {
            if (urlTrailers != null) {
                GetTrailers task = new GetTrailers(DetailsActivity.this);
                task.execute(String.valueOf(urlTrailers));
            }

            if (urlReviews != null) {
                GetReviews task = new GetReviews(DetailsActivity.this);
                task.execute(String.valueOf(urlReviews));
            }
        }
    }

    private void setData() {
        String dateOfRelease = details.getReleaseDate();
        String[] date = dateOfRelease.split("-");

        txtSynopsis.setText(details.getOverview());
        txtYearOfRelease.setText(date[0]);
        txtRating.setText(String.format("%s/10", details.getVoteAverage()));
        URL url = NetworkUtil.buildImgUrl(details.getPosterPath());

        try {
            if (url != null) {
                Picasso.with(DetailsActivity.this)
                        .load(String.valueOf(url.toURI()))
                        .into(imgBanner);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        MovieDBHelper helper = new MovieDBHelper(DetailsActivity.this);
        if (helper.isMovieExists(String.valueOf(details.getId()))) {
            chkFav.setChecked(true);
        } else {
            chkFav.setChecked(false);
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init() {

        LinearLayoutManager trailerManager = new
                LinearLayoutManager(DetailsActivity.this);
        LinearLayoutManager reviewManager = new
                LinearLayoutManager(DetailsActivity.this);
        recyclerViewTrailers.setLayoutManager(trailerManager);
        recyclerViewReviews.setLayoutManager(reviewManager);
        trailerAdapter = new TrailerAdapter(trailerListener);
        reviewAdapter = new ReviewAdapter(reviewListener);
        recyclerViewTrailers.setAdapter(trailerAdapter);
        recyclerViewReviews.setAdapter(reviewAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case android.R.id.home: {
                setResult(RESULT_OK);
                finish();
                break;
            }

            default: {
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onTrailerSuccess(ArrayList<TrailerModel> trailers) {
        if (!trailers.isEmpty()) {
            llTrailers.setVisibility(View.VISIBLE);
        }
        trailerModels = trailers;
        trailerAdapter.setArrayList(trailers);
    }

    @Override
    public void onReviewSuccess(ArrayList<ReviewModel> reviews) {
        if (!reviews.isEmpty()) {
            llReviews.setVisibility(View.VISIBLE);
        }
        reviewModels = reviews;
        reviewAdapter.setArrayList(reviews);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(TRAILERS_LIST, trailerModels);
        outState.putParcelableArrayList(REVIEWS_LIST, reviewModels);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            trailerModels = savedInstanceState.getParcelableArrayList(TRAILERS_LIST);
            reviewModels = savedInstanceState.getParcelableArrayList(REVIEWS_LIST);

            if (!reviewModels.isEmpty()) {
                llReviews.setVisibility(View.VISIBLE);
            }

            if (!trailerModels.isEmpty()) {
                llTrailers.setVisibility(View.VISIBLE);
            }

            reviewAdapter.setArrayList(reviewModels);
            trailerAdapter.setArrayList(trailerModels);
        }
    }
}
