package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mariana Salas on 05/04/2018.
 */

public class itemGasto implements Parcelable {

    private String name;
    private Integer image;

    public itemGasto() {
        this.name = "";
        this.image = 0;

    }

    @Override
    public String toString() {
        return "itemGasto{" +
                "name='" + name + '\'' +
                ", image=" + image +
                '}';
    }

    public itemGasto(String name, Integer image) {
        this.name = name;
        this.image = image;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        dest.writeValue(this.image);

    }

    protected itemGasto(Parcel in) {
        this.name = in.readString();
        this.image = (Integer) in.readValue(Integer.class.getClassLoader());

    }

    public static final Parcelable.Creator<itemGasto> CREATOR = new Parcelable.Creator<itemGasto>() {
        @Override
        public itemGasto createFromParcel(Parcel source) {
            return new itemGasto(source);
        }

        @Override
        public itemGasto[] newArray(int size) {
            return new itemGasto[size];
        }
    };
}
