package com.libify.epick.network;

import com.libify.epick.models.Product;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by ofeka_000 on 11/13/2015.
 */

public interface IProductsApi {
    @GET("/ProductInfo/{productId}")
    Call<Product> getProductDescriptor(@Path("productId") String productId);
}
