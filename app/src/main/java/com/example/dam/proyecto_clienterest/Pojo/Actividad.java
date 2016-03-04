package com.example.dam.proyecto_clienterest.Pojo;

import android.os.Parcel;
import android.os.Parcelable;


public class Actividad implements Parcelable{
    private String id, idprofesor;
    private String tipo, lugari, lugarf, fechai, fechaf, alumno, descripcion;

    public Actividad() {
        this("","","","","","","","","");
    }

    public Actividad(String id, String profesor, String tipo, String lugari, String lugarf, String fechai, String fechaf, String alumno, String descripcion) {
        this.id = id;
        this.idprofesor = profesor;
        this.tipo = tipo;
        this.lugari = lugari;
        this.lugarf = lugarf;
        this.fechai = fechai;
        this.fechaf = fechaf;
        this.alumno = alumno;
        this.descripcion=descripcion;
    }

    protected Actividad(Parcel in) {
        id = in.readString();
        idprofesor = in.readString();
        tipo = in.readString();
        lugari = in.readString();
        lugarf = in.readString();
        fechai = in.readString();
        fechaf = in.readString();
        alumno = in.readString();
        descripcion = in.readString();
    }

    public static final Creator<Actividad> CREATOR = new Creator<Actividad>() {
        @Override
        public Actividad createFromParcel(Parcel in) {
            return new Actividad(in);
        }

        @Override
        public Actividad[] newArray(int size) {
            return new Actividad[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdprofesor() {
        return idprofesor;
    }

    public void setIdprofesor(String profesor) {
        this.idprofesor = profesor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getLugari() {
        return lugari;
    }

    public void setLugari(String lugari) {
        this.lugari = lugari;
    }

    public String getLugarf() {
        return lugarf;
    }

    public void setLugarf(String lugarf) {
        this.lugarf = lugarf;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
    }

    public String getFechai() {
        return fechai;
    }

    public void setFechai(String fechai) {
        this.fechai = fechai;
    }

    public String getFechaf() {
        return fechaf;
    }

    public void setFechaf(String fechaf) {
        this.fechaf = fechaf;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "id=" + id +
                ", profesor=" + idprofesor +
                ", tipo='" + tipo + '\'' +
                ", lugari='" + lugari + '\'' +
                ", lugarf='" + lugarf + '\'' +
                ", alimno='" + alumno + '\'' +
                ", fechai='" + fechai + '\'' +
                ", fechaf='" + fechaf + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(idprofesor);
        dest.writeString(tipo);
        dest.writeString(lugari);
        dest.writeString(lugarf);
        dest.writeString(fechai);
        dest.writeString(fechaf);
        dest.writeString(alumno);
        dest.writeString(descripcion);
    }
}
