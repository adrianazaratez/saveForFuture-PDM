package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
        public ProgressBar mProgressBar;

        public ViewHolder(View v) {
            super(v);
            mProductTitle = (TextView) v.findViewById(R.id.item_meta_name);
            mProductPercentage = (TextView) v.findViewById(R.id.item_meta_percentage) ;
     //       mProductImage = (ImageView) v.findViewById(R.id.item_meta_image);
            mProgressBar = (ProgressBar) v.findViewById(R.id.item_meta_progressbar);
        }}

    @Override
    public void onBindViewHolder( ViewHolder holder, int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getName());
        holder.mProductPercentage.setText(mDataSet.get(position).getPorcentaje());
        int i = 0;
        try {
           i =  Integer.parseInt(mDataSet.get(position).getPorcentaje());
        }catch(Exception e){
            System.out.println(e);
        }
        holder.mProgressBar.setProgress(i);

    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
