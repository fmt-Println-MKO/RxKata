package de.sunbits.rxkata.data.services;

import android.util.Log;

import java.util.List;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.data.services.backend.BackendBooksService;
import rx.Observable;

/**
 * Created by matkoch on 26/02/16.
 */
public class BooksService {

    private static final String TAG = "BooksService";

    private BackendBooksService backendBooksService;

    public BooksService() {

        backendBooksService = new BackendBooksService();
    }

    public Observable<List<Book>> getBooks() {

        Log.d(TAG, "getBooks");

        return Observable.just(backendBooksService.getBooks());
    }

    public Observable<Author> getAuthor(int id) {

        Log.d(TAG, "getAuthor id: " + id);
        return Observable.just(backendBooksService.getAuthor(id));
    }

    public int insertBook(Book book) {

        Log.d(TAG, "insertBook book:" + book);
        return backendBooksService.insertBook(book);
    }
}
