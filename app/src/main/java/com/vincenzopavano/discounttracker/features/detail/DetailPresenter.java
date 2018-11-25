package com.vincenzopavano.discounttracker.features.detail;

import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.features.base.BasePresenter;

import javax.inject.Inject;

public class DetailPresenter extends BasePresenter<DetailMvpView> {

    private final DataManager mDataManager;

    @Inject
    public DetailPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(DetailMvpView mvpView) {
        super.attachView(mvpView);
    }
}
