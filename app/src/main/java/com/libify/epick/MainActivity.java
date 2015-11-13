package com.libify.epick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;
import com.libify.epick.storage.PicksStorage;

import java.util.ArrayList;
import java.util.Collection;
import com.libify.epick.pickOverview.PickOverviewActivity;

import com.libify.epick.homePage.PickItemViewModel;
import com.libify.epick.homePage.PicksAdapter;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private void tests(){
        PicksStorage storage = PicksStorage.getInstance(this);

        Pick p1 = new Pick("NAME");
        p1.products.add(new Product("url1", "title1", "desc1"));
        p1.products.add(new Product("url2", "title2", "desc2"));
        Pick p2 = new Pick("NAME2");
        p2.products.add(new Product("url3", "title3", "desc3"));
        p2.products.add(new Product("url4", "title4", "desc4"));

        Collection<Pick> picks = new ArrayList<Pick>();

        picks.add(p1);
        picks.add(p2);

        storage.addPicks(picks);

        Pick newPick=new Pick("NAME3");
        newPick.products.add(new Product("url3", "title3", "desc3"));
        newPick.products.add(new Product("url4", "title4", "desc4"));
        storage.addPick(newPick);

        Collection<Pick> allPicks = storage.getAllPicks();

    }
    @Bind(R.id.picks_recycleView)
    RecyclerView picksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        initRecycleView();
    }

    private void initRecycleView(){
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        picksList.setLayoutManager(layoutManager);
        picksList.setAdapter(new PicksAdapter(getDataFromTheServer()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private ArrayList<PickItemViewModel> getDataFromTheServer(){
        ArrayList<PickItemViewModel> results = new ArrayList<>();
        ArrayList<String> images = new ArrayList<>();

        images.add("https://lh4.googleusercontent.com/-wC54Rcr7Hq8/AAAAAAAAAAI/AAAAAAAAAAA/p8lQdwq1v6Y/s0-c-k-no-ns/photo.jpg");
        images.add("https://lh4.googleusercontent.com/-wC54Rcr7Hq8/AAAAAAAAAAI/AAAAAAAAAAA/p8lQdwq1v6Y/s0-c-k-no-ns/photo.jpg");
        images.add("https://lh4.googleusercontent.com/-wC54Rcr7Hq8/AAAAAAAAAAI/AAAAAAAAAAA/p8lQdwq1v6Y/s0-c-k-no-ns/photo.jpg");
        images.add("https://lh4.googleusercontent.com/-wC54Rcr7Hq8/AAAAAAAAAAI/AAAAAAAAAAA/p8lQdwq1v6Y/s0-c-k-no-ns/photo.jpg");

        results.add(new PickItemViewModel("What should I buy?", images));
        results.add(new PickItemViewModel("What should I buy to my Dad?", images));
        results.add(new PickItemViewModel("Which is better?", images));
        results.add(new PickItemViewModel("What do you I should buy for my Parents?", images));
        results.add(new PickItemViewModel("My girlfriend love gadgets, what should I buy?", images));
        results.add(new PickItemViewModel("What should I buy?", images));

        return results;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
