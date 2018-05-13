package com.example.adriana.piggybank_moviles;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;

public class itemMeta implements Parcelable {

    private String name;
    String id;
    double cantidad, ahorrado;
    String fechaLimite;

    public itemMeta(String name, String id, double cantidad, double ahorrado, String fechaLimite) {
        this.name = name;
        this.id = id;
        this.cantidad = cantidad;
        this.ahorrado = ahorrado;
        this.fechaLimite = fechaLimite;
    }

    @Override
    public String toString() {
        return "itemMeta{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", cantidad=" + cantidad +
                ", ahorrado=" + ahorrado +
                ", fechaLimite='" + fechaLimite + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getAhorrado() {
        return ahorrado;
    }

    public void setAhorrado(double ahorrado) {
        this.ahorrado = ahorrado;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.id);
        dest.writeDouble(this.cantidad);
        dest.writeDouble(this.ahorrado);
        dest.writeString(this.fechaLimite);
    }

    protected itemMeta(Parcel in) {
        this.name = in.readString();
        this.id = in.readString();
        this.cantidad = in.readDouble();
        this.ahorrado = in.readDouble();
        this.fechaLimite = in.readString();
    }

    public static final Creator<itemMeta> CREATOR = new Creator<itemMeta>() {
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