package com.libify.epick.utils.IoC;

import com.libify.epick.MainActivity;
import com.libify.epick.utils.IoC.modules.ApiModule;
import com.libify.epick.utils.IoC.modules.ApplicationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApiModule.class,
        ApplicationModule.class,

})

public interface InjectionComponent {
    void inject(MainActivity injectedObject);
}


