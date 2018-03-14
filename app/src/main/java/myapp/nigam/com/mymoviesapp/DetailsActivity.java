package myapp.nigam.com.mymoviesapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.net.URL;

import butterknife.BindView;
import butterknife.ButterKnife;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        ButterKnife.bind(DetailsActivity.this);

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
}
