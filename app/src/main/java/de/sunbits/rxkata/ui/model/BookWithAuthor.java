package de.sunbits.rxkata.ui.model;

import de.sunbits.rxkata.data.model.Author;
import de.sunbits.rxkata.data.model.Book;

/**
 * Created by matkoch on 26/02/16.
 */
public class BookWithAuthor {

    private Book book;
    private Author author;

    public Book getBook() {

        return book;
    }

    public void setBook(final Book book) {

        this.book = book;
    }

    public Author getAuthor() {

        return author;
    }

    public void setAuthor(final Author author) {

        this.author = author;
    }

    @Override
    public String toString() {

        return "BookWithAuthor{" +
                "book=" + book +
                ", author=" + author +
                '}';
    }
}
