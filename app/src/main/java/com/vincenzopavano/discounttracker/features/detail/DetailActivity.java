package com.vincenzopavano.discounttracker.features.detail;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.vincenzopavano.discounttracker.R;
import com.vincenzopavano.discounttracker.data.model.Discount;
import com.vincenzopavano.discounttracker.features.base.BaseActivity;
import com.vincenzopavano.discounttracker.injection.component.ActivityComponent;
import com.vincenzopavano.discounttracker.util.NetworkUtil;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailActivity extends BaseActivity implements DetailMvpView {

    @Inject
    DetailPresenter detailPresenter;

    @BindView(R.id.text_company)
    TextView textCompany;

    @BindView(R.id.button_call)
    Button buttonCall;

    @BindView(R.id.button_map)
    Button buttonMap;

    @BindView(R.id.button_website)
    Button buttonWebsite;

    @BindView(R.id.text_description)
    TextView textDescription;

    private Discount mDiscount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get Discount information from previous intent
        if (getIntent().getExtras() != null) {
            Bundle extras = getIntent().getExtras();
            mDiscount = new Discount(
                    extras.getInt("id"),
                    extras.getString("company"),
                    extras.getString("description"),
                    extras.getString("address"),
                    extras.getString("website"),
                    extras.getString("phone"),
                    extras.getDouble("latitude"),
                    extras.getDouble("longitude"),
                    extras.getInt("categoryId")
            );

            // Update Action Bar Title
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setTitle(mDiscount.getCompany());
            }

            // Bind layout elements
            textCompany.setText(mDiscount.getCompany());
            textDescription.setText(mDiscount.getDescription());
        }
    }

    @OnClick(R.id.button_call)
    public void callCompany() {
        Intent intent = new Intent(Intent.ACTION_DIAL);

        if (NetworkUtil.isTelephonyEnabled(this)) {
            intent.setData(Uri.parse(mDiscount.getPhone()));
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.detail_call_error, Toast.LENGTH_LONG).show();
        }
    }

    @OnClick(R.id.button_map)
    public void openMap() {
        Uri uri = Uri.parse("geo:0,0?q=" + mDiscount.getCompany() + ", " + mDiscount.getAddress());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @OnClick(R.id.button_website)
    public void openWebsite() {
        Uri uri = Uri.parse(mDiscount.getWebsite());
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_detail;
    }

    @Override
    protected void inject(ActivityComponent activityComponent) {
        activityComponent.inject(this);
    }

    @Override
    protected void attachView() {
        detailPresenter.attachView(this);
    }

    @Override
    protected void detachPresenter() {
        detailPresenter.detachView();
    }

    public void setDiscount(Discount discount) {
        this.mDiscount = discount;
    }
}
