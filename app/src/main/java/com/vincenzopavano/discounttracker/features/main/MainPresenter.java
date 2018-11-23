package com.vincenzopavano.discounttracker.features.main;

import javax.inject.Inject;

import com.vincenzopavano.discounttracker.data.DataManager;
import com.vincenzopavano.discounttracker.features.base.BasePresenter;
import com.vincenzopavano.discounttracker.injection.ConfigPersistent;
import com.vincenzopavano.discounttracker.util.rx.scheduler.SchedulerUtils;

@ConfigPersistent
public class MainPresenter extends BasePresenter<MainMvpView> {

    private final DataManager dataManager;

    @Inject
    public MainPresenter(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(MainMvpView mvpView) {
        super.attachView(mvpView);
    }

    public void getPokemon(int limit) {
        checkViewAttached();
        getView().showProgress(true);
        dataManager
                .getPokemonList(limit)
                .compose(SchedulerUtils.ioToMain())
                .subscribe(
                        pokemons -> {
                            getView().showProgress(false);
                            getView().showPokemon(pokemons);
                        },
                        throwable -> {
                            getView().showProgress(false);
                            getView().showError(throwable);
                        });
    }
}
