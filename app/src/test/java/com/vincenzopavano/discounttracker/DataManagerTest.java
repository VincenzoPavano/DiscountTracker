package com.vincenzopavano.discounttracker;

import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.data.remote.DiscountService;
import com.vincenzopavano.discounttracker.util.RxSchedulersOverrideRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class DataManagerTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    private DiscountService mockDiscountService;

    private DataManager dataManager;

    @Before
    public void setUp() {
        dataManager = new DataManager(mockDiscountService);
    }

    @Test
    public void testGetDiscountsAndEmit() {
//        List<Discount> discountList = TestDataFactory.makeDiscountList();
//
//        when(mockDiscountService.getDiscountList())
//                .thenReturn(Single.just(discountList));
//
//        dataManager
//                .getDiscounts()
//                .test()
//                .assertComplete()
//                .assertValue(discountList);
    }
}
