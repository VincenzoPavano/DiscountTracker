package com.vincenzopavano.discounttracker;

import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.vincenzopavano.discounttracker.common.TestComponentRule;
import com.vincenzopavano.discounttracker.common.TestDataFactory;
import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.features.main.MainActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.RuleChain;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;

import java.util.List;

import io.reactivex.Single;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private final TestComponentRule componentRule =
            new TestComponentRule(InstrumentationRegistry.getTargetContext());
    private final ActivityTestRule<MainActivity> mainActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    // TestComponentRule needs to go first to make sure the Dagger ApplicationTestComponent is set
    // in the Application before any Activity is launched.
    @Rule
    public TestRule chain = RuleChain.outerRule(componentRule).around(mainActivityTestRule);

    @Test
    public void testDiscountsLoad() {
        // Given
        List<Discount> discountList = TestDataFactory.makeDiscountList();

        // When
        stubDataManagerGetDiscountList(Single.just(discountList));
        mainActivityTestRule.launchActivity(null);

        // Then
        for (Discount discount : discountList) {
            onView(withText(discount.getCompany())).check(matches(isDisplayed()));
            onView(withText(discount.getAddress())).check(matches(isDisplayed()));
        }
    }

    @Test
    public void testLaunchDetailActivity() {
        // Given
        List<Discount> discountList = TestDataFactory.makeDiscountList();

        // When
        stubDataManagerGetDiscountList(Single.just(discountList));
        mainActivityTestRule.launchActivity(null);
        onView(withText(discountList.get(0).getCompany())).perform(click());

        // Then
        onView(withId(R.id.text_company)).check(matches(withText(discountList.get(0).getCompany())));
        onView(withId(R.id.text_description)).check(matches(withText(discountList.get(0).getDescription())));
    }

    @Test
    public void checkErrorViewDisplays() {
        // Given
        stubDataManagerGetDiscountList(Single.error(new RuntimeException()));

        // When
        mainActivityTestRule.launchActivity(null);

        // Then
        onView(withText(R.string.error_discount)).inRoot(withDecorView(not(is(mainActivityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    public void stubDataManagerGetDiscountList(Single<List<Discount>> single) {
        when(componentRule.getMockApiManager().getDiscounts()).thenReturn(single);
    }
}
