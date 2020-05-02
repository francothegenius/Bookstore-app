package com.example.bookstore;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASENAME = "books.db";

    public static final int VERSION = 10;

    public DBHelper(Context context) {
        super(context, DATABASENAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE books ( " +
                "id_book int NOT NULL," +
                "title VARCHAR(50) NOT NULL," +
                "author VARCHAR(50) NOT NULL," +
                "category VARCHAR(70) NOT NULL," +
                "editorial VARCHAR(50) NOT NULL," +
                "description VARCHAR(100) NOT NULL," +
                "price VARCHAR(20) NOT NULL," +
                "url_picture VARCHAR(200) NOT NULL," +
                "PRIMARY KEY(id_book))"
        );
    }

    public void insertBook(Book book){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("id_book", book.getId());
        values.put("title", book.getTitle());
        values.put("author", book.getAuthor());
        values.put("category", book.getCategory());
        values.put("editorial", book.getEditorial());
        values.put("description", book.getDescription());
        values.put("price", book.getPrice());
        values.put("url_picture", book.getURL());

        db.insert("books", null, values);
    }

    public Cursor getBooksByCategory(String category){
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.rawQuery("SELECT * FROM books WHERE category=" + category,null);
        String[] selectionArgs = {category};
        Cursor cursor = db.query(
                "books",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                "category=?",              // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null              // The sort order
        );
        cursor.moveToFirst();
        return cursor;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
