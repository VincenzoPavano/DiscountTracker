package com.vincenzopavano.discounttracker.data;

import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.data.remote.DiscountService;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class DataManager {

    private DiscountService mDiscountService;

    @Inject
    public DataManager(DiscountService discountService) {
        this.mDiscountService = discountService;
    }

    public Single<List<Discount>> getDiscounts() {
        return mDiscountService.getDiscountList();
    }
}
