package com.example.minilib;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.minilib.actions.FireBaseActions;
import com.example.minilib.actions.GoogleBooksActions;
import com.example.minilib.adapters.BookAdapter;
import com.example.minilib.classes.Book;

import java.util.ArrayList;

public class SuggestionActivity extends AppCompatActivity {


    private BookAdapter adapter;
    private ListView listView;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);




        listView = (ListView) findViewById(R.id.search_bar_list);
        adapter = new BookAdapter(this, new ArrayList<Book>());
        listView.setAdapter(adapter);

        final FireBaseActions fireBaseAPIHelper = new FireBaseActions(adapter);
        Button searchBarSubmitButton =  (Button) findViewById(R.id.search_bar_submit_button);
        final EditText searchBarText = (EditText) findViewById(R.id.search_bar_main);

        searchBarText.setFocusable(false);
        searchBarText.setHint("Seeing everyone's suggestions");


    }
}
