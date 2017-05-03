package com.joan.ra1;

import android.graphics.Bitmap;

/**
 * Created by Joan on 16/12/2016.
 */

public class Mascotas {
    String apodo;
    String mascota;
    String tamano;
    int amistoso;
    Bitmap imagen;

    public String getApodo() {
        return apodo;
    }

    public void setApodo(String apodo) {
        this.apodo = apodo;
    }

    public String getMascota() {
        return mascota;
    }

    public void setMascota(String mascota) {
        this.mascota = mascota;
    }

    public String getTamano() {
        return tamano;
    }

    public void setTamano(String tamano) {
        this.tamano = tamano;
    }

    public int getAmistoso() {
        return amistoso;
    }

    public void setAmistoso(int amistoso) {
        this.amistoso = amistoso;
    }

    public Bitmap getImagen() {
        return imagen;
    }

    public void setImagen(Bitmap imagen) {
        this.imagen = imagen;
    }
}
