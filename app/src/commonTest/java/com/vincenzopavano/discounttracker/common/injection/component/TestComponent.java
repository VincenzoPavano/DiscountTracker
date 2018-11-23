package com.vincenzopavano.discounttracker.common.injection.component;

import javax.inject.Singleton;

import dagger.Component;
import com.vincenzopavano.discounttracker.common.injection.module.ApplicationTestModule;
import com.vincenzopavano.discounttracker.injection.component.AppComponent;

@Singleton
@Component(modules = ApplicationTestModule.class)
public interface TestComponent extends AppComponent {
}
