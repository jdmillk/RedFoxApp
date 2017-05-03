package com.joan.ra1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by Joan on 15/12/2016.
 */

public class DataBase extends SQLiteOpenHelper{

    public DataBase(Context context) {
        super(context, Constantes.NOMBRE_DB, null, Constantes.VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Constantes.TABLA_PAISES +
                "(type VARCHAR(20)," +
                "seq INTEGER );" +
                "INSERT INTO paises(type,seq) VALUES ('Espana',1);" +
                "INSERT INTO paises(type,seq) VALUES ('Inglaterra',2);");

        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Constantes.TABLA_USUARIOS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "usuario VARCHAR(20)," +
                "contrasena VARCHAR(20)," +
                "email VARCHAR(40)," +
                "pais  VARCHAR(20) DEFAULT('ESPANA') REFERENCES paises(type)," +
                "ciudad VARCHAR(40) );");



        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + Constantes.TABLA_MASCOTAS +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "apodo VARCHAR(20)," +
                "mascota VARCHAR(40)," +
                "tamano VARCHAR(20)," +
                "amistoso INTEGER," +
                "imagen BLOB," +
                "id_usuario INTEGER," +
                "FOREIGN KEY (id_usuario) REFERENCES usuarios (id) );");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void registrarUsuario(Users u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", u.getUsuario());
        values.put("contrasena", u.getContrasena());
        values.put("email", u.getEmail());
        values.put("pais", u.getPais());
        values.put("ciudad", u.getCiudad());
        System.out.println("hola");
        db.insertOrThrow(Constantes.TABLA_USUARIOS, null, values);
        db.close();
    }

    public ArrayList<String> usuario(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constantes.TABLA_USUARIOS, Constantes.SELECT_USUARIO, null, null, null, null, Constantes.ORDER_BY);

        ArrayList<String> vUsuarios = new ArrayList<>();
        while(cursor.moveToNext()){
            vUsuarios.add(cursor.getString(0));
        }
        return vUsuarios;
    }
    public ArrayList<String> contrasena(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(Constantes.TABLA_USUARIOS, Constantes.SELECT_CONTRASENA, null, null, null, null, Constantes.ORDER_BY);

        ArrayList<String> vContrasena = new ArrayList<>();
        while(cursor.moveToNext()){
            vContrasena.add(cursor.getString(0));
        }
        return vContrasena;
    }


    public void registrarMascota(Mascotas m, String usuario) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("apodo", m.getApodo());
        values.put("mascota", m.getMascota());
        values.put("Tamano", m.getTamano());
        values.put("amistoso", m.getAmistoso());
        values.put("imagen", Util.getBytes(m.getImagen()));
        values.put("id_usuario", obteneridUsuario(usuario));
        db.insertOrThrow(Constantes.TABLA_MASCOTAS, null, values);
        db.close();
    }

    public ArrayList<Mascotas> obtenerMascotas(){
        SQLiteDatabase db = getReadableDatabase();
        String argumentos[] = new String[]{String.valueOf(obteneridUsuario(Constantes.USUARIO))};
        Cursor cursor = db.query(Constantes.TABLA_MASCOTAS, Constantes.SELECT_MASCOTAS, "id_usuario = ?", argumentos, null, null, Constantes.ORDER_BY_APODO);
        ArrayList<Mascotas> vMascotas = new ArrayList<>();
        while(cursor.moveToNext()){
            Mascotas m = new Mascotas();
            m.setApodo(cursor.getString(0));
            m.setMascota(cursor.getString(1));
            m.setTamano(cursor.getString(2));
            m.setAmistoso(cursor.getInt(3));
            m.setImagen(Util.getBitmap(cursor.getBlob(4)));
            vMascotas.add(m);
        }
        db.close();
        return vMascotas;
    }

    public ArrayList<Mascotas> obtenerMascota(){
        SQLiteDatabase db = getReadableDatabase();
        String argumentos[] = new String[]{String.valueOf(obteneridUsuario(Constantes.USUARIO))};
        Cursor cursor = db.query(Constantes.TABLA_MASCOTAS, Constantes.SELECT_MASCOTA, "id_usuario = ?", argumentos, null, null, Constantes.ORDER_BY_APODO);
        ArrayList<Mascotas> vMascotas = new ArrayList<>();
        while(cursor.moveToNext()){
            Mascotas m = new Mascotas();
            m.setApodo(cursor.getString(0));
            m.setMascota(cursor.getString(1));
            vMascotas.add(m);
        }
        db.close();
        return vMascotas;
    }

    public ArrayList<Users> obtenerUsuarios(){
        SQLiteDatabase db = getReadableDatabase();
        String argumentos[] = new String[]{String.valueOf(obteneridUsuario(Constantes.USUARIO))};
        Cursor cursor = db.query(Constantes.TABLA_USUARIOS, Constantes.SELECT_USUARIOS, "id = ?", argumentos, null, null, Constantes.ORDER_BY);

        ArrayList<Users> vUsuarios = new ArrayList<>();
        while(cursor.moveToNext()){
            Users u = new Users();
            u.setUsuario(cursor.getString(0));
            u.setContrasena(cursor.getString(1));
            u.setEmail(cursor.getString(2));
            u.setPais(cursor.getString(3));
            u.setCiudad(cursor.getString(4));
            vUsuarios.add(u);
        }
        return vUsuarios;
    }

    public void modificarUsuario(Users u) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("usuario", u.getUsuario());
        values.put("contrasena", u.getContrasena());
        values.put("email", u.getEmail());
        values.put("pais", u.getPais());
        values.put("ciudad", u.getCiudad());
        db.update(Constantes.TABLA_USUARIOS,values,null,null);
        db.close();
    }

    public void modificarMascota(Mascotas m, String usuario, String apodo) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("apodo", m.getApodo());
        values.put("mascota", m.getMascota());
        values.put("Tamano", m.getTamano());
        values.put("amistoso", m.getAmistoso());
        values.put("imagen", Util.getBytes(m.getImagen()));
        String argumentos[] = new String[]{apodo, String.valueOf(obteneridUsuario(usuario))};
        db.update(Constantes.TABLA_MASCOTAS,values,"apodo = ? and id_usuario = ?",argumentos);
        db.close();
    }

    public int obteneridUsuario(String usuario){
        SQLiteDatabase db = getReadableDatabase();
        int id = -1;
        Cursor cursor = db.query(Constantes.TABLA_USUARIOS, Constantes.SELECT_IDUSUARIOS, "usuario = ?", new String[]{usuario}, null, null, null);
        while(cursor.moveToNext()){
            id =cursor.getInt(0);
        }
        return id;
    }

    public void eliminarMascota(String apodo) {
        SQLiteDatabase db = getWritableDatabase();
        String[] argumentos = new String[]{apodo,String.valueOf(obteneridUsuario(Constantes.USUARIO))};
        db.delete(Constantes.TABLA_MASCOTAS, "apodo = ? and id_usuario = ?", argumentos);
        db.close();
    }
}
