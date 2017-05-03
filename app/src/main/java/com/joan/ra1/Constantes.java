package com.joan.ra1;

/**
 * Created by Joan on 16/12/2016.
 */

public class Constantes {
    public static final String NOMBRE_DB = "mascotas.db";
    public static final int VERSION=1;
    public static final String TABLA_USUARIOS = "usuarios";
    public static final String TABLA_PAISES = "paises";
    public static final String[] SELECT_USUARIO= {"usuario"};
    public static final String[] SELECT_CONTRASENA = {"contrasena"};
    public static final String ORDER_BY = "usuario ASC";
    public static final String TABLA_MASCOTAS = "mascotas";
    public static final String[] SELECT_MASCOTAS = {"apodo", "mascota", "tamano", "amistoso", "imagen"};
    public static final String ORDER_BY_APODO = "apodo ASC";
    public static final String[] SELECT_MASCOTA = {"apodo", "mascota"};
    public static final String[] SELECT_USUARIOS = {"usuario" , "contrasena", "email", "pais", "ciudad"};
    public static final String[] SELECT_IDUSUARIOS = {"id"};
    public static String USUARIO = "";
}
