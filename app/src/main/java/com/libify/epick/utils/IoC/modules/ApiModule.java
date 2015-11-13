package com.libify.epick.utils.IoC.modules;

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
    @Named("serverAdapter")
    Retrofit provideServerRestAdapter(){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.client(new OkHttpClient())
                .baseUrl("http://roni-pc.searsil.loc:8082/api/icloset/")
                .addConverterFactory(GsonConverterFactory.create());

        return builder.build();
    }

}
