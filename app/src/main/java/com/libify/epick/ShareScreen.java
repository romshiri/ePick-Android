package com.libify.epick;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.libify.epick.R;
import com.libify.epick.ShareScreenPackage.PickAdapter;
import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;
import com.libify.epick.network.IProductsApi;
import com.libify.epick.storage.PicksStorage;
import com.libify.epick.utils.IoC.ApplicationCommon;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ShareScreen extends AppCompatActivity {

    Collection<Pick> picks;

    @Inject
    IProductsApi productsApi;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.ProductTitle)
    TextView productTitle;

    @Bind(R.id.ProductPrice)
    TextView productPrice;

    @Bind(R.id.ProductImage)
    ImageView productImage;

    @Bind(R.id.rv)
    RecyclerView rv;

//    @Bind(R.id.btnAddPick)
//    Button btn;

    ProgressDialog progress;


    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_screen);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add To Pick");
        toolbar.setTitleTextColor(0xFFFFFFFF);

        ApplicationCommon.getComponent().inject(this);
        LinearLayoutManager llm = new LinearLayoutManager(ShareScreen.this);
        rv.setLayoutManager(llm);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();
        picks = PicksStorage.getInstance(ShareScreen.this).getAllPicks();

//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Pick pick=new Pick("watches for mom");
//                picks.add(pick);
//                PicksStorage.getInstance(ShareScreen.this).addPicks(picks);
//            }
//        });

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }

        // initPicks();

    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            progress = ProgressDialog.show(this, "Loading",
                    "Loading Product Details...", true);
            Uri uri = Uri.parse(sharedText);
            final String id = uri.getQueryParameter("id");
            // http://172.13.0.129:8000/ProductInfo/371320064687
            productsApi.getProductDescriptor(id).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Response<Product> response, Retrofit retrofit) {
                    if (response.isSuccess()) {
                        product = response.body();
                        product.ebayId = id;
                        Picasso.with(ShareScreen.this)
                                .load(product.imageUrl)
                                .into(productImage);
                        productTitle.setText(product.productTitle);
                        productPrice.setText(product.productPrice + " $");
                        progress.dismiss();
                        initAdapter();
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    private void initAdapter() {
        ArrayList<Pick> reversedList = new ArrayList<Pick>(picks);
        Collections.reverse(reversedList);
        PickAdapter adapter = new PickAdapter((List<Pick>) reversedList, product, ShareScreen.this);
        rv.setAdapter(adapter);
    }

}
