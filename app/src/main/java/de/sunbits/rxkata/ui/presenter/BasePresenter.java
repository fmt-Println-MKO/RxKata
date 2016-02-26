package de.sunbits.rxkata.ui.presenter;

import de.sunbits.rxkata.ui.view.MvpView;

/**
 * Created by matkoch on 26/02/16.
 */
public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mMvpView;

    @Override
    public void attachView(T mvpView) {

        mMvpView = mvpView;
    }

    @Override
    public void detachView() {

        mMvpView = null;
    }

    public boolean isViewAttached() {

        return mMvpView != null;
    }

    public T getMvpView() {

        return mMvpView;
    }

    public void checkViewAttached() {

        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {

        public MvpViewNotAttachedException() {

            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }

}