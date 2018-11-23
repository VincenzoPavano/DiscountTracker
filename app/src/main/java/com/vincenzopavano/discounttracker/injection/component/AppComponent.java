package com.vincenzopavano.discounttracker.injection.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.injection.ApplicationContext;
import com.vincenzopavano.discounttracker.injection.module.AppModule;

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ApplicationContext
    Context context();

    Application application();

    DataManager apiManager();
}
