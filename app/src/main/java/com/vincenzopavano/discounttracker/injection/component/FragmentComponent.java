package com.vincenzopavano.discounttracker.injection.component;

import dagger.Subcomponent;
import com.vincenzopavano.discounttracker.injection.PerFragment;
import com.vincenzopavano.discounttracker.injection.module.FragmentModule;

/**
 * This component inject dependencies to all Fragments across the application
 */
@PerFragment
@Subcomponent(modules = FragmentModule.class)
public interface FragmentComponent {
}
