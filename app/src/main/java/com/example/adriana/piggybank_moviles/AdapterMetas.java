package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.text.DecimalFormat;
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
        RelativeLayout mMetaLayout;

        public ViewHolder(View v) {
            super(v);
            mProductTitle = (TextView) v.findViewById(R.id.item_meta_name);
            mProductPercentage = (TextView) v.findViewById(R.id.item_meta_percentage) ;
            //       mProductImage = (ImageView) v.findViewById(R.id.item_meta_image);
            mProgressBar = (CircularProgressBar)v.findViewById(R.id.item_meta_progressbar);
            mMetaLayout = (RelativeLayout) v.findViewById(R.id.item_meta_layout);
        }}

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        DecimalFormat df = new DecimalFormat("0.00");
        holder.mProductTitle.setText(mDataSet.get(position).getName());
        holder.mProductPercentage.setText("" + df.format((mDataSet.get(position).getAhorrado() / mDataSet.get(position).getCantidad()) * 100) + "%");
        holder.mProgressBar.setProgress((float) ((mDataSet.get(position).getAhorrado() / mDataSet.get(position).getCantidad()) * 100));
        holder.mProgressBar.setColor(ContextCompat.getColor(getContext(), R.color.progressBarColor));
        holder.mProgressBar.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.backgroundProgressBarColor));
        holder.mProgressBar.setProgressBarWidth(7);
        holder.mProgressBar.setBackgroundProgressBarWidth(7);
        holder.mMetaLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityMetaDetail.class);
              intent.putExtra("Detalle Meta", mDataSet.get(position));
              ((ActivityMetas) context).startActivityForResult(intent, 9999);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
