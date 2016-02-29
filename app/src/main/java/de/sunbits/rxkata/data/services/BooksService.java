package de.sunbits.rxkata.data.services;

import android.util.Log;

import java.util.List;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.data.services.backend.BackendBooksService;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func0;

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

        Log.d(TAG, "getBooks Thread: " + Thread.currentThread().getName());

        return Observable.create(new Observable.OnSubscribe<List<Book>>() {
            @Override
            public void call(final Subscriber<? super List<Book>> subscriber) {

                Log.d(TAG, "getBooks - onSubscribe Thread: " + Thread.currentThread().getName());
                subscriber.onNext(backendBooksService.getBooks());
                subscriber.onCompleted();
            }
        }).cache();
    }

    public Observable<Author> getAuthor(final int id) {

        Log.d(TAG, "getAuthor id: " + id + " Thread: " + Thread.currentThread().getName());
        return Observable.defer(new Func0<Observable<Author>>() {
            @Override
            public Observable<Author> call() {

                Log.d(TAG, "getAuthor - defer Thread: " + Thread.currentThread().getName());
                return Observable.just(backendBooksService.getAuthor(id)).cache();
            }
        }); // Why is cache don't bring benefit ?
    }

    public Observable<Integer> insertBook(final Book book) {

        Log.d(TAG, "insertBook book:" + book);
        return Observable.defer(new Func0<Observable<Integer>>() {
            @Override
            public Observable<Integer> call() {

                Log.d(TAG, "insertBook - defer Thread: " + Thread.currentThread().getName());
                return Observable.just(backendBooksService.insertBook(book));
            }
        });
    }
}
