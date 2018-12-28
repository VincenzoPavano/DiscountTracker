package com.vincenzopavano.discounttracker.common.injection.module;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.data.remote.DiscountService;
import com.vincenzopavano.discounttracker.injection.ApplicationContext;

import static org.mockito.Mockito.mock;

/**
 * Provides application-level dependencies for an app running on a testing environment This allows
 * injecting mocks if necessary.
 */
@Module
public class ApplicationTestModule {
    private final Application application;

    public ApplicationTestModule(Application application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return application;
    }

    @Provides
    @ApplicationContext
    Context provideContext() {
        return application;
    }

    /**
     * ********** MOCKS ***********
     */
    @Provides
    @Singleton
    DataManager providesDataManager() {
        return mock(DataManager.class);
    }

    @Provides
    @Singleton
    DiscountService provideMvpBoilerplateService() {
        return mock(DiscountService.class);
    }
}
