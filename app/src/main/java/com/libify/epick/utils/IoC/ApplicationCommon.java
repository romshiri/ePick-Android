package com.libify.epick.utils.IoC;

import android.app.Application;

import com.libify.epick.utils.IoC.modules.ApiModule;
import com.libify.epick.utils.IoC.modules.ApplicationModule;

public class ApplicationCommon extends Application {
    private static InjectionComponent injectionComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        injectionComponent = DaggerInjectionComponent.builder()
                .apiModule(new ApiModule())
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static InjectionComponent getComponent(){
        return injectionComponent;
    }
}