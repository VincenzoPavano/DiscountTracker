package com.vincenzopavano.discounttracker.features.detail;

import com.vincenzopavano.discounttracker.data.model.response.Pokemon;
import com.vincenzopavano.discounttracker.data.model.response.Statistic;
import com.vincenzopavano.discounttracker.features.base.MvpView;

public interface DetailMvpView extends MvpView {

    void showPokemon(Pokemon pokemon);

    void showStat(Statistic statistic);

    void showProgress(boolean show);

    void showError(Throwable error);
}
