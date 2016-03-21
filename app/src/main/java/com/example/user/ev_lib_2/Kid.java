package com.example.user.ev_lib_2;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;


public class Kid extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<BookData> booksList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_kids, container, false);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        booksList = new ArrayList<>();


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

                                booksList.add(bookData);
                                Log.d("Success", responseString);
                                mAdapter = new MyAdapter(booksList);
                                mRecyclerView.setAdapter(mAdapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    } catch (Exception e){ e.printStackTrace();}
                }


            }
        });

     return rootView;
            }

}
