package com.example.bookstore;

import android.app.AlertDialog;
import android.content.DialogInterface;
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


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnglishLiterature#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnglishLiterature extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    AdapterBook adapterBook;
    RecyclerView recyclerView;
    ArrayList<Book> book_list;
    DBHelper db;

    public EnglishLiterature() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnglishLiterature.
     */
    // TODO: Rename and change types and number of parameters
    public static EnglishLiterature newInstance(String param1, String param2) {
        EnglishLiterature fragment = new EnglishLiterature();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_english_literature, container, false);
        this.recyclerView = view.findViewById(R.id.recyclerView);
        this.book_list = new ArrayList<>();
        this.db = new DBHelper(view.getContext());
        loadData();
        showData();
        // Inflate the layout for this fragment
        return view;
    }

    public void loadData(){
        Cursor books = this.db.getBooksByCategory("english");
        books.moveToFirst();
        Book book = new Book(books.getInt(books.getColumnIndex("id_book")),
                books.getString(books.getColumnIndex("title")),
                books.getString(books.getColumnIndex("author")),
                books.getString(books.getColumnIndex("category")),
                books.getString(books.getColumnIndex("editorial")),
                books.getString(books.getColumnIndex("description")),
                books.getString(books.getColumnIndex("price")),
                books.getString(books.getColumnIndex("url_picture")));
                Log.e("url", books.getString(books.getColumnIndex("url_picture")));
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
                        Log.e("url", books.getString(books.getColumnIndex("url_picture")));
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
                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog alert = builder.create();
                alert.setTitle("Description");
                alert.show();
            }
        });
    }
}
