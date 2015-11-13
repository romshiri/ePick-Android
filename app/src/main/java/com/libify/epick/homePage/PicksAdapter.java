package com.libify.epick.homePage;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;

public class PicksAdapter extends RecyclerView.Adapter<PickItemViewHolder> {

    private ArrayList<PickItemViewModel> data;

    public PicksAdapter(ArrayList<PickItemViewModel> data) {
        this.data = data;
    }

    @Override
    public PickItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PickItemViewHolder(new PickItemView(parent.getContext()));
    }

    @Override
    public void onBindViewHolder(PickItemViewHolder holder, int position) {
        PickItemViewModel model = data.get(position);

        if(model == null)
            return;

        holder.bindData(model);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
