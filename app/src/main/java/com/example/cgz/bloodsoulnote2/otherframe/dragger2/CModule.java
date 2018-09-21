package com.example.cgz.bloodsoulnote2.otherframe.dragger2;

import dagger.Module;
import dagger.Provides;

@Module
public class CModule {

    @Provides
    C providerA() {
        return new C();
    }

}
