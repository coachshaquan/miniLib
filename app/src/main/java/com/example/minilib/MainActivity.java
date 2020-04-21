package com.example.minilib;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;


public class MainActivity extends AppCompatActivity {


    private ChildEventListener childEventListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button searchPage, suggestionPage;
        setContentView(R.layout.activity_main);


        suggestionPage = findViewById(R.id.page_5_link);
        searchPage = findViewById(R.id.page_6_link);

        suggestionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),
                        "Opening Browse Page", Toast.LENGTH_SHORT).show();
                Intent searchIntent = new Intent(MainActivity.this, SuggestionActivity.class);
                startActivity(searchIntent);
            }
        });


        searchPage.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 Toast.makeText(v.getContext(),
                         "Opening Search Page", Toast.LENGTH_SHORT).show();
                 Intent searchIntent = new Intent(MainActivity.this, SearchActivity.class);
                 startActivity(searchIntent);
             }
         });



    }
}
