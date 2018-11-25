package com.vincenzopavano.discounttracker.features.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vincenzopavano.discounttracker.data.model.Discount;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Discount> discountList;
    private Subject<Discount> discountClickSubject;

    @Inject
    MainAdapter() {
        discountClickSubject = PublishSubject.create();
        discountList = Collections.emptyList();
    }

    void setDiscounts(List<Discount> discounts) {
        this.discountList = discounts;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(android.R.layout.simple_list_item_2, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Discount discount = this.discountList.get(i);
        viewHolder.company.setText(discount.getCompany());
        viewHolder.address.setText(discount.getAddress());
        viewHolder.setDiscount(discount);
    }

    @Override
    public int getItemCount() {
        return discountList.size();
    }

    Observable<Discount> getDiscountClick() {
        return discountClickSubject;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(android.R.id.text1)
        TextView company;

        @BindView(android.R.id.text2)
        TextView address;

        private Discount mDiscount;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> discountClickSubject.onNext(mDiscount));
        }

        void setDiscount(Discount discount) {
            this.mDiscount = discount;
        }
    }
}
