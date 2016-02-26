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
import rx.functions.Action1;
import rx.functions.Func1;
import rx.functions.Func2;

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

                        return Observable.from(books);
                    }
                });

        Observable<Author> authors = books.flatMap(new Func1<Book, Observable<Author>>() {
            @Override
            public Observable<Author> call(final Book book) {

                return dataManager.getAuthor(book.getAuthorId());
            }
        });

        Observable.zip(books, authors, new Func2<Book, Author, BookWithAuthor>() {
            @Override
            public BookWithAuthor call(final Book book, final Author author) {

                BookWithAuthor bookWithAuthor = new BookWithAuthor();
                bookWithAuthor.setBook(book);
                bookWithAuthor.setAuthor(author);
                return bookWithAuthor;
            }
        })
                .toList()
                .subscribe(new Action1<List<BookWithAuthor>>() {
                    @Override
                    public void call(final List<BookWithAuthor> bookWithAuthors) {

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
