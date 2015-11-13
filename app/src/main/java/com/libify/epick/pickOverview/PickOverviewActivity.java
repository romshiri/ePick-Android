package com.libify.epick.pickOverview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.libify.epick.R;
import com.libify.epick.homePage.PickItemViewHolder;
import com.libify.epick.models.Pick;
import com.libify.epick.storage.PicksStorage;

import java.util.ArrayList;
import java.util.Collection;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PickOverviewActivity extends AppCompatActivity {

    private Pick subject;

    @Bind(R.id.listings_grid)
    RecyclerView listingsGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_pick);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String pickId = getIntent().getExtras().getString(PickItemViewHolder.PICK_ID);

        Collection<Pick> picks = PicksStorage.getInstance(this).getAllPicks();
        for (Pick item : picks){
            if (item.pickId.equals(pickId)){
                subject = item;
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        setTitle(subject.pickTitle);

        listingsGrid.setHasFixedSize(true);
        listingsGrid.setLayoutManager(new GridLayoutManager(this, 2));
        listingsGrid.setAdapter(new ListingsGridAdapter(this, subject.products));
    }

}
