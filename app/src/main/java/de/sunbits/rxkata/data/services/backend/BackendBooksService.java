package de.sunbits.rxkata.data.services.backend;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;

/**
 * Created by matkoch on 26/02/16.
 */
public class BackendBooksService {

    private static final String TAG = "BackendBooksService";

    private List<Book> books;
    private Map<Integer, Author> authors;

    public BackendBooksService() {

        authors = new HashMap<>();
        books = new ArrayList<>();

        Author author1 = new Author();
        author1.setAge(40);
        author1.setId(1);
        author1.setName("Schneider");

        authors.put(author1.getId(), author1);
        Author author2 = new Author();
        author2.setAge(30);
        author2.setId(2);
        author2.setName("Mueller");
        authors.put(author2.getId(), author2);
        Author author3 = new Author();
        author3.setAge(30);
        author3.setId(3);
        author3.setName("Schmidt");
        authors.put(author3.getId(), author3);

        Book book1 = new Book();
        book1.setAuthorId(author1.getId());
        book1.setAvailable(true);
        book1.setId(1);
        book1.setName("RxJava");
        books.add(book1);

        Book book2 = new Book();
        book2.setAuthorId(author1.getId());
        book2.setAvailable(true);
        book2.setId(2);
        book2.setName("Android");
        books.add(book2);

        Book book3 = new Book();
        book3.setAuthorId(author1.getId());
        book3.setAvailable(false);
        book3.setId(3);
        book3.setName("Google");
        books.add(book3);

        Book book4 = new Book();
        book4.setAuthorId(author2.getId());
        book4.setAvailable(true);
        book4.setId(4);
        book4.setName("Perl");
        books.add(book4);

        Book book5 = new Book();
        book5.setAuthorId(author3.getId());
        book5.setAvailable(false);
        book5.setId(5);
        book5.setName("Scala");
        books.add(book5);
    }

    public List<Book> getBooks() {

        Log.d(TAG, "getBooks - oh network is slow");
        try {
            Thread.sleep(2000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.books;
    }

    public Author getAuthor(int id) {

        Log.d(TAG, "getAuthor - oh network is slow: id: " + id);
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return this.authors.get(id);
    }

    public int insertBook(Book book) {

        Log.d(TAG, "insertBook - oh network is slow");
        try {
            Thread.sleep(1000l);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        book.setId(this.books.size());
        this.books.add(book);
        return book.getId();
    }
}
