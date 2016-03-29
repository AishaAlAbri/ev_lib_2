package com.example.user.ev_lib_2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private RecyclerView allBooksRV,mostViewedRV;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<BookData> allBooksList,mostBooksList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        allBooksRV = (RecyclerView) findViewById(R.id.allBooksRV);
        mostViewedRV = (RecyclerView) findViewById(R.id.mostViewedRV);
        allBooksRV.setHasFixedSize(true);

        allBooksRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));

        allBooksList = new ArrayList<>();



        AsyncHttpClient client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        params.put("Query", "select * from books");
        client.post("http://104.155.91.222:8080/getAllBooks", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Log.d("Error: ", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("Success", responseString);
                String jsonStr = responseString;
                if (jsonStr != null) {
                    try {

                        JSONArray jsonArray = new JSONArray(jsonStr);
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {


                                jsonArray = new JSONArray(jsonStr);
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                BookData bookData = new BookData();
                                bookData.setId(jsonObject.getInt("book_id"));
                                bookData.setName(jsonObject.getString("book_name"));
                                bookData.setDescription(jsonObject.getString("description"));
                                bookData.setPrice(jsonObject.getInt("price"));
                                bookData.setImage(jsonObject.getString("cover_picture"));

                                allBooksList.add(bookData);
                                mAdapter = new AllBooksAdapter(allBooksList);
                                allBooksRV.setAdapter(mAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });

        mostViewedRV.setLayoutManager(new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false));
        mostBooksList = new ArrayList<>();

        AsyncHttpClient clientMost = new AsyncHttpClient();
        RequestParams paramsMost = new RequestParams();
        params.put("Query", "select * from books");
        clientMost.post("http://104.155.91.222:8080/getBooksByMostViewed", paramsMost, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Log.d("Error: ", responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d("Success", responseString);
                String jsonStr = responseString;
                if (jsonStr != null) {
                    try {

                        JSONArray jsonArray = new JSONArray(jsonStr);
                        try {
                            for (int i = 0; i < jsonArray.length(); i++) {


                                jsonArray = new JSONArray(jsonStr);
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                BookData bookData = new BookData();
                                bookData.setId(jsonObject.getInt("book_id"));
                                bookData.setName(jsonObject.getString("book_name"));
                                bookData.setDescription(jsonObject.getString("description"));
                                bookData.setPrice(jsonObject.getInt("price"));
                                bookData.setImage(jsonObject.getString("cover_picture"));

                                mostBooksList.add(bookData);
                                mAdapter = new AllBooksAdapter(mostBooksList);
                                mostViewedRV.setAdapter(mAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });



    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_signin) {
            Intent i=new Intent(MainActivity.this,Login.class);
            startActivity(i);
            // Handle the camera action
        } else if (id == R.id.nav_myBooks) {

            Intent i=new Intent(MainActivity.this,Mybooks.class);
            startActivity(i);

        } else if (id == R.id.nav_Adults) {

            Intent i=new Intent(MainActivity.this,Adult.class);
            startActivity(i);
        } else if (id == R.id.nav_Kids) {

            Intent i=new Intent(MainActivity.this,Kid.class);
            startActivity(i);
        } else if (id == R.id.nav_about_us) {

            Intent i=new Intent(MainActivity.this,AboutUs.class);
            startActivity(i);
        } else if (id == R.id.nav_contact_us) {

            Intent i=new Intent(MainActivity.this,ContactUS.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
