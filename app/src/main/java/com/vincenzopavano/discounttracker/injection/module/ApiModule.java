package com.vincenzopavano.discounttracker.injection.module;

import com.vincenzopavano.discounttracker.data.remote.DiscountService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    DiscountService provideDiscountService(Retrofit retrofit) {
        return retrofit.create(DiscountService.class);
    }
}
