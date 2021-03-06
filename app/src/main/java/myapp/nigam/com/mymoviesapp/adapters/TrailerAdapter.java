package myapp.nigam.com.mymoviesapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import myapp.nigam.com.mymoviesapp.R;
import myapp.nigam.com.mymoviesapp.interfaces.OnItemClickListener;
import myapp.nigam.com.mymoviesapp.models.TrailerModel;

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.ViewHolder> {

    private ArrayList<TrailerModel> arrayList;
    private final OnItemClickListener listener;
    private Context mContext;

    public TrailerAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }

    public void setArrayList(ArrayList<TrailerModel> arrayList) {
        if (arrayList != null) {
            this.arrayList = arrayList;
        }
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.layout_trailer, parent, false);
        return new TrailerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.txtname.setText(arrayList.get(position).getName());
        String url = "http://img.youtube.com/vi/" + arrayList.get(position).getKey() + "/0.jpg";
        try {
            if (url != null) {
                Picasso.with(mContext)
                        .load(url)
                        .into(holder.imgThumbnail);
            }
        } catch (Exception e) {
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

        final TextView txtname;
        final ImageView imgThumbnail;

        ViewHolder(View convertView) {
            super(convertView);
            txtname = convertView.findViewById(R.id.txt_name);
            imgThumbnail = convertView.findViewById(R.id.img_thumbnail);
            convertView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onItemSelected(getAdapterPosition());
        }
    }
}
