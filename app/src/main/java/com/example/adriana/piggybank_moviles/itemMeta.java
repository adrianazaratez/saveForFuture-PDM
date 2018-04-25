package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

public class itemMeta implements Parcelable {

    private String name, porcentaje;
    private Integer image;

    public itemMeta(){

    }

    public itemMeta(String name, String porcentaje, Integer image) {
        this.name = name;
        this.porcentaje = porcentaje;
        this.image = image;
    }

    @Override
    public String toString() {
        return "itemMeta{" +
                "name='" + name + '\'' +
                ", porcentaje='" + porcentaje + '\'' +
                ", image=" + image +
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

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.porcentaje);
        dest.writeValue(this.image);
    }

    protected itemMeta(Parcel in) {
        this.name = in.readString();
        this.porcentaje = in.readString();
        this.image = (Integer) in.readValue(Integer.class.getClassLoader());
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
