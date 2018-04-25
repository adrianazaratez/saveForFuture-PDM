package com.example.adriana.piggybank_moviles.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by adriana on 24/04/2018.
 */

public class Meta implements Parcelable {
    String id;
    Long cantidad;
    String fechaLimite;
    String nombre;
    ArrayList<Categoria> categoria;

    public Meta() {
    }

    public Meta(String id, Long cantidad, String fechaLimite, String nombre, ArrayList<Categoria> categoria) {
        this.id = id;
        this.cantidad = cantidad;
        this.fechaLimite = fechaLimite;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Meta{" +
                "id='" + id + '\'' +
                ", cantidad='" + cantidad + '\'' +
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

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
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

    public ArrayList<Categoria> getCategoria() {
        return categoria;
    }

    public void setCategoria(ArrayList<Categoria> categoria) {
        this.categoria = categoria;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeLong(this.cantidad);
        dest.writeString(this.fechaLimite);
        dest.writeString(this.nombre);
        dest.writeTypedList(this.categoria);
    }

    protected Meta(Parcel in) {
        this.id = in.readString();
        this.cantidad = in.readLong();
        this.fechaLimite = in.readString();
        this.nombre = in.readString();
        this.categoria = in.createTypedArrayList(Categoria.CREATOR);
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