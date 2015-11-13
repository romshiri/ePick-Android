package com.libify.epick.pickOverview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Nadav on 13/11/2015.
 */

public class ListingsGridAdapter extends RecyclerView.Adapter<ListingsGridAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView listingTitle;
        public ImageView listingImage;

        public ViewHolder(View itemView) {
            super(itemView);
            listingTitle = (TextView) itemView.findViewById(R.id.listing_title);
            listingImage = (ImageView) itemView.findViewById(R.id.listing_image);
        }

    }

    private List<String> itemList;
    private Context context;

    public ListingsGridAdapter(Context context, List<String> itemList) {
        this.itemList = itemList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecyclingTest", "onCreateViewHolder method is called");
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.d("RecyclingTest", "onBindViewHolder method is called");

        //TODO: fetch
        holder.listingTitle.setText("Nexus 5");
        holder.listingImage.setImageResource(itemList.get(position).getWonderImage());
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }
}

