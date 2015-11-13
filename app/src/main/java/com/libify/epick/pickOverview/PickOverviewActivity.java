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

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PickOverviewActivity extends AppCompatActivity {

    @Bind(R.id.listings_grid)
    RecyclerView listingsGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_overview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ButterKnife.bind(this);

        listingsGrid.setHasFixedSize(true);
        listingsGrid.setLayoutManager(new GridLayoutManager(this, 2));
        ArrayList<String> listings = new ArrayList<String>();
        listings.add("asdf");
        listings.add("asdf");
        listings.add("asdf");
        listings.add("asdf");
        listings.add("asdf");
        listingsGrid.setAdapter(new ListingsGridAdapter(this, listings));
    }
}
