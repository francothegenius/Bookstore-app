package com.example.bookstore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, RequestBook.Callback {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    Toolbar toolbar;
    NavigationView navigationView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    DBHelper db;

    JSONArray json;
    List<Book> book_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.setDrawerSlideAnimationEnabled(true);
        actionBarDrawerToggle.syncState();

        this.db = new DBHelper(getApplicationContext());
        RequestBook.getRequest(this,this);
        book_list = new ArrayList<>();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.container, new MainFragment());
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);

        if(item.getItemId() == R.id.home){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new MainFragment());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.scienceFiction){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new ScieneFiction());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.sports){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Sports());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.englishLiterature){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new EnglishLiterature());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.artCulture){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new ArtCulture());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.comics){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Comics());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.selfHelp){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new SelfHelp());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.findus){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new FindUs());
            fragmentTransaction.commit();
        }
        if(item.getItemId() == R.id.contact){
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container, new Email());
            fragmentTransaction.commit();
        }

        return false;
    }

    @Override
    public void processJSON(String response) {
        try{
            json = new JSONArray(response);

            for(int i = 0; i<json.length();i++)
            {
                JSONArray jsonArray = json.getJSONArray(i);

                Book book = new Book(Integer.parseInt(jsonArray.getJSONObject(0).getString("id_book")),
                                    jsonArray.getJSONObject(0).getString("title"),
                                    jsonArray.getJSONObject(0).getString("author"),
                                    jsonArray.getJSONObject(0).getString("category"),
                                    jsonArray.getJSONObject(0).getString("editorial"),
                                    jsonArray.getJSONObject(0).getString("description"),
                                    jsonArray.getJSONObject(0).getString("price"),
                                    jsonArray.getJSONObject(0).getString("url_picture"));
                Log.e("prueba",book.getAuthor());
                book_list.add(book);
                db.insertBook(book);
            }
        }catch(JSONException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void onError() {

    }
}
