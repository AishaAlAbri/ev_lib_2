package com.example.user.ev_lib_2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by Mk Computer on 09/03/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    ArrayList<BookData> mDataset;


    public MyAdapter(ArrayList<BookData> myDataset) {
        mDataset = myDataset;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kids_card_view, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

       // holder.mTextView.setText(String.valueOf(mDataset.get(position)));
        holder.bookName.setText((CharSequence) mDataset.get(position).getName());
       // viewHolder.currentItem = items.get(i);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
        //return 1;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookName;
        public ViewHolder(View v) {
            super(v);
            bookName = (TextView)v.findViewById(R.id.book_name);
            v.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    getAdapterPosition();
                    Log.d("adaapter", String.valueOf(getAdapterPosition()));
                    Intent i=new Intent(v.getContext(),BookDetails.class );
                    //move to details activity with some information of selected card
                    v.getContext().startActivity(i);
                }
            });
        }
    }

}

