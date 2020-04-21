package com.example.minilib.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.minilib.DPPBookActivity;
import com.example.minilib.R;
import com.example.minilib.SearchActivity;
import com.example.minilib.classes.Book;
import java.util.ArrayList;

public class BookAdapter extends ArrayAdapter<Book> {

    public BookAdapter(Context context, ArrayList<Book> books){
        super(context, 0, books);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item_horizontalview, parent, false);
        }

        final Book currentBook = (Book) getItem(position);
        //title, author, isbn
        TextView bookTitle = (TextView) listItemView.findViewById(R.id.book_title);
        TextView bookAuthor = (TextView) listItemView.findViewById(R.id.book_author);
        TextView bookISBN = (TextView) listItemView.findViewById(R.id.book_isbn);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.book_image);
        Button buttonView = (Button) listItemView.findViewById(R.id.book_button_submit);

        bookTitle.setText(currentBook.getTitle());
        bookAuthor.setText(("by " + currentBook.getAuthor()));
        bookISBN.setText(("ISBN: " + currentBook.getISBN()));


        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(v.getContext(),
                        "Opening Dynamic Product Page", Toast.LENGTH_SHORT).show();
                Intent searchIntent = new Intent(v.getContext(), DPPBookActivity.class);
                searchIntent.putExtra("Book_ID", currentBook.getBookID());
                v.getContext().startActivity(searchIntent);
            }
        });

        Glide.with(listItemView.getContext()).load(currentBook.getImageUrl()).into(imageView);

        return listItemView;
    }
}
