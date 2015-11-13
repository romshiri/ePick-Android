package com.libify.epick;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.libify.epick.R;
import com.libify.epick.ShareScreenPackage.PickAdapter;
import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;
import com.libify.epick.network.IProductsApi;
import com.libify.epick.utils.IoC.ApplicationCommon;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class ShareScreen extends AppCompatActivity {

    List<Pick> picks;

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

    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_screen);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add To Pick");

        ApplicationCommon.getComponent().inject(this);
        LinearLayoutManager llm = new LinearLayoutManager(ShareScreen.this);
        rv.setLayoutManager(llm);

        Intent intent = getIntent();
        String action = intent.getAction();
        String type = intent.getType();

        if (Intent.ACTION_SEND.equals(action) && type != null) {
            if ("text/plain".equals(type)) {
                handleSendText(intent); // Handle text being sent
            }
        }

        initPicks();
        initAdapter();
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared
            Uri uri= Uri.parse(sharedText);
            String id=uri.getQueryParameter("id");
            // http://172.13.0.129:8000/ProductInfo/371320064687
            productsApi.getProductDescriptor(id).enqueue(new Callback<Product>() {
                @Override
                public void onResponse(Response<Product> response, Retrofit retrofit) {
                    if(response.isSuccess()){
                        product=response.body();
                        Picasso.with(ShareScreen.this)
                                .load(product.imageUrl)
                                .into(productImage);
                        productTitle.setText(product.productTitle);
                        productPrice.setText(product.productPrice+" $");
                    }
                }

                @Override
                public void onFailure(Throwable t) {

                }
            });
        }
    }

    private void initPicks() {
        picks=new ArrayList<Pick>();
        Pick p1 = new Pick("NAME");
        p1.products.add(new Product("url1", "title1", "desc1"));
        p1.products.add(new Product("url2", "title2", "desc2"));
        Pick p2 = new Pick("NAME2");
        p2.products.add(new Product("url3", "title3", "desc3"));
        p2.products.add(new Product("url4", "title4", "desc4"));
        picks.add(p1);
        picks.add(p2);
    }

    private void initAdapter(){
        PickAdapter adapter = new PickAdapter(picks, product, ShareScreen.this);
        rv.setAdapter(adapter);
    }

}
