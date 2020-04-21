package com.example.minilib;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.minilib.actions.FireBaseActions;
import com.example.minilib.actions.GoogleBooksActions;
import com.example.minilib.adapters.BookAdapter;
import com.example.minilib.classes.Book;
import com.google.firebase.database.DatabaseReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

public class DPPBookActivity extends AppCompatActivity {

    private Book thisBook;
    private GoogleBooksActions googleBooksAPI;
    private Button bookAddToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_dpp);

        String intentISBN = getIntent().getStringExtra("Book_ID");
        String url = "https://www.googleapis.com/books/v1/volumes/" + intentISBN;



        googleBooksAPI = new GoogleBooksActions(this);
        googleBooksAPI.searchByISBN(url, this, R.id.book_dpp_metadata_text);

        final FireBaseActions fireBaseAPIHelper = new FireBaseActions();
        bookAddToCart = (Button) findViewById(R.id.book_dpp_atc);
        bookAddToCart.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                thisBook = googleBooksAPI.getReturnBook();

                /*
                * Add child node to "books". Because .child() indexes the list by bookID, there
                * are no duplicates to return
                * */
                fireBaseAPIHelper.getBooksDatabaseReference().child(thisBook.getBookID())
                        .setValue(thisBook);

                /* Give feedback and trigger new page */
                Toast.makeText(v.getContext(),
                        "Book added to Database", Toast.LENGTH_SHORT).show();
                Intent searchIntent = new Intent(DPPBookActivity.this, SuggestionActivity.class);
                startActivity(searchIntent);

            }
        });


    }

}

