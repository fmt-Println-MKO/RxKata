package de.sunbits.rxkata.ui.presenter;

import de.sunbits.rxkata.ui.view.MvpView;

/**
 * Created by matkoch on 26/02/16.
 */
public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);

    void detachView();
}