package com.libify.epick.ShareScreenPackage;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.libify.epick.R;
import com.libify.epick.models.Pick;

import java.util.List;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class PickAdapter extends RecyclerView.Adapter<PickAdapter.PersonViewHolder>{

    List<Pick> picks;

    public PickAdapter(List<Pick> picksList){
        this.picks = picksList;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView pickTitle;
        TextView productsCount;

        PersonViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cv);
            pickTitle = (TextView)itemView.findViewById(R.id.pick_title);
            productsCount = (TextView)itemView.findViewById(R.id.products_count);
        }
    }

    @Override
    public int getItemCount() {
        return picks.size();
    }

    @Override
    public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
        personViewHolder.pickTitle.setText(picks.get(i).pickTitle);
        personViewHolder.productsCount.setText(picks.get(i).products.size()+ " Items");
    }

    @Override
    public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pick_item_view, viewGroup, false);
        PersonViewHolder pvh = new PersonViewHolder(v);
        return pvh;
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}