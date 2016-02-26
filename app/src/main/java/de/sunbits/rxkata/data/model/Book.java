package de.sunbits.rxkata.data.model;

/**
 * Created by matkoch on 26/02/16.
 */
public class Book {

    private int id;
    private String name;
    private int authorId;
    private boolean available;

    public int getId() {

        return id;
    }

    public void setId(final int id) {

        this.id = id;
    }

    public String getName() {

        return name;
    }

    public void setName(final String name) {

        this.name = name;
    }

    public int getAuthorId() {

        return authorId;
    }

    public void setAuthorId(final int authorId) {

        this.authorId = authorId;
    }

    public boolean isAvailable() {

        return available;
    }

    public void setAvailable(final boolean available) {

        this.available = available;
    }

    @Override
    public String toString() {

        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", authorId=" + authorId +
                ", available=" + available +
                '}';
    }
}
