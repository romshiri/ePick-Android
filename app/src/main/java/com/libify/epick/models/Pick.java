package com.libify.epick.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class Pick {

    public String pickTitle;
    public List<Product> products;
    public boolean isGenerated;


    public Pick(String title) {
        this.pickTitle = title;
        products = new ArrayList<Product>();
        isGenerated = false;
    }


}
