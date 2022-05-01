package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class Area (este:Context){

    var idArea=0
    var descripcion=""
    var division =""
    var cantEmpleados=0

    private var este=este
    private var err=""

    fun insertar():Boolean{
        var baseDatos=BaseDatos(este,"area",null,1)
        err=""
        try {
            val tabla=baseDatos.writableDatabase
            var datos= ContentValues()

            datos.put("DESCRIPCION",descripcion)
            datos.put("DIVISION",division)
            datos.put("CAN_EMPLEADOS",cantEmpleados)

            var resultado=tabla.insert("AREA",null,datos)
            if (resultado==-1L){
                return false
            }
        }catch (err: SQLiteException){
            this.err=err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }

    fun mostrarTodos():ArrayList<Area>{
        var baseDatos=BaseDatos(este,"area",null,1)
        var arreglo=ArrayList<Area>()


        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM AREA"

            var cursor=tabla.rawQuery(SQL_SELECT,null)
            if (cursor.moveToFirst()){
                do{
                    val areaCursor=Area(este)
                    areaCursor.idArea = cursor.getInt(0)
                    areaCursor.descripcion=cursor.getString(1)
                    areaCursor.division=cursor.getString(2)
                    areaCursor.cantEmpleados= cursor.getInt(3)

                    arreglo.add(areaCursor)

                }while (cursor.moveToNext())
            }

        }catch (err: SQLiteException){

        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarArea(AreaBuscarid:Int):Area{
        var baseDatos=BaseDatos(este,"area",null,1)

        val area=Area(este)
        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM AREA WHERE IDAREA=${AreaBuscarid}"


            var cursor=tabla.rawQuery(SQL_SELECT, null)
            if (cursor.moveToFirst()){


                area.idArea = cursor.getInt(0)
                area.descripcion=cursor.getString(1)
                area.division=cursor.getString(2)
                area.cantEmpleados= cursor.getInt(3)

            }

        }catch (err: SQLiteException){
            this.err=err.message!!

        }finally {
            baseDatos.close()
        }
        return  area
    }


    fun actualizar():Boolean{
        var baseDatos=BaseDatos(este,"area",null,1)
        err=""
        try {
            var tabla= baseDatos.writableDatabase
            var datosAct= ContentValues()

            datosAct.put("DESCRIPCION",descripcion)
            datosAct.put("DIVISION",division)
            datosAct.put("CAN_EMPLEADOS",cantEmpleados)

            val resultado= tabla.update("AREA",datosAct,"IDAREA=${idArea}", null)

            if (resultado==0){
                return false
            }

        }catch (err: SQLiteException){
            this.err=err.message!!
            return false
        }finally {
            baseDatos.close()
        }
        return true
    }


    fun eliminar():Boolean{
        var baseDatos=BaseDatos(este,"area",null,1)
        err=""
        try {
            var tabla = baseDatos.writableDatabase

            var respuesta=tabla.delete("AREA","IDAREA='${idArea}'", null)
            if (respuesta==0){
                return false
            }

        }catch (err: SQLiteException){
            this.err = err.message!!
            return false
        }finally {
            baseDatos.close()
        }

        return true
    }

}