package com.example.user.ev_lib_2;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Mk Computer on 09/03/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private String[] mDataset;



    public MyAdapter(String[] myDataset) {
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
        holder.mTextView.setText(mDataset[position]);
       // viewHolder.currentItem = items.get(i);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTextView;
        public ViewHolder(View v) {
            super(v);
            mTextView = (TextView)v.findViewById(R.id.info_text);
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

