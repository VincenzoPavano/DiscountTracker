package com.vincenzopavano.discounttracker.data;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.data.model.response.Pokemon;
import com.vincenzopavano.discounttracker.data.remote.DiscountService;
import com.vincenzopavano.discounttracker.data.remote.PokemonService;
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
