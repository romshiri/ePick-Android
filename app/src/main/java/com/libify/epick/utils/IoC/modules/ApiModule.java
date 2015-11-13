package com.libify.epick.utils.IoC.modules;

import com.libify.epick.network.IProductsApi;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

@Module
public class ApiModule {

    @Provides
    @Singleton
    Retrofit provideServerRestAdapter(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(new OkHttpClient())
                .baseUrl("http://172.13.0.129:8000/")
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

    @Provides
    IProductsApi provideProductApi(Retrofit adapter){
        return adapter.create(IProductsApi.class);
    }



}
