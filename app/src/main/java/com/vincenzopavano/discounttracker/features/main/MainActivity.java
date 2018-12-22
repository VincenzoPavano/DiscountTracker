package com.vincenzopavano.discounttracker.features.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.vincenzopavano.discounttracker.R;
import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.features.base.BaseActivity;
import com.vincenzopavano.discounttracker.features.detail.DetailActivity;
import com.vincenzopavano.discounttracker.injection.component.ActivityComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import timber.log.Timber;

public class MainActivity extends BaseActivity implements MainMvpView {

    @Inject
    MainPresenter mainPresenter;

    @Inject
    MainAdapter mainAdapter;

    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.recycler_discount)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Swipe layout customizations
        swipeRefreshLayout.setProgressBackgroundColorSchemeResource(R.color.colorPrimary);
        swipeRefreshLayout.setColorSchemeResources(R.color.white);
        swipeRefreshLayout.setOnRefreshListener(() -> mainPresenter.getDiscounts());

        // RecyclerView customizations
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(mainAdapter);

        // Respond when a discount is selected
        discountSelected();

        // Get initial discount list
        mainPresenter.getDiscounts();
    }

    public void discountSelected() {
        Disposable disposable = mainAdapter
                .getDiscountClick()
                .subscribe(
                        discount -> {
                            Bundle extras = new Bundle();
                            extras.putInt("id", discount.getId());
                            extras.putInt("categoryId", discount.getCategoryId());
                            extras.putString("company", discount.getCompany());
                            extras.putString("description", discount.getDescription());
                            extras.putString("address", discount.getAddress());
                            extras.putString("website", discount.getWebsite());
                            extras.putString("phone", discount.getPhone());
                            extras.putDouble("latitude", discount.getLatitude());
                            extras.putDouble("longitude", discount.getLongitude());

                            Intent intent = new Intent(this, DetailActivity.class);
                            intent.putExtras(extras);
                            startActivity(intent);
                        },
                        throwable -> {
                            Timber.e(throwable, "Discount click failed");
                        });
        mainPresenter.addDisposable(disposable);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        mainPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        mainPresenter.detachView();
    }

    @Override
    public void showDiscounts(List<Discount> discounts) {
        mainAdapter.setDiscounts(discounts);
        recyclerView.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void showError(Throwable e) {
        Toast.makeText(this, getText(R.string.error_discount), Toast.LENGTH_LONG).show();
        Timber.e(e);
    }
}