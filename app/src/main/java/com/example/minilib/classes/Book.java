package com.example.minilib.classes;

public class Book{

    /*      Class variables for Book objects.    */
    private String bookID, ISBN, author, title, datePublished, imageUrl, borrowerKey;

    /*      Constructors for Book objects.    */
    public Book(){}

    public Book( String bookID, String ISBN, String author, String title, String datePublished, String imageUrl, String borrowerKey) {
        this.bookID = bookID;
        this.ISBN = ISBN;
        this.author = author;
        this.title = title;
        this.datePublished = datePublished;
        this.imageUrl = imageUrl;
        this.borrowerKey = borrowerKey;
    }



    /*      Getter methods for Book objects.    */
    public String getBookID() { return this.bookID; }

    public String getISBN() {
        return this.ISBN;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDatePublished() {
        return this.datePublished;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getBorrowerKey() {
        return this.borrowerKey;
    }


}


