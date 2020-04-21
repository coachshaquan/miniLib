package com.example.minilib.actions;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.minilib.adapters.BookAdapter;
import com.example.minilib.classes.Book;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FireBaseActions {

//fireBase Code

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference booksDatabaseReference;
    public static ArrayList<Book> returnList = new ArrayList<>();


    /* FirebaseActions for when a BookAdapter is present in the Activity class */

    public FireBaseActions(final BookAdapter b){
        firebaseDatabase =FirebaseDatabase.getInstance();
        booksDatabaseReference =firebaseDatabase.getReference().child("books");

        ChildEventListener childEventListener =new ChildEventListener() {
            @Override
            public void onChildAdded (@NonNull DataSnapshot dataSnapshot, @Nullable String s){

                //       dataSnapshot is the data recently added to firestore
                //        the getValue method will serialize our dataSnapshot object into a Book
                //       object because our books collection on firestore has the same data
                        b.add(dataSnapshot.getValue(Book.class));
                        b.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged (@NonNull DataSnapshot dataSnapshot, @Nullable String s){
            }

            @Override
            public void onChildRemoved (@NonNull DataSnapshot dataSnapshot){
            }
            @Override
            public void onChildMoved (@NonNull DataSnapshot dataSnapshot, @Nullable String s){
            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
            }
        };

        //childEventListener is triggered when a new child is appeneded to the firebase database
        booksDatabaseReference.addChildEventListener(childEventListener);
    }

    public FireBaseActions(){
        firebaseDatabase =FirebaseDatabase.getInstance();
        booksDatabaseReference =firebaseDatabase.getReference().child("books");

        ChildEventListener childEventListener =new ChildEventListener() {
            @Override
            public void onChildAdded (@NonNull DataSnapshot dataSnapshot, @Nullable String s){

            }

            @Override
            public void onChildChanged (@NonNull DataSnapshot dataSnapshot, @Nullable String s){
            }

            @Override
            public void onChildRemoved (@NonNull DataSnapshot dataSnapshot){
            }
            @Override
            public void onChildMoved (@NonNull DataSnapshot dataSnapshot, @Nullable String s){
            }

            @Override
            public void onCancelled (@NonNull DatabaseError databaseError){
            }
        };

        //childEventListener is triggered when a new child is appeneded to the firebase database
        booksDatabaseReference.addChildEventListener(childEventListener);
    }

    public FirebaseDatabase getFirebaseDatabase() { return firebaseDatabase; }

    public DatabaseReference getBooksDatabaseReference() { return booksDatabaseReference; }

    public void setAdapter(ArrayList<Book> list, final BookAdapter b) {
        b.clear();
        b.addAll(list);
        b.notifyDataSetChanged();

    }
}