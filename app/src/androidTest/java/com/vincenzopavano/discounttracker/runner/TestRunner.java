package com.vincenzopavano.discounttracker.runner;

import android.app.Application;
import android.content.Context;

import io.appflate.restmock.android.RESTMockTestRunner;
import com.vincenzopavano.discounttracker.MvpStarterApplication;

public class TestRunner extends RESTMockTestRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        return super.newApplication(cl, MvpStarterApplication.class.getName(), context);
    }
}
