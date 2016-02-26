package de.sunbits.rxkata.ui.view.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import de.sunbits.rxkata.R;
import de.sunbits.rxkata.RxKataModules;
import de.sunbits.rxkata.ui.model.BookWithAuthor;
import de.sunbits.rxkata.ui.presenter.MainPresenter;
import de.sunbits.rxkata.ui.view.adapter.BooksAdapter;

/**
 * Created by matkoch on 26/02/16.
 */
public class MainActivityImpl extends AppCompatActivity implements MainActivity {


    private static final String TAG = "MainActivityImpl";
    private MainPresenter presenter;
    private RecyclerView recyclerView;
    private BooksAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        presenter = RxKataModules.mainPresenter();
        adapter = new BooksAdapter();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_books);
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                presenter.insertBook();
            }
        });
    }

    @Override
    protected void onResume() {

        super.onResume();
        presenter.attachView(this);
        Log.d(TAG, "onResume - Thread:" + Thread.currentThread().getName());
        presenter.loadBooks();
        Log.d(TAG, "onResume - Thread:" + Thread.currentThread().getName());
    }

    @Override
    protected void onPause() {

        super.onPause();
        presenter.detachView();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        presenter = null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showBooks(final List<BookWithAuthor> books) {

        adapter.setBooks(books);
        adapter.notifyDataSetChanged();
    }
}
