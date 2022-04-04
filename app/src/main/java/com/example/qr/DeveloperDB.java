package com.example.qr;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DeveloperDB extends SQLiteOpenHelper {

    private static final String NOMBRE_DB="developer.db";
    private static final int VERSION_DB=1;
    private static final String TABLA_REGISTROS="CREATE TABLE REGISTROS (CODIGO INT PRIMARY KEY,UBICACION TEXT, TELEFONO INT )";

    public DeveloperDB(@Nullable Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);

    }

    public static void agregarRegistros(String toString, String toString1) {
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_REGISTROS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_REGISTROS);
        sqLiteDatabase.execSQL(TABLA_REGISTROS);

    }

    public void agregarRegistros(int codigo, String ubicacion, int telefono){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO REGISTROS VALUES('"+codigo+","+telefono+","+ubicacion+"')");
            bd.close();
        }
    }
}
