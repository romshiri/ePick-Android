package com.libify.epick.homePage;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by romshiri on 13/11/15.
 */
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
    }
}
