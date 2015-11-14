package com.libify.epick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.libify.epick.homePage.PickItemView;
import com.libify.epick.homePage.PickViewModelMapper;
import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;
import com.libify.epick.storage.PicksStorage;

import java.util.ArrayList;
import java.util.Collection;

import com.libify.epick.homePage.PickItemViewModel;
import com.libify.epick.homePage.PicksAdapter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 1;
    @Bind(R.id.toolbar)
    Toolbar toolbar;

    private void tests(){
//        PicksStorage storage = PicksStorage.getInstance(this);
//
//        Pick p1 = new Pick("Which one is the best case for LG 4?");
//        p1.products.add(new Product("http://i.ebayimg.com/images/g/xOEAAOSwT6pVn3JS/s-l1600.jpg",
//                "Shockproof Hybrid Rugged Rubber Impact Hard Case Protective Cover Skin For LG G4", "4.99"));
//
//        p1.products.add(new Product("http://i.ebayimg.com/images/g/KzcAAOSwT6pVg4Rc/s-l1600.jpg",
//                "Hybrid Hard Rugged Shockproof Armor Stand Slim Protective Case Cover for LG G4", "3.99"));
//
//        p1.products.add(new Product("http://www.buyeasyonline.com/ebay_photo/201504/31465/31465-1.jpg",
//                "http://www.buyeasyonline.com/ebay_photo/201504/31465/31465-1.jpg", "3.69"));
//
//        p1.products.add(new Product("http://i.ebayimg.com/images/g/AAoAAOSw9mFWJ0pC/s-l1600.jpg", "http://i.ebayimg.com/images/g/AAoAAOSw9mFWJ0pC/s-l1600.jpg",
//                "4.40"));
//
//        storage.addPick(p1);
    }

    @Bind(R.id.picks_recycleView)
    RecyclerView picksList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        PicksStorage storage = PicksStorage.getInstance(this);
        storage.initialize();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(getApplicationContext(), CreateDilema.class), REQUEST_CODE);
            }
        });

        initRecycleView();
    }

    private void initRecycleView(){
        GridLayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
        picksList.setLayoutManager(layoutManager);
        picksList.setAdapter(new PicksAdapter(getPersistenceData()));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private ArrayList<PickItemViewModel> getPersistenceData(){
        PicksStorage storage = PicksStorage.getInstance(this);
        Collection<Pick> picks =  storage.getAllPicks();
        PickViewModelMapper mapper = new PickViewModelMapper();

        ArrayList<PickItemViewModel> pickVm = new ArrayList<>();

        if(picks == null)
            return new ArrayList<>();

        for (Pick p: picks) {
            pickVm.add(mapper.map(p));
        }

        return pickVm;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                initRecycleView();
            }
        }
    }
}
