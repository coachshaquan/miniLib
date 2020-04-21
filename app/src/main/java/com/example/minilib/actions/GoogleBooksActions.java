package com.example.minilib.actions;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.minilib.DPPBookActivity;
import com.example.minilib.R;
import com.example.minilib.SearchActivity;
import com.example.minilib.adapters.BookAdapter;
import com.example.minilib.classes.Book;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GoogleBooksActions {

    public static ArrayList<Book> returnList = new ArrayList<>();
    public static Book returnBook;

    private Context context;
    private RequestQueue requestQueue;

    public GoogleBooksActions(Context context){
        this.context = context;
        requestQueue = Volley.newRequestQueue(context);
    }

    private void basicAPICall(String url){
        final JSONObject ret = new JSONObject();
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //TO DO:print error message
                    }
                });

        requestQueue.add(jsonObjectRequest);
    }

    public void searchByISBN(String url, final Context context, final int R_id){

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                             returnBook = GoogleBooksActions.this.searchByISBN(response);

                            TextView bookAuthor, bookTitle, bookISBN, bookDescription;
                            ImageView bookImage;



                            bookAuthor = (TextView) ((Activity)context).findViewById(R.id.book_dpp_author);
                            bookTitle = (TextView) ((Activity)context).findViewById(R.id.book_dpp_title);
                            bookISBN = (TextView) ((Activity)context).findViewById(R.id.book_dpp_isbn);
                            bookDescription = (TextView) ((Activity)context).findViewById(R.id.book_dpp_description);
                            bookImage = (ImageView) ((Activity)context).findViewById(R.id.book_dpp_image);



                            bookAuthor.setText(returnBook.getAuthor());
                            bookTitle.setText("by " + returnBook.getTitle());
                            bookISBN.setText("ISBN: " +  returnBook.getISBN());

                            //  bookDescription.setText(thisBook.getDescription());
                            Glide.with(context).load(returnBook.getImageUrl()).into(bookImage);


                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            //TO DO:print error message
                        }
                    });

            requestQueue.add(jsonObjectRequest);


    }

    public void searchByString(String url, final BookAdapter b){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,

                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        returnList = GoogleBooksActions.this.searchByString(response);
                        b.clear();
                        b.addAll(returnList);
                        b.notifyDataSetChanged();

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        //TO DO:print error message
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    /* Helper method for searchByString method which we will call later*/
    private ArrayList<Book> searchByString(JSONObject response){
                ArrayList<Book> newList = new ArrayList<>();
                try{
                        JSONArray itemsAsJSONArray = response.getJSONArray("items");
                        JSONObject bookNode; Book searchedBook;
                    for(int i=0; i<itemsAsJSONArray.length(); i++) {
                             bookNode = itemsAsJSONArray.getJSONObject(i);
                             searchedBook = createBook(bookNode);
                             newList.add(searchedBook);
                        }
                }catch(JSONException e){
                    //newList = null;
                }

            return newList;
    }

    /* Helper method for searchByISBN method which we will call later*/
    private Book searchByISBN(JSONObject response){
            return createBook(response);
    }

    private Book createBook(JSONObject node){
        Book returnBook;
        try {
            String bookID, author, title, datePublished, imageUrl, isbnAsString;
            bookID = node.getString("id");
            JSONObject bookMetadata = node.getJSONObject("volumeInfo");


            //Remove Punctuation except commas, numbers and spaces
            author = scraperHelper(bookMetadata, "authors", "");
            title = scraperHelper(bookMetadata,  "title", "");
            datePublished = scraperHelper(bookMetadata, "publishedDate", "");
            imageUrl = scraperHelper(bookMetadata.getJSONObject("imageLinks"), "thumbnail", "");

            try{
                isbnAsString = scraperHelper(
                        bookMetadata.getJSONArray("industryIdentifiers").getJSONObject(0),
                        "identifier", "");
            }catch(JSONException e){
                isbnAsString = "";
            }


            author = cleanAuthors(author);
            isbnAsString = cleanISBN(isbnAsString);

            returnBook = new Book( bookID, isbnAsString, author, title, datePublished, imageUrl, null);
        }catch(JSONException e){
            returnBook = null;
        }
        return returnBook;
    }

    private String cleanAuthors(String text){
        return text.replaceAll("[\\p{Punct}&&[^0-9]&&[^,]]", "");

    }

    private String cleanISBN(String text){
        if (!text.matches("\\d{10}$") && !text.matches("\\d{13}$")) {
           return "00000000000000";

        }
        else return text;
    }

    private String scraperHelper(JSONObject object, String try_string, String catch_string){
        try{
            return object.getString(try_string);
        }
        catch(JSONException e){
            return "Unavailable at this time";
        }
    }

    public Book getReturnBook(){
        return returnBook;
    }
}

