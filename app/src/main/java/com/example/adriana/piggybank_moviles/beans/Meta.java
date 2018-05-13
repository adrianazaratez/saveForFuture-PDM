package com.example.adriana.piggybank_moviles.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adriana on 24/04/2018.
 */

public class Meta implements Parcelable {
    String id;
    double cantidad, ahorrado;
    String fechaLimite;
    String nombre;
    HashMap<String,Boolean> categoria;

    public Meta(){

    }

    public Meta(String id, double cantidad, double ahorrado, String fechaLimite, String nombre, HashMap<String, Boolean> categoria) {
        this.id = id;
        this.cantidad = cantidad;
        this.ahorrado = ahorrado;
        this.fechaLimite = fechaLimite;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "id='" + id + '\'' +
                ", cantidad=" + cantidad +
                ", ahorrado=" + ahorrado +
                ", fechaLimite='" + fechaLimite + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoria=" + categoria +
                '}';
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public HashMap<String, Boolean> getCategoria() {
        return categoria;
    }

    public void setCategoria(HashMap<String, Boolean> categoria) {
        this.categoria = categoria;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeDouble(this.cantidad);
        dest.writeDouble(this.ahorrado);
        dest.writeString(this.fechaLimite);
        dest.writeString(this.nombre);
        dest.writeSerializable(this.categoria);
    }

    protected Meta(Parcel in) {
        this.id = in.readString();
        this.cantidad = in.readDouble();
        this.ahorrado = in.readDouble();
        this.fechaLimite = in.readString();
        this.nombre = in.readString();
        this.categoria = (HashMap<String, Boolean>) in.readSerializable();
    }

    public static final Creator<Meta> CREATOR = new Creator<Meta>() {
        @Override
        public Meta createFromParcel(Parcel source) {
            return new Meta(source);
        }

        @Override
        public Meta[] newArray(int size) {
            return new Meta[size];
        }
    };
}