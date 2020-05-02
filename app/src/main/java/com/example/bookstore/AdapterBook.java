package com.example.bookstore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterBook extends RecyclerView.Adapter<AdapterBook.ViewHolder> implements View.OnClickListener{

    LayoutInflater inflater;
    ArrayList<Book> model;
    private View.OnClickListener listener;

    public AdapterBook(Context context, ArrayList<Book> model){
        this.inflater= LayoutInflater.from(context);
        this.model = model;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_book,parent,false);
        view.setOnClickListener(this);
        Picasso.get().setLoggingEnabled(true);
        return new ViewHolder(view);
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String title = model.get(position).getTitle();
        String author = model.get(position).getAuthor();
        String editorial = model.get(position).getEditorial();
        String price = model.get(position).getPrice();
        String url_picture = model.get(position).getURL();

        holder.title.setText(title);
        holder.author.setText(author);
        holder.editorial.setText(editorial);
        holder.price.setText(price);
        Picasso.get().load(url_picture).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View v) {
        if(this.listener!=null){
            listener.onClick(v);
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title, author, editorial, price;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.bookTitle);
            author = itemView.findViewById(R.id.bookAuthor);
            editorial = itemView.findViewById(R.id.bookEditorial);
            price = itemView.findViewById(R.id.bookPrice);
            image = itemView.findViewById(R.id.imageView);
        }
    }
}
