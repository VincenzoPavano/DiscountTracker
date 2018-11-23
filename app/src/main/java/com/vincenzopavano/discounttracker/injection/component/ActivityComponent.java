package com.vincenzopavano.discounttracker.injection.component;

import dagger.Subcomponent;
import com.vincenzopavano.discounttracker.features.detail.DetailActivity;
import com.vincenzopavano.discounttracker.features.main.MainActivity;
import com.vincenzopavano.discounttracker.injection.PerActivity;
import com.vincenzopavano.discounttracker.injection.module.ActivityModule;

@PerActivity
@Subcomponent(modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);
}
