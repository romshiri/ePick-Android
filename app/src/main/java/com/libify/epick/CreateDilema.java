package com.libify.epick;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.libify.epick.models.Pick;
import com.libify.epick.storage.PicksStorage;

import butterknife.Bind;
import butterknife.ButterKnife;

public class CreateDilema extends AppCompatActivity {

    @Bind(R.id.create)
    Button button;

    @Bind(R.id.text_input)
    EditText textEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_dilema);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicksStorage storage = PicksStorage.getInstance(getApplicationContext());
                storage.addPick(new Pick(textEdit.getText().toString()));
                setResult(Activity.RESULT_OK, getIntent());
                finish();
            }
        });

    }

}
