package myapp.nigam.com.mymoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;
import myapp.nigam.com.mymoviesapp.application.AppController;
import myapp.nigam.com.mymoviesapp.models.ModelsList;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class DetailsActivity extends AppCompatActivity {

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
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.img_fav)
    ImageView imgFav;
    private static boolean isFav = false;
    private Realm realm;
    private MovieDetails details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(DetailsActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        realm = AppController.getRealmInstance();
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("extras");
        if (bundle != null) {
            int position = bundle.getInt("position");
            details = ModelsList.getInstance().getArrayList().get(position);
            txtTitle.setText(details.getTitle());
            setData();
        }
    }

    private void setData() {
        String dateOfRelease = details.getReleaseDate();
        String[] date = dateOfRelease.split("-");

        txtSynopsis.setText(details.getOverview());
        txtYearOfRelease.setText(date[0]);
        txtRating.setText(String.format("%s/10", details.getVoteAverage()));
        URL url = NetworkUtil.buildImgUrl(String.valueOf(details.getPosterPath()));

        try {
            if (url != null) {
                Picasso.with(DetailsActivity.this)
                        .load(String.valueOf(url.toURI()))
                        .into(imgBanner);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        URL urlTrailers = NetworkUtil.buildTrailersUrl(details.getId());

        if (urlTrailers != null) {
            if (NetworkUtil.isNetworkAvailable(DetailsActivity.this)) {
                GetTrailers task = new GetTrailers();
                task.execute(String.valueOf(urlTrailers));
            } else {
//                hidepDialog();
                Snackbar.make(recyclerView, R.string.network_error, Snackbar.LENGTH_INDEFINITE)
                        .setAction(R.string.retry, new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
//                                getData(listType);
                            }
                        })
                        .show();
            }
        }
        RealmResults<MovieDetails> results = realm.where(MovieDetails.class)
                .equalTo("id", details.getId())
                .findAll();
        if (results.size() != 0) {
            isFav = true;
            imgFav.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            isFav = false;
            imgFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
        }
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

    @OnClick(R.id.img_fav)
    void addfav() {
        if (!isFav) {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    realm.copyToRealmOrUpdate(details);
                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    imgFav.setImageResource(R.drawable.ic_favorite_black_24dp);
                    Toast.makeText(DetailsActivity.this, "Added to favorites", Toast.LENGTH_SHORT).show();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {

                }
            });
        } else {
            realm.executeTransactionAsync(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    RealmResults<MovieDetails> results = realm.where(MovieDetails.class)
                            .equalTo("id", details.getId())
                            .findAll();
                    results.deleteAllFromRealm();

                }
            }, new Realm.Transaction.OnSuccess() {
                @Override
                public void onSuccess() {
                    imgFav.setImageResource(R.drawable.ic_favorite_border_black_24dp);
                    Toast.makeText(DetailsActivity.this,
                            "Removed from favorites", Toast.LENGTH_SHORT).show();
                }
            }, new Realm.Transaction.OnError() {
                @Override
                public void onError(Throwable error) {

                }
            });
        }
        isFav = !isFav;
    }
}