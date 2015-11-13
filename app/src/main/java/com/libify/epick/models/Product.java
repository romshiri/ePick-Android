package com.libify.epick.models;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class Product {

    public String imageUrl;
    public String productTitle;
    public String productDesctiption;

    public Product(String image, String title, String Desc) {
        this.imageUrl = image;
        this.productTitle = title;
        this.productDesctiption = Desc;
    }
}
