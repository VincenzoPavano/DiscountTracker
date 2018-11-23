package com.vincenzopavano.discounttracker.data.remote;


import com.vincenzopavano.discounttracker.data.model.Discount;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DiscountService {

    @GET("discount.php")
    Single<Discount> getDiscountList();

}