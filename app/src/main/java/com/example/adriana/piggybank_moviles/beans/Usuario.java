package com.example.adriana.piggybank_moviles.beans;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by adriana on 23/04/2018.
 */

public class Usuario implements Parcelable{
    public String id;
    public String nombre;
    public String correo;
    public String fechaNac;
    public String genero;
    public String nombreUsuario;
    public String contrasena;
    public Long salario;
    public HashMap<String,Boolean> categoria;
    public HashMap<String,Boolean> movimiento;
    public HashMap<String,Boolean> meta;

    public Usuario() {
    }

    public Usuario(String id, String nombre, String correo, String fechaNac, String genero, String nombreUsuario, String contrasena, Long salario, HashMap<String, Boolean> categoria, HashMap<String, Boolean> movimiento, HashMap<String, Boolean> meta) {
        this.id = id;
        this.nombre = nombre;
        this.correo = correo;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.salario = salario;
        this.categoria = categoria;
        this.movimiento = movimiento;
        this.meta = meta;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", fechaNac='" + fechaNac + '\'' +
                ", genero='" + genero + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", salario=" + salario +
                ", categoria=" + categoria +
                ", movimiento=" + movimiento +
                ", meta=" + meta +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public Long getSalario() {
        return salario;
    }

    public void setSalario(Long salario) {
        this.salario = salario;
    }

    public HashMap<String, Boolean> getCategoria() {
        return categoria;
    }

    public void setCategoria(HashMap<String, Boolean> categoria) {
        this.categoria = categoria;
    }

    public HashMap<String, Boolean> getMovimiento() {
        return movimiento;
    }

    public void setMovimiento(HashMap<String, Boolean> movimiento) {
        this.movimiento = movimiento;
    }

    public HashMap<String, Boolean> getMeta() {
        return meta;
    }

    public void setMeta(HashMap<String, Boolean> meta) {
        this.meta = meta;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.nombre);
        dest.writeString(this.correo);
        dest.writeString(this.fechaNac);
        dest.writeString(this.genero);
        dest.writeString(this.nombreUsuario);
        dest.writeString(this.contrasena);
        dest.writeValue(this.salario);
        dest.writeSerializable(this.categoria);
        dest.writeSerializable(this.movimiento);
        dest.writeSerializable(this.meta);
    }

    protected Usuario(Parcel in) {
        this.id = in.readString();
        this.nombre = in.readString();
        this.correo = in.readString();
        this.fechaNac = in.readString();
        this.genero = in.readString();
        this.nombreUsuario = in.readString();
        this.contrasena = in.readString();
        this.salario = (Long) in.readValue(Long.class.getClassLoader());
        this.categoria = (HashMap<String, Boolean>) in.readSerializable();
        this.movimiento = (HashMap<String, Boolean>) in.readSerializable();
        this.meta = (HashMap<String, Boolean>) in.readSerializable();
    }

    public static final Creator<Usuario> CREATOR = new Creator<Usuario>() {
        @Override
        public Usuario createFromParcel(Parcel source) {
            return new Usuario(source);
        }

        @Override
        public Usuario[] newArray(int size) {
            return new Usuario[size];
        }
    };
}
