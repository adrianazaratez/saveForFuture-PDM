package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterGastos extends RecyclerView.Adapter<AdapterGastos.ViewHolder>{
    private ArrayList<itemGasto> mDataSet = null;
    private Context context;

    public AdapterGastos(Context context, ArrayList<itemGasto> myDataSet) {
        mDataSet = myDataSet;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_gasto, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mProductTitle;
        public ImageView mProductImage;
        public RelativeLayout mEventLayout;
        public TextView mMonto;
        public ProgressBar mProgress;

        public ViewHolder(View v) {
            super(v);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_gasto_layout);
            mProductTitle = (TextView) v.findViewById(R.id.item_gasto_name);
            mProductImage = (ImageView) v.findViewById(R.id.item_gasto_image);
            mMonto = (TextView) v.findViewById(R.id.item_gasto_progressBar);
            mProgress = (ProgressBar) v.findViewById(R.id.progressBar2);
        }}
    @Override
    public void onBindViewHolder(AdapterGastos.ViewHolder holder, int position) {
        holder.mProductTitle.setText(mDataSet.get(position).getName());
        holder.mMonto.setText("$" + mDataSet.get(position).getMonto());
        holder.mProgress.setProgress(mDataSet.get(position).getProgress());
        switch(mDataSet.get(position).getImage()){
            case 0:
                holder.mProductImage.setImageResource(R.drawable.comida); break;
            case 1:
                holder.mProductImage.setImageResource(R.drawable.transporte); break;
            case 2:
                holder.mProductImage.setImageResource(R.drawable.ropa);break;
            case 3:
                holder.mProductImage.setImageResource(R.drawable.casa); break;
            case 4:
                holder.mProductImage.setImageResource(R.drawable.entretenimiento); break;
            case 5:
                holder.mProductImage.setImageResource(R.drawable.salud); break;
            case 6:
                holder.mProductImage.setImageResource(R.drawable.cosmeticos); break;
            case 7:
                holder.mProductImage.setImageResource(R.drawable.viajes); break;
            case 8:
                holder.mProductImage.setImageResource(R.drawable.otros); break;
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }
}
