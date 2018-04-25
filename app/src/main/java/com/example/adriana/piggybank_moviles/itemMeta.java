package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

public class itemMeta implements Parcelable {

    private String name, porcentaje;

    public itemMeta(String name, String porcentaje) {
        this.name = name;
        this.porcentaje = porcentaje;

    }

    @Override
    public String toString() {
        return "itemMeta{" +
                "name='" + name + '\'' +
                ", porcentaje='" + porcentaje + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.porcentaje);
    }

    protected itemMeta(Parcel in) {
        this.name = in.readString();
        this.porcentaje = in.readString();

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
