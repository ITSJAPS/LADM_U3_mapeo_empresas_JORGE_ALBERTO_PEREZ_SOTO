package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto


import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BaseDatos(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) { //CONSTRUCTOR QUE TIENE AL FINAL UN ENTERO
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE AREA(" +
                       "IDAREA INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       "DESCRIPCION VARCHAR(200)," +
                       "DIVISION VARCHAR(50)," +
                       "CAN_EMPLEADOS INTEGER)")

        db.execSQL("CREATE TABLE SUBDEPARTAMENTO(" +
                    "IDSUBDEPTO INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "IDEDIFICIO VARCHAR(20),"+
                    "PISO VARCHAR(50),"+
                    "IDAREA INTEGER,"+
                    "FOREIGN KEY (IDAREA) REFERENCES AREA(IDAREA))")


        /*db.delete("SUBDEPARTAMENTO",null,null)*/


    }

    override fun onUpgrade(db: SQLiteDatabase, p1: Int, p2: Int) {
    }


}