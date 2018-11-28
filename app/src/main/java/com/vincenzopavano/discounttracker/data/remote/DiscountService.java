package com.vincenzopavano.discounttracker.data.remote;


import com.vincenzopavano.discounttracker.data.model.Discount;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface DiscountService {

    @GET("discount.php")
    Single<List<Discount>> getDiscountList();

}