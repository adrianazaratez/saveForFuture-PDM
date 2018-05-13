package com.example.adriana.piggybank_moviles;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adriana on 11/05/2018.
 */

public class AdapterEstadistica extends RecyclerView.Adapter<AdapterEstadistica.ViewHolder> {
    private ArrayList<itemEstadistica> mDataSet = null;
    private Context context;

    public AdapterEstadistica(Context context, ArrayList<itemEstadistica> myDataSet) {
        mDataSet = myDataSet;
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    @Override
    public AdapterEstadistica.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_estadistica, parent, false);
        AdapterEstadistica.ViewHolder vh = new AdapterEstadistica.ViewHolder(v);
        return vh;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public PieChart mChart;
        public RelativeLayout mEventLayout;
        public TextView mTitle;


        public ViewHolder(View v) {
            super(v);
            mEventLayout = (RelativeLayout) v.findViewById(R.id.item_estadistica_layout);
            mChart = v.findViewById(R.id.activity_estadisticas_pie_chart);
            mTitle = v.findViewById(R.id.chart_title);
        }
    }
    @Override
    public void onBindViewHolder(AdapterEstadistica.ViewHolder holder, int position) {
        String gastoType[] = mDataSet.get(position).getGastoType();
        float monto[] = mDataSet.get(position).getRainfall();

        holder.mTitle.setText(mDataSet.get(position).getTitle().toString());
        List<PieEntry> entries = new ArrayList<>();
        entries.clear();
        for (int i = 0; i < monto.length; i++){
            entries.add(new PieEntry(monto[i],gastoType[i]));
        }

        PieDataSet dataSet = new PieDataSet(entries," ");
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        PieData data = new PieData(dataSet);

        PieChart chart = (PieChart)holder.mChart;
        chart.setData(data);

        switch (mDataSet.get(position).getTipoChart()){
            case 1:
                chart.setUsePercentValues(true);
                break;
            case 2:
                chart.setUsePercentValues(false);
                break;
            default:
                chart.setUsePercentValues(false);
                break;
        }
        chart.animateY(2000);
        chart.invalidate();
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
