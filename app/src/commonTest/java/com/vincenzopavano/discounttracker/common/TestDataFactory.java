package com.vincenzopavano.discounttracker.common;

import com.vincenzopavano.discounttracker.data.model.Discount;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

/**
 * Factory class that makes instances of data models with random field values. The aim of this class
 * is to help setting up test fixtures.
 */
public class TestDataFactory {

    private static final Random random = new Random();

    public static String randomUuid() {
        return UUID.randomUUID().toString();
    }

    public static List<Discount> makeDiscountList() {
        List<Discount> discounts = new ArrayList<>();
        discounts.add(new Discount(
                1,
                "Van Andel Arena",
                "$2 Beer and $2 Hot Dogs",
                "130 Fulton St W, Grand Rapids, MI 49503",
                "https://vanandelarena.com",
                "6167426600",
                42.963290,
                -85.672150,
                1
        ));

        discounts.add(new Discount(
                2,
                "HopCat",
                "Unlimited Crack Fries for Catpack members",
                "25 Ionia Ave SW, Grand Rapids, MI 49503",
                "https://hopcat.com",
                "6167426600",
                42.962380,
                -85.670590,
                1
        ));

        return discounts;
    }

    public static Discount getDiscount() {
        return makeDiscountList().get(0);
    }
}
