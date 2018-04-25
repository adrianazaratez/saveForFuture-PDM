package com.example.adriana.piggybank_moviles.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adriana on 24/04/2018.
 */

public class Movimiento implements Parcelable {
    String id;
    String tipo;
    String fecha;
    Long monto;
    HashMap<String,Boolean> categoria;

    public Movimiento() {
    }

    public Movimiento(String id, String tipo, String fecha, Long monto, HashMap<String, Boolean> categoria) {
        this.id = id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.monto = monto;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Movimiento{" +
                "id='" + id + '\'' +
                ", tipo='" + tipo + '\'' +
                ", fecha='" + fecha + '\'' +
                ", monto=" + monto +
                ", categoria=" + categoria +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Long getMonto() {
        return monto;
    }

    public void setMonto(Long monto) {
        this.monto = monto;
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
        dest.writeString(this.tipo);
        dest.writeString(this.fecha);
        dest.writeValue(this.monto);
        dest.writeSerializable(this.categoria);
    }

    protected Movimiento(Parcel in) {
        this.id = in.readString();
        this.tipo = in.readString();
        this.fecha = in.readString();
        this.monto = (Long) in.readValue(Long.class.getClassLoader());
        this.categoria = (HashMap<String, Boolean>) in.readSerializable();
    }

    public static final Creator<Movimiento> CREATOR = new Creator<Movimiento>() {
        @Override
        public Movimiento createFromParcel(Parcel source) {
            return new Movimiento(source);
        }

        @Override
        public Movimiento[] newArray(int size) {
            return new Movimiento[size];
        }
    };
}
