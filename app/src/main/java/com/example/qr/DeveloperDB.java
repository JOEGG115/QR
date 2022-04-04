package com.example.qr;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class DeveloperDB extends SQLiteOpenHelper {

    private static final String UBICACION_DB="developer.db";
    private static final int VERSION_DB=1;
    private static final String TABLA_REGISTRO="CREATE TABLE REGISTROS(NUM INT PRIMARY KEY,UBICACION TEXT, TELEFONO INT )";

    public DeveloperDB(@Nullable Context context) {
        super(context, UBICACION_DB, null, VERSION_DB);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_REGISTRO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLA_REGISTRO);
    }

    public void agregarRegistro(String ubicacion, String telefono){
        SQLiteDatabase bd=getWritableDatabase();
        if (bd!=null){
            bd.execSQL("INSERT INTO REGISTRO VALUES('"+telefono+","+ubicacion+"')");
            bd.close();
        }
    }
}
