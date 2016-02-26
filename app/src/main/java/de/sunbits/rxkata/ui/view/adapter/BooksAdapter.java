package de.sunbits.rxkata.ui.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.sunbits.rxkata.R;
import de.sunbits.rxkata.ui.model.BookWithAuthor;

/**
 * Created by matkoch on 26/02/16.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookViewHolder> {

    private final List<BookWithAuthor> books;

    public BooksAdapter() {

        this.books = new ArrayList<>();
    }

    public void setBooks(List<BookWithAuthor> books) {

        this.books.clear();
        this.books.addAll(books);
        this.notifyDataSetChanged();
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new BookViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {

        BookWithAuthor book = books.get(position);
        holder.bind(book);
    }

    @Override
    public int getItemCount() {

        return books.size();
    }

    public static class BookViewHolder extends RecyclerView.ViewHolder {

        TextView bookName;
        TextView authorName;
        TextView authorAge;
        TextView bookAvailable;


        BookViewHolder(View view) {

            super(view);
            bookName = (TextView) view.findViewById(R.id.text_book_name);
            authorName = (TextView) view.findViewById(R.id.text_author_name);
            authorAge = (TextView) view.findViewById(R.id.text_author_age);
            bookAvailable = (TextView) view.findViewById(R.id.text_book_available);
        }

        public void bind(final BookWithAuthor book) {

            bookName.setText(book.getBook().getName());
            authorName.setText(book.getAuthor().getName());
            authorAge.setText(String.valueOf(book.getAuthor().getAge()));
            bookAvailable.setText(String.valueOf(book.getBook().isAvailable()));

        }
    }
}
