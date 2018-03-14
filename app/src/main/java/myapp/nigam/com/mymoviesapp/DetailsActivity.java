package myapp.nigam.com.mymoviesapp;

import android.annotation.TargetApi;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import myapp.nigam.com.mymoviesapp.adapters.ReviewAdapter;
import myapp.nigam.com.mymoviesapp.adapters.TrailerAdapter;
import myapp.nigam.com.mymoviesapp.asynctasks.GetReviews;
import myapp.nigam.com.mymoviesapp.asynctasks.GetTrailers;
import myapp.nigam.com.mymoviesapp.interfaces.GetReviewsListener;
import myapp.nigam.com.mymoviesapp.interfaces.GetTrailersListener;
import myapp.nigam.com.mymoviesapp.interfaces.OnItemClickListener;
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
    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private ArrayList<TrailerModel> trailerModels;
    private ArrayList<ReviewModel> reviewModels;

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
        Bundle bundle = intent.getBundleExtra("extras");
        if (bundle != null) {
            String title = bundle.getString("title");
            txtTitle.setText(title);
            setData(bundle);
        }
    }

    private void setData(Bundle bundle) {
        if (bundle != null) {
            String synopsis = bundle.getString("synopsis", "N/A");
            String imgUrl = bundle.getString("url", "N/A");
            String dateOfRelease = bundle.getString("release", "N/A");
            String rating = bundle.getString("rating", "N/A");
            String[] date = dateOfRelease.split("-");

            txtSynopsis.setText(synopsis);
            txtYearOfRelease.setText(date[0]);
            txtRating.setText(String.format("%s/10", rating));
            URL url = NetworkUtil.buildImgUrl(String.valueOf(imgUrl));

            try {
                if (url != null) {
                    Picasso.with(DetailsActivity.this)
                            .load(String.valueOf(url.toURI()))
                            .into(imgBanner);
                }
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }

            URL urlTrailers = NetworkUtil.buildTrailersUrl(bundle.getInt("id"));
            URL urlReviews = NetworkUtil.buildReviewsUrl(bundle.getInt("id"));

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
        DividerItemDecoration itemDecorator = new DividerItemDecoration(DetailsActivity.this,
                DividerItemDecoration.VERTICAL);
        itemDecorator.setDrawable(getResources().getDrawable(R.drawable.divider, null));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {

            case android.R.id.home: {
                DetailsActivity.this.finish();
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
        trailerModels = trailers;
        trailerAdapter.setArrayList(trailers);
    }

    @Override
    public void onReviewSuccess(ArrayList<ReviewModel> reviews) {
        reviewModels = reviews;
        reviewAdapter.setArrayList(reviews);
    }
}
