package com.vincenzopavano.discounttracker.features.main;

import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.features.base.MvpView;

import java.util.List;

public interface MainMvpView extends MvpView {

    void showDiscounts(List<Discount> discounts);

    void showError(Throwable e);
}
