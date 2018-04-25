package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.ArrayList;

/**
 * Created by Mariana Salas on 05/04/2018.
 */
public class AdapterMetas extends RecyclerView.Adapter<AdapterMetas.ViewHolder>{
    private ArrayList<itemMeta> mDataSet;
    private Context context;

    public AdapterMetas(Context context, ArrayList<itemMeta> myDataSet) {
        mDataSet = myDataSet;
        this.context = context;
    }


    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meta, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mProductTitle;
        public TextView mProductPercentage;
       // public ImageView mProductImage;
        CircularProgressBar mProgressBar;

        public ViewHolder(View v) {
            super(v);
            mProductTitle = (TextView) v.findViewById(R.id.item_meta_name);
            mProductPercentage = (TextView) v.findViewById(R.id.item_meta_percentage) ;
     //       mProductImage = (ImageView) v.findViewById(R.id.item_meta_image);
            mProgressBar = (CircularProgressBar)v.findViewById(R.id.item_meta_progressbar);
        }}

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getName());
        holder.mProductPercentage.setText(mDataSet.get(position).getPorcentaje());
        holder.mProgressBar.setProgress(mDataSet.get(position).getProgress());
        holder.mProgressBar.setColor(ContextCompat.getColor(getContext(), R.color.progressBarColor));
        holder.mProgressBar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundProgressBarColor));
        holder.mProgressBar.setProgressBarWidth(7);
        holder.mProgressBar.setBackgroundProgressBarWidth(7);
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
