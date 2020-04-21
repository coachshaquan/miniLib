package com.example.minilib;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.minilib.actions.GoogleBooksActions;
import com.example.minilib.adapters.BookAdapter;
import com.example.minilib.classes.Book;
import com.google.firebase.database.ChildEventListener;




import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.function.Function;

public class SearchActivity extends AppCompatActivity {

    private Button addBookButton;
    private ChildEventListener childEventListener;

    private BookAdapter adapter;
    private ListView listView;
    private GoogleBooksActions googleBooksAPI;


    private ArrayList<Book> bookList = new ArrayList<>();
    private String apiKey = "?key=AIzaSyCooWN8Vnws2squePqTPoYrW315PYqMxj4";


    public SearchActivity(){
            /*
             Dummy Book object for testing
     */
        Book book = new Book(
                "VdxNlZidgikC",
                "9780860685111",
                "Maya Angelou",
                "I Know Why the Caged Bird Sings",
                "2020",
                "http://books.google.com/books/content?id=E2PTzaTZEQ0C&printsec=frontcover&img=1&zoom=5&edge=curl&imgtk=AFLRE71oGEqgZ-tOQ3Iw70PFov8tapvU3UT_aS53TelBdxJTNZV6GS8umXKbUIs3mWdCr_oXuYOOijrIABHHkfbbTIaQTUIeBK5sAIxqShVKGJ39ayki_zM4RRUgXpcq8ULaMnbIRgnR&source=gbs_api\",\n" +
                        "   \"thumbnail\": \"http://books.google.com/books/content?id=E2PTzaTZEQ0C&printsec=frontcover&img=1&zoom=1&edge=curl&imgtk=AFLRE73OGmDsaVaSpqnyvkHWuh8roOuYGk9ahcuTAR-_An1JdWUcnuI651-dPCvjTzHlVcMt0o0o-sQ3PBQK0TwxL3kv_PxjmnS0XWPwNhtNY7PkP6F0GAqfMFy_PUFzcPBYRvfaqtfm&source=gbs_api",
                null
        );


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        final Context mContext = this;
        googleBooksAPI = new GoogleBooksActions(mContext);
        Button searchBarSubmitButton =  (Button) findViewById(R.id.search_bar_submit_button);
        final EditText searchBarText = (EditText) findViewById(R.id.search_bar_main);

        adapter = new BookAdapter(this, bookList);
        listView = (ListView) findViewById(R.id.search_bar_list);
        listView.setAdapter(adapter);

        searchBarSubmitButton.setOnClickListener( new View.OnClickListener(){
            @Override
            public void onClick(View view){
                String url;
                String searchBarInput = searchBarText.getText().toString();
                searchBarInput= searchBarInput.replaceAll("s+", "+");

                searchBarText.setText("");

                // An ISBN is a regular expression with either 10 or 13 numbers.
                if(searchBarInput.matches("\\d{10,13}$")){
                    url = "https://www.googleapis.com/books/v1/volumes?q=isbn:"  + searchBarInput;
                }
                else{
                    url = "https://www.googleapis.com/books/v1/volumes?q="  + searchBarInput;
                }

                googleBooksAPI.searchByString(url, adapter);

            }

        });


    }


}
