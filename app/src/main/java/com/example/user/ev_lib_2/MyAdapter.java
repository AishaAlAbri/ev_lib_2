package com.example.user.ev_lib_2;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    ImageLoader imageLoader;


    public MyAdapter(ArrayList<BookData> myDataset) {
        mDataset = myDataset;
    }


    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.kids_card_view, parent, false);
                imageLoader=new ImageLoader(parent.getContext());


        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

       // holder.mTextView.setText(String.valueOf(mDataset.get(position)));
        holder.bookName.setText((CharSequence) mDataset.get(position).getName());
        int price=mDataset.get(position).getPrice();
        holder.price_txv_list.setText(Integer.toString(price));

        String image=mDataset.get(position).getImage();
        ImageView view=holder.cardimage;
        imageLoader.DisplayImage(image, view);

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView bookName,price_txv_list;
        public ImageView cardimage;
        public ViewHolder(View v) {
            super(v);
            bookName = (TextView)v.findViewById(R.id.book_name);
            price_txv_list = (TextView)v.findViewById(R.id.price_txv_list);
            cardimage = (ImageView)v.findViewById(R.id.cardimage);
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

