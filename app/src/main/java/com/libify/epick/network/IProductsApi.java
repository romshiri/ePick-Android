package com.libify.epick.network;

import com.libify.epick.models.Pick;
import com.libify.epick.models.Product;

import java.util.List;
import java.util.Map;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.FieldMap;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

/**
 * Created by ofeka_000 on 11/13/2015.
 */

public interface IProductsApi {
    @GET("/ProductInfo/{productId}")
    Call<Product> getProductDescriptor(@Path("productId") String productId);
    @GET("/Pick/{pickId}/Statistics")
    Call<List<Product>> getPickStats(@Path("pickId") String pickId);
    @FormUrlEncoded
    @POST("/CreatePick")
    Call<Pick> generatePick(@Field("question") String title, @Field("products") String[] products);
}
