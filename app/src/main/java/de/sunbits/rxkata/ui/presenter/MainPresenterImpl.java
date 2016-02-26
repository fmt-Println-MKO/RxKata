package de.sunbits.rxkata.ui.presenter;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import de.sunbits.rxkata.data.DataManager;
import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.ui.model.BookWithAuthor;
import de.sunbits.rxkata.ui.view.activities.MainActivity;

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


        class GetBooksAsyncTask extends AsyncTask<Void, Void, List<BookWithAuthor>> {

            @Override
            protected List<BookWithAuthor> doInBackground(final Void... params) {

                final List<BookWithAuthor> bookWithAuthors = new ArrayList<BookWithAuthor>();
                dataManager.getBooks(new DataManager.GetBooksCallback() {
                    @Override
                    public void onCallback(final List<Book> books) {


                        for (final Book book : books) {

                            dataManager.getAuthor(book.getAuthorId(), new DataManager.GetAuthorCallback() {
                                @Override
                                public void onCallback(final Author author) {

                                    final BookWithAuthor bookWithAuthor = new BookWithAuthor();
                                    bookWithAuthor.setBook(book);
                                    bookWithAuthor.setAuthor(author);
                                    bookWithAuthors.add(bookWithAuthor);
                                }
                            });
                        }

                    }
                });

                return bookWithAuthors;
            }

            @Override
            protected void onPostExecute(final List<BookWithAuthor> bookWithAuthors) {

                Log.d(TAG, "loadBooks - bookWithAuthors" + bookWithAuthors);
                getMvpView().showBooks(bookWithAuthors);
            }
        }

        GetBooksAsyncTask task = new GetBooksAsyncTask();
        task.execute();

    }

    @Override
    public void insertBook() {

        final Book book = new Book();
        book.setName("New Testbook");
        book.setAvailable(true);

        class InsertBooksAsyncTask extends AsyncTask<Integer, Void, Void> {

            @Override
            protected Void doInBackground(final Integer... params) {

                dataManager.getAuthor(params[0], new DataManager.GetAuthorCallback() {
                    @Override
                    public void onCallback(final Author author) {

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
