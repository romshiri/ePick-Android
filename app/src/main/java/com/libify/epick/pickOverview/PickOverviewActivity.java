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
import com.libify.epick.models.Product;
import com.libify.epick.network.IProductsApi;
import com.libify.epick.storage.PicksStorage;
import com.libify.epick.utils.IoC.ApplicationCommon;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.QueryMap;

public class PickOverviewActivity extends AppCompatActivity {

    private Pick subject;

    @Bind(R.id.listings_grid)
    RecyclerView listingsGrid;

    @Inject
    IProductsApi api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_overview_pick);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        ApplicationCommon.getComponent().inject(this);

        listingsGrid.setHasFixedSize(true);
        listingsGrid.setLayoutManager(new GridLayoutManager(this, 2));

        String pickId = getIntent().getStringExtra(PickItemViewHolder.PICK_ID);

        if (pickId != null) {
            Collection<Pick> picks = PicksStorage.getInstance(this).getAllPicks();
            for (Pick item : picks) {
                if (item.pickId.equals(pickId)) {
                    subject = item;
                }
            }
        }
        subject = new Pick("Shalom Naim Meod");
        subject.pickId = "1";
        subject.isGenerated = true;

        setTitle(subject.pickTitle);
        if (subject.isGenerated) {

                api.getPickStats(subject.pickId).enqueue(new Callback<List<Product>>() {
                    @Override
                    public void onResponse(Response<List<Product>> response, Retrofit retrofit) {
                        listingsGrid.setAdapter(new ListingsGridAdapter(PickOverviewActivity.this, response.body()));
                    }

                    @Override
                    public void onFailure(Throwable t) {

                    }
                });
            } else {
                String[] productIds = new String[subject.products.size()];
                for (int i = 0; i < subject.products.size(); i++){
                    productIds[i] = subject.products.get(i).ebayId;
                }

                listingsGrid.setAdapter(new ListingsGridAdapter(this, subject.products));
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
    }

}
