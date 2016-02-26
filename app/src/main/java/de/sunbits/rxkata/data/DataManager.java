package de.sunbits.rxkata.data;

import java.util.List;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.data.services.BooksService;
import de.sunbits.rxkata.data.services.FailingService;
import rx.Observable;

/**
 * Created by matkoch on 26/02/16.
 */
public class DataManager {

    private BooksService booksService;
    private FailingService failingService;

    public DataManager() {

        booksService = new BooksService();
        failingService = new FailingService();
    }


    public interface InsertBookCallback {

        void onCallback(int id);
    }

    public Observable<List<Book>> getBooks() {

        return booksService.getBooks();
    }

    public Observable<Author> getAuthor(int id) {

        return booksService.getAuthor(id);
    }

    public void insertBook(Book book, InsertBookCallback callback) {

        int id = booksService.insertBook(book);

        callback.onCallback(id);
    }
}
