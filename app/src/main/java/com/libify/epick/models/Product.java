package com.libify.epick.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by ofeka_000 on 11/13/2015.
 */
public class Product {

    @SerializedName("picture")
    public String imageUrl;

    @SerializedName("title")
    public String productTitle;

    @SerializedName("price")
    public String productPrice;

    public String ebayId;

    @SerializedName("votes")
    public int votes;

    @SerializedName("percents")
    public int percentage;


    public Product(String image, String title, String price) {
        this.imageUrl = image;
        this.productTitle = title;
        this.productPrice = price;
    }
}
