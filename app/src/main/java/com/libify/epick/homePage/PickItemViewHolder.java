package com.libify.epick.homePage;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libify.epick.pickOverview.PickOverviewActivity;

public class PickItemViewHolder extends RecyclerView.ViewHolder {

    public PickItemViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void bindData(PickItemViewModel model){

        ((PickItemView)itemView).bindData(model);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),PickOverviewActivity.class));
            }
        });
    }
}
