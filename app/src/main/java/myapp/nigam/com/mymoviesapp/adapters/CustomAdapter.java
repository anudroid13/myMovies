package myapp.nigam.com.mymoviesapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.R;
import myapp.nigam.com.mymoviesapp.interfaces.OnItemClickListener;
import myapp.nigam.com.mymoviesapp.models.MovieDetails;
import myapp.nigam.com.mymoviesapp.utils.NetworkUtil;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {

    private ArrayList<MovieDetails> arrayList;
    private Context mContext;
    private final OnItemClickListener listener;

    public CustomAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setArrayList(ArrayList<MovieDetails> arrayList) {
        if (arrayList != null) {
            this.arrayList = arrayList;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_item, parent, false);
        return new CustomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        MovieDetails details = arrayList.get(position);

        URL url = NetworkUtil.buildImgUrl(String.valueOf(details.getPosterPath()));

        try {
            if (url != null) {
                Picasso.with(mContext)
                        .load(String.valueOf(url.toURI()))
                        .error(R.drawable.ic_launcher_foreground)
                        .into(holder.imgBanner);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        if (arrayList != null && !arrayList.isEmpty()) {
            return arrayList.size();
        }
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        final ImageView imgBanner;

        ViewHolder(View convertView) {
            super(convertView);
            imgBanner = convertView.findViewById(R.id.img_banner);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemSelected(getAdapterPosition());
        }
    }
}