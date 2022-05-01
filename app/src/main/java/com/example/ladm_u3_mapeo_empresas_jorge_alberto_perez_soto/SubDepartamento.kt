package com.example.ladm_u3_mapeo_empresas_jorge_alberto_perez_soto

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteException

class SubDepartamento(este:Context) {

    /* "CREATE TABLE SUBDEPARTAMENTO(" +
                    "IDSUBDEPTO INTEGER PRIMARY KEY AUTOINCREMENT,"+
                    "IDEDIFICIO VARCHAR(20),"+
                    "PISO VARCHAR(50),"+
                    "IDAREA INTEGER,"+
     */

    var idSubdepto=0
    var idEdificio=""
    var piso =""
    var idArea=0

    private var este=este
    var err=""

    fun insertar():Boolean{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        err=""
        try {
            val tabla=baseDatos.writableDatabase
            var datos= ContentValues()

            datos.put("IDEDIFICIO",idEdificio)
            datos.put("PISO",piso)
            datos.put("IDAREA",idArea)


            var resultado=tabla.insert("SUBDEPARTAMENTO",null,datos)
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

    fun mostrarTodos():ArrayList<SubDepartamento>{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        var arreglo=ArrayList<SubDepartamento>()


        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM SUBDEPARTAMENTO"

            var cursor=tabla.rawQuery(SQL_SELECT,null)
            if (cursor.moveToFirst()){
                do{
                    val subCursor=SubDepartamento(este)
                    subCursor.idSubdepto = cursor.getInt(0)
                    subCursor.idEdificio=cursor.getString(1)
                    subCursor.piso=cursor.getString(2)
                    subCursor.idArea= cursor.getInt(3)

                    arreglo.add(subCursor)

                }while (cursor.moveToNext())
            }

        }catch (err: SQLiteException){

        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarSubdepartamento(SubBuscarid:Int):SubDepartamento{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)

        val sub=SubDepartamento(este)
        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT * FROM SUBDEPARTAMENTO WHERE IDSUBDEPTO=${SubBuscarid}"


            var cursor=tabla.rawQuery(SQL_SELECT, null)
            if (cursor.moveToFirst()){


                sub.idSubdepto = cursor.getInt(0)
                sub.idEdificio=cursor.getString(1)
                sub.piso=cursor.getString(2)
                sub.idArea= cursor.getInt(3)

            }

        }catch (err: SQLiteException){
            this.err=err.message!!

        }finally {
            baseDatos.close()
        }
        return  sub
    }


    fun actualizar():Boolean{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        err=""
        try {
            var tabla= baseDatos.writableDatabase
            var datosAct= ContentValues()


            datosAct.put("IDEDIFICIO",idEdificio)
            datosAct.put("PISO",piso)
            datosAct.put("IDAREA",idArea)

            val resultado= tabla.update("SUBDEPARTAMENTO",datosAct,"IDSUBDEPTO=${idSubdepto}", null)

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
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        err=""
        try {
            var tabla = baseDatos.writableDatabase

            var respuesta=tabla.delete("SUBDEPARTAMENTO","IDSUBDEPTO='${idSubdepto}'", null)
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



    ///Consultas
    fun mostraridEdificio(dato:Int):ArrayList<SubDepartamento>{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        var arreglo=ArrayList<SubDepartamento>()


        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT S.ID_SUBDEPTO, S.ID_EDIFICIO, S.PISO, A.DESCRIPCION, A.DIVISION, A.ID_AREA " +
                    "FROM SUBDEPARTAMENTO S " +
                    "INNER JOIN AREA A " +
                    "ON A.ID_AREA = S.ID_AREA " +
                    "WHERE S.ID_EDIFICIO LIKE '${dato}%'"

            var cursor=tabla.rawQuery(SQL_SELECT,null)
            if (cursor.moveToFirst()){
                do{
                    val subCursor=SubDepartamento(este)
                    subCursor.idSubdepto = cursor.getInt(0)
                    subCursor.idEdificio=cursor.getString(1)
                    subCursor.piso=cursor.getString(2)
                    subCursor.idArea= cursor.getInt(3)

                    arreglo.add(subCursor)

                }while (cursor.moveToNext())
            }

        }catch (err: SQLiteException){

        }finally {
            baseDatos.close()
        }
        return arreglo
    }


    fun mostrarDescripcion(parametro:String):ArrayList<SubDepartamento>{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        var arreglo=ArrayList<SubDepartamento>()


        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT S.ID_SUBDEPTO, S.ID_EDIFICIO, S.PISO, A.DESCRIPCION, A.DIVISION, A.ID_AREA " +
                    "FROM SUBDEPARTAMENTO S " +
                    "INNER JOIN AREA A " +
                    "ON A.ID_AREA = S.ID_AREA " +
                    "WHERE A.DESCRIPCION LIKE '${parametro}%'"

            var cursor=tabla.rawQuery(SQL_SELECT,null)
            if (cursor.moveToFirst()){
                do{
                    val subCursor=SubDepartamento(este)
                    subCursor.idSubdepto = cursor.getInt(0)
                    subCursor.idEdificio=cursor.getString(1)
                    subCursor.piso=cursor.getString(2)
                    subCursor.idArea= cursor.getInt(3)

                    arreglo.add(subCursor)

                }while (cursor.moveToNext())
            }

        }catch (err: SQLiteException){

        }finally {
            baseDatos.close()
        }
        return arreglo
    }

    fun mostrarDivision(parametro:String):ArrayList<SubDepartamento>{
        var baseDatos=BaseDatos(este,"subdepartamento",null,1)
        var arreglo=ArrayList<SubDepartamento>()


        err=""
        try {
            var tabla=baseDatos.readableDatabase
            var SQL_SELECT= "SELECT S.ID_SUBDEPTO, S.ID_EDIFICIO, S.PISO, A.DESCRIPCION, A.DIVISION, A.ID_AREA " +
                    "FROM SUBDEPARTAMENTO S " +
                    "INNER JOIN AREA A " +
                    "ON A.ID_AREA = S.ID_AREA " +
                    "WHERE A.DESCRIPCION LIKE '${parametro}%'"

            var cursor=tabla.rawQuery(SQL_SELECT,null)
            if (cursor.moveToFirst()){
                do{
                    val subCursor=SubDepartamento(este)
                    subCursor.idSubdepto = cursor.getInt(0)
                    subCursor.idEdificio=cursor.getString(1)
                    subCursor.piso=cursor.getString(2)
                    subCursor.idArea= cursor.getInt(3)

                    arreglo.add(subCursor)

                }while (cursor.moveToNext())
            }

        }catch (err: SQLiteException){

        }finally {
            baseDatos.close()
        }
        return arreglo
    }




}