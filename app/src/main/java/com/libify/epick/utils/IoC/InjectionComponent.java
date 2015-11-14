package com.libify.epick.utils.IoC;

import com.libify.epick.MainActivity;
import com.libify.epick.ShareScreen;
import com.libify.epick.pickOverview.PickOverviewActivity;
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
    void inject(ShareScreen injectedObject);
    void inject(PickOverviewActivity injectedObject);

}


