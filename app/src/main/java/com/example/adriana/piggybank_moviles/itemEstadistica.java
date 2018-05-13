package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

import com.github.mikephil.charting.charts.PieChart;

import java.util.Arrays;

/**
 * Created by adriana on 11/05/2018.
 */

public class itemEstadistica implements Parcelable {

    float rainfall[];
    String gastoType[];
    String title;
    int tipoChart;

    public itemEstadistica() {
    }

    public itemEstadistica(float[] rainfall, String[] gastoType, String title, int tipoChart) {
        this.rainfall = rainfall;
        this.gastoType = gastoType;
        this.title = title;
        this.tipoChart = tipoChart;
    }

    public float[] getRainfall() {
        return rainfall;
    }

    public void setRainfall(float[] rainfall) {
        this.rainfall = rainfall;
    }

    public String[] getGastoType() {
        return gastoType;
    }

    public void setGastoType(String[] gastoType) {
        this.gastoType = gastoType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTipoChart() {
        return tipoChart;
    }

    public void setTipoChart(int tipoChart) {
        this.tipoChart = tipoChart;
    }

    @Override
    public String toString() {
        return "itemEstadistica{" +
                "rainfall=" + Arrays.toString(rainfall) +
                ", gastoType=" + Arrays.toString(gastoType) +
                ", title='" + title + '\'' +
                ", tipoChart=" + tipoChart +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloatArray(this.rainfall);
        dest.writeStringArray(this.gastoType);
        dest.writeString(this.title);
        dest.writeInt(this.tipoChart);
    }

    protected itemEstadistica(Parcel in) {
        this.rainfall = in.createFloatArray();
        this.gastoType = in.createStringArray();
        this.title = in.readString();
        this.tipoChart = in.readInt();
    }

    public static final Creator<itemEstadistica> CREATOR = new Creator<itemEstadistica>() {
        @Override
        public itemEstadistica createFromParcel(Parcel source) {
            return new itemEstadistica(source);
        }

        @Override
        public itemEstadistica[] newArray(int size) {
            return new itemEstadistica[size];
        }
    };
}
