package com.libify.epick.ShareScreenPackage;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.libify.epick.R;
import com.libify.epick.models.Pick;

import java.util.List;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class PickAdapter {

    List<Pick> picks;

    PickAdapter(List<Pick> picks){
        this.picks = picks;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {
       // CardView cv;
        TextView personName;
        TextView personAge;
        Button btnAdd;



        PersonViewHolder(View itemView) {
            super(itemView);
          //  cv = (CardView)itemView.findViewById(R.id.cv);
            personName = (TextView)itemView.findViewById(R.id.person_name);
            personAge = (TextView)itemView.findViewById(R.id.person_age);
            btnAdd = (Button)itemView.findViewById(R.id.btn_add);
        }
    }

}
