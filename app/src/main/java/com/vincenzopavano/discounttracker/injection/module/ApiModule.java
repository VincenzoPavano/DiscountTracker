package com.vincenzopavano.discounttracker.injection.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import com.vincenzopavano.discounttracker.data.remote.DiscountService;
import com.vincenzopavano.discounttracker.data.remote.PokemonService;
import retrofit2.Retrofit;

@Module(includes = {NetworkModule.class})
public class ApiModule {

    @Provides
    @Singleton
    DiscountService provideDiscountService(Retrofit retrofit) {
        return retrofit.create(DiscountService.class);
    }
}
