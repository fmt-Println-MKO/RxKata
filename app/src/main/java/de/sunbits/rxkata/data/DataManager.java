package de.sunbits.rxkata.data;

import android.util.Log;

import java.util.List;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.data.services.BooksService;
import de.sunbits.rxkata.data.services.FailingService;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by matkoch on 26/02/16.
 */
public class DataManager {

    private static final String TAG = "DataManager";
    private BooksService booksService;
    private FailingService failingService;

    public DataManager() {

        booksService = new BooksService();
        failingService = new FailingService();
    }

    public Observable<List<Book>> getBooks() {

        return booksService.getBooks();
    }

    public Observable<Author> getAuthor(int id) {

        return booksService.getAuthor(id);
    }

    public Observable<Integer> insertBook(Book book) {

        return booksService.insertBook(book);
    }

    public Observable<Book> getAvailableBooks() {

        return booksService.getBooks()
                .flatMap(new Func1<List<Book>, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(final List<Book> books) {

                        Log.d(TAG, "flatMap - getAvailableBooks Thread:" + Thread.currentThread().getName());
                        return Observable.from(books);
                    }
                })
                .filter(new Func1<Book, Boolean>() {
                    @Override
                    public Boolean call(final Book book) {

                        return book.isAvailable();
                    }
                });
    }
}
