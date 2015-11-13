package com.libify.epick;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.libify.epick.R;
import com.libify.epick.models.Pick;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ShareScreen extends AppCompatActivity {

    List<Pick> picks;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.ProductTitle)
    TextView productTitle;

    @Bind(R.id.ProductDescription)
    TextView productDescription;

    @Bind(R.id.rv)
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.share_screen);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add To Pick");

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
    }

    void handleSendText(Intent intent) {
        String sharedText = intent.getStringExtra(Intent.EXTRA_TEXT);
        if (sharedText != null) {
            // Update UI to reflect text being shared

        }
    }

    private void getPicks() {
        picks = new ArrayList<>();
        picks.add(new Pick("Emma Wilson", "23 years old"));
        picks.add(new Pick("Lavery Maiss", "25 years old"));
        picks.add(new Pick("Lillie Watts", "35 years old"));
    }

}
