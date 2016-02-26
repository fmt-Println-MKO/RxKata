package de.sunbits.rxkata;

import android.app.Application;

import de.sunbits.rxkata.data.DataManager;
import de.sunbits.rxkata.ui.presenter.MainPresenter;
import de.sunbits.rxkata.ui.presenter.MainPresenterImpl;

/**
 * Created by matkoch on 26/02/16.
 */
public class RxKataAppilcation extends Application {

    private RxKataModules rxKataModules;

    @Override
    public void onCreate() {

        super.onCreate();

        DataManager dataManager = new DataManager();
        MainPresenter mainPresenter = new MainPresenterImpl(dataManager);
        rxKataModules = new RxKataModules(mainPresenter, dataManager);
    }
}
