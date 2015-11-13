package com.libify.epick.models;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class Pick {

    public String pickId;
    public String pickTitle;
    public List<Product> products;
    public boolean isGenerated;


    public Pick(String title) {
        this.pickTitle = title;
        products = new ArrayList<Product>();
        isGenerated = false;
        pickId = UUID.randomUUID().toString();

        Log.d("ROM", "Created pick:" + pickId);
    }


}
