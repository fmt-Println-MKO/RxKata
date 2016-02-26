package de.sunbits.rxkata.ui.view.activities;

import java.util.List;

import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.ui.model.BookWithAuthor;
import de.sunbits.rxkata.ui.view.MvpView;

/**
 * Created by matkoch on 26/02/16.
 */
public interface MainActivity extends MvpView {

    void showBooks(List<BookWithAuthor> books);
}
