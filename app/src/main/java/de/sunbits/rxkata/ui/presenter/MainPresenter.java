package de.sunbits.rxkata.ui.presenter;

import de.sunbits.rxkata.ui.view.activities.MainActivity;

/**
 * Created by matkoch on 26/02/16.
 */
public interface MainPresenter extends Presenter<MainActivity> {


    void loadBooks();

    void insertBook();
}
