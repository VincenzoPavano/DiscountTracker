package com.vincenzopavano.discounttracker;

import com.vincenzopavano.discounttracker.common.TestDataFactory;
import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.features.main.MainMvpView;
import com.vincenzopavano.discounttracker.features.main.MainPresenter;
import com.vincenzopavano.discounttracker.util.RxSchedulersOverrideRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Single;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Rule
    public final RxSchedulersOverrideRule overrideSchedulersRule = new RxSchedulersOverrideRule();

    @Mock
    MainMvpView mockMainMvpView;

    @Mock
    DataManager mockDataManager;

    private MainPresenter mainPresenter;

    @Before
    public void setUp() {
        mainPresenter = new MainPresenter(mockDataManager);
        mainPresenter.attachView(mockMainMvpView);
    }

    @After
    public void tearDown() {
        mainPresenter.detachView();
    }

    @Test
    public void testReturnDiscountList() throws Exception {
        // Given
        List<Discount> discountList = TestDataFactory.makeDiscountList();

        // When
        when(mockDataManager.getDiscounts()).thenReturn(Single.just(discountList));
        mainPresenter.getDiscounts();

        // Then
        verify(mockMainMvpView).showDiscounts(discountList);
        verify(mockMainMvpView, never()).showError(any(Throwable.class));
    }

    @Test
    public void testReturnDiscountListError() throws Exception {
        // Given
        List<Discount> discountList = null;

        // When
        when(mockDataManager.getDiscounts()).thenReturn(Single.error(new RuntimeException()));
        mainPresenter.getDiscounts();

        // Then
        verify(mockMainMvpView).showError(any(Throwable.class));
        verify(mockMainMvpView, never()).showDiscounts(ArgumentMatchers.anyList());
    }
}
