package com.libify.epick.homePage;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.libify.epick.pickOverview.PickOverviewActivity;

public class PickItemViewHolder extends RecyclerView.ViewHolder {

    public static final String PICK_ID = "pickId";

    public PickItemViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void bindData(final PickItemViewModel model){

        ((PickItemView)itemView).bindData(model);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),PickOverviewActivity.class);
                intent.putExtra(PICK_ID, model.getPickId());
                v.getContext().startActivity(intent);
            }
        });
    }
}
