package com.example.bookstore;

public class Book {

    private int id;
    private String title, author, category, editorial, description, price, url_picture;

    public Book(int id, String title, String author, String category, String editorial,
                String description, String price, String url_picture){
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.editorial = editorial;
        this.description = description;
        this.price = price;
        this.url_picture = url_picture;
    }

    //GETTERS
    public int getId(){ return this.id; }
    public String getTitle(){ return this.title; }
    public String getAuthor(){ return this.author; }
    public String getCategory(){ return this.category; }
    public String getEditorial(){ return this.editorial; }
    public String getDescription(){ return this.description; }
    public String getPrice(){ return this.price; }
    public String getURL(){ return this.url_picture; }

}
