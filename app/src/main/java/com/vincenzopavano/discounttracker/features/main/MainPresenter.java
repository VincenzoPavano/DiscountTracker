package com.vincenzopavano.discounttracker.features.main;

import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.features.base.BasePresenter;
import com.vincenzopavano.discounttracker.util.rx.scheduler.SchedulerUtils;

import javax.inject.Inject;

public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager mDataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    void getDiscounts() {
        mDataManager
                .getDiscounts()
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        discounts -> {
                            getView().showDiscounts(discounts);
                        },
                        throwable -> {
                            getView().showError(throwable);
                        }
                );
    }
}
