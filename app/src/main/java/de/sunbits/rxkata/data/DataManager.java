package de.sunbits.rxkata.data;

import java.util.List;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;
import de.sunbits.rxkata.data.services.BooksService;
import de.sunbits.rxkata.data.services.FailingService;

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

    public interface GetBooksCallback {

        void onCallback(List<Book> books);
    }

    public interface GetAuthorCallback {

        void onCallback(Author author);
    }

    public interface InsertBookCallback {

        void onCallback(int id);
    }

    public void getBooks(GetBooksCallback callback) {

        List<Book> books = booksService.getBooks();
        callback.onCallback(books);
    }

    public void getAuthor(int id, GetAuthorCallback callback) {

        Author author = booksService.getAuthor(id);

        callback.onCallback(author);
    }

    public void insertBook(Book book, InsertBookCallback callback) {

        int id = booksService.insertBook(book);

        callback.onCallback(id);
    }
}
