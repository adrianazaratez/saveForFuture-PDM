package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Mariana Salas on 05/04/2018.
 */

public class itemGasto implements Parcelable {

    private String name;
    private Integer image;
    private String monto;
    private int progress;

    public itemGasto() {
    }

    public itemGasto(String name, Integer image, String monto, int progress) {
        this.name = name;
        this.image = image;
        this.monto = monto;
        this.progress = progress;
    }

    @Override
    public String toString() {
        return "itemGasto{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", monto='" + monto + '\'' +
                '}';
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

    public int getProgress() {
        return progress;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
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
        dest.writeValue(this.image);
        dest.writeString(this.monto);
        dest.writeInt(this.progress);
    }

    protected itemGasto(Parcel in) {
        this.name = in.readString();
        this.image = (Integer) in.readValue(Integer.class.getClassLoader());
        this.monto = in.readString();
        this.progress = in.readInt();
    }

    public static final Creator<itemGasto> CREATOR = new Creator<itemGasto>() {
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
