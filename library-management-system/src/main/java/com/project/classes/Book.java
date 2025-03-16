package com.project.classes;

public class Book {
    private int bookId;
    private String bookTitle;
    private String bookAuthor;
    private String bookGenre;
    private int availableCopies;

    public Book() {
    }


    public Book(int bookId, String bookTitle, String bookAuthor, String bookGenre, int availableCopies) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookGenre = bookGenre;
        this.availableCopies = availableCopies;
    }


    public int getBookId() {
        return bookId;
    }


    public void setBookId(int bookId) {
        this.bookId = bookId;
    }


    public String getBookTitle() {
        return bookTitle;
    }


    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }


    public String getBookAuthor() {
        return bookAuthor;
    }


    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }


    public String getBookGenre() {
        return bookGenre;
    }


    public void setBookGenre(String bookGenre) {
        this.bookGenre = bookGenre;
    }


    public int getAvailableCopies() {
        return availableCopies;
    }


    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }




}
