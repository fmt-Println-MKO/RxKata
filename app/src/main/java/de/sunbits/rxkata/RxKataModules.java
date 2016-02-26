package de.sunbits.rxkata;

import de.sunbits.rxkata.data.DataManager;
import de.sunbits.rxkata.ui.presenter.MainPresenter;

/**
 * Created by matkoch on 26/02/16.
 */
public class RxKataModules {

    private static RxKataModules INSTANCE;
    private final MainPresenter mainPresenter;
    private final DataManager dataManager;

    public RxKataModules(MainPresenter mainPresenter, DataManager dataManager) {

        this.mainPresenter = mainPresenter;
        this.dataManager = dataManager;
        INSTANCE = this;
    }

    private static RxKataModules instance() {

        if (INSTANCE == null) {
            throw new IllegalStateException("RxKataModules not initialized!");
        }
        return INSTANCE;
    }

    public static MainPresenter mainPresenter() {

        return instance().mainPresenter;
    }

    public static DataManager dataManager() {

        return instance().dataManager;
    }
}
