package de.sunbits.rxkata.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import de.sunbits.rxkata.data.DataManager;
import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.ui.model.BookWithAuthor;
import de.sunbits.rxkata.ui.view.activities.MainActivity;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * Created by matkoch on 26/02/16.
 */
public class MainPresenterImpl extends BasePresenter<MainActivity> implements MainPresenter {

    private static final String TAG = "MainPresenterImpl";
    private final DataManager dataManager;

    public MainPresenterImpl(final DataManager dataManager) {

        this.dataManager = dataManager;
    }

    @Override
    public void loadBooks() {


        Observable<Book> books = dataManager.getBooks()
                .flatMap(new Func1<List<Book>, Observable<Book>>() {
                    @Override
                    public Observable<Book> call(final List<Book> books) {

                        Log.d(TAG, "flatMap - getBooks Thread:" + Thread.currentThread().getName());
                        return Observable.from(books);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());//create new Thread for each Subscriber

        Observable<Author> authors = books // first Subscriber to Observable books
                .flatMap(new Func1<Book, Observable<Author>>() {
                    @Override
                    public Observable<Author> call(final Book book) {

                        Log.d(TAG, "flatMap - getAuthor Thread:" + Thread.currentThread().getName());
                        return dataManager.getAuthor(book.getAuthorId());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());

        Observable.zip(books, authors, new Func2<Book, Author, BookWithAuthor>() { // SECOND Subscriber to Observable books
            @Override
            public BookWithAuthor call(final Book book, final Author author) {

                Log.d(TAG, "zip - Thread:" + Thread.currentThread().getName());
                BookWithAuthor bookWithAuthor = new BookWithAuthor();
                bookWithAuthor.setBook(book);
                bookWithAuthor.setAuthor(author);
                return bookWithAuthor;
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .toList()
                .subscribe(new Action1<List<BookWithAuthor>>() {
                    @Override
                    public void call(final List<BookWithAuthor> bookWithAuthors) {

                        Log.d(TAG, "subscriber - Thread:" + Thread.currentThread().getName());
                        getMvpView().showBooks(bookWithAuthors);
                    }
                });

    }

    @Override
    public void insertBook() {

        final Book book = new Book();
        book.setName("New Testbook");
        book.setAvailable(true);

        class InsertBooksAsyncTask extends AsyncTask<Integer, Void, Void> {

            @Override
            protected Void doInBackground(final Integer... params) {

                dataManager.getAuthor(params[0])
                        .subscribe(new Action1<Author>() {
                            @Override
                            public void call(final Author author) {

                                book.setAuthorId(author.getId());

                                dataManager.insertBook(book, new DataManager.InsertBookCallback() {
                                    @Override
                                    public void onCallback(final int id) {

                                        Log.d(TAG, "insertBook - added success");
                                        loadBooks();
                                    }
                                });
                            }
                        });
                return null;
            }
        }
        InsertBooksAsyncTask task = new InsertBooksAsyncTask();
        task.execute(1);
    }
}
