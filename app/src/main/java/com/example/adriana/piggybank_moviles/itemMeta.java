package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

public class itemMeta implements Parcelable {

    private String name, porcentaje;
    private int progress;

<<<<<<< HEAD
    public String getName() {
        return name;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public int getProgress() {
        return progress;
    }

    public itemMeta(String name, String porcentaje, int progress) {
=======
    public itemMeta(){

    }

    public itemMeta(String name, String porcentaje, Integer image) {
>>>>>>> 0bb9a4999a2ae181868e79db2b75f51e5862bde7
        this.name = name;
        this.porcentaje = porcentaje;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "itemMeta{" +
                "name='" + name + '\'' +
                ", porcentaje='" + porcentaje + '\'' +
                ", progress=" + progress +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.porcentaje);
        dest.writeInt(this.progress);
    }

    protected itemMeta(Parcel in) {
        this.name = in.readString();
        this.porcentaje = in.readString();
        this.progress = in.readInt();
    }

    public static final Parcelable.Creator<itemMeta> CREATOR = new Parcelable.Creator<itemMeta>() {
        @Override
        public itemMeta createFromParcel(Parcel source) {
            return new itemMeta(source);
        }

        @Override
        public itemMeta[] newArray(int size) {
            return new itemMeta[size];
        }
    };
}
