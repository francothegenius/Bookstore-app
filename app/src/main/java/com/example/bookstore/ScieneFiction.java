package com.example.bookstore;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class ScieneFiction extends Fragment {

    AdapterBook adapterBook;
    RecyclerView recyclerView;
    ArrayList<Book> book_list;
    DBHelper db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sciene_fiction, container, false);
        this.recyclerView = view.findViewById(R.id.recyclerView);
        this.book_list = new ArrayList<>();
        this.db = new DBHelper(view.getContext());
        loadData();
        showData();
        // Inflate the layout for this fragment
        return view;
    }

    public void loadData(){
        Cursor books = this.db.getBooksByCategory("scifi");
        books.moveToFirst();
        Book book = new Book(books.getInt(books.getColumnIndex("id_book")),
                books.getString(books.getColumnIndex("title")),
                books.getString(books.getColumnIndex("author")),
                books.getString(books.getColumnIndex("category")),
                books.getString(books.getColumnIndex("editorial")),
                books.getString(books.getColumnIndex("description")),
                books.getString(books.getColumnIndex("price")),
                books.getString(books.getColumnIndex("url_picture")));
        book_list.add(book);
        while(true){
            if(books.moveToNext()){
                book = new Book(books.getInt(books.getColumnIndex("id_book")),
                        books.getString(books.getColumnIndex("title")),
                        books.getString(books.getColumnIndex("author")),
                        books.getString(books.getColumnIndex("category")),
                        books.getString(books.getColumnIndex("editorial")),
                        books.getString(books.getColumnIndex("description")),
                        books.getString(books.getColumnIndex("price")),
                        books.getString(books.getColumnIndex("url_picture")));
                book_list.add(book);
            }else{
                break;
            }
        }

    }

    public void showData(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        this.adapterBook = new AdapterBook(getContext(),this.book_list);
        recyclerView.setAdapter(this.adapterBook);

        adapterBook.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage(book_list.get(recyclerView.getChildAdapterPosition(v)).getDescription());
                AlertDialog alert = builder.create();
                alert.setTitle("Description");
                alert.show();
            }
        });
    }
}
