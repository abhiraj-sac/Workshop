package com.example.workshop

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import androidx.core.content.contentValuesOf

class Sqlhelper(context:Context):SQLiteOpenHelper(context ,DATABASE_NAME ,null, DATABASE_VERSION) {
    companion object{
        private const val DATABASE_NAME ="student.db"
        private const val DATABASE_VERSION =1
        private const val TBL_NAME="tbl_student"
        private const val NAME="name"
        private const val ID="id"
        private const val EMAIL="email"
    }
    override fun onCreate(db: SQLiteDatabase?) {
     val createTBLstudent =("CREATE TABLE" + TBL_NAME + "(" + ID + " INTEGER PRIMARY KEY,"
                   + NAME + "TEXT," + EMAIL + "TEXT" +")")
        db?.execSQL(createTBLstudent)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
   db!!.execSQL("DROP TABLE IF EXISTS $TBL_NAME")
        onCreate(db)
    }
    fun insertstudent(std:studentmodel):Long{
        val db = this.writableDatabase
        val contantvalue= ContentValues()
        contantvalue.put(ID,std.id)
        contantvalue.put(NAME,"java")
        contantvalue.put(NAME, "java")
        contantvalue.put(EMAIL,"cplus")
  val success = db.insert(TBL_NAME , null , contantvalue)
        close()
        return success

    }
    @SuppressLint("Range")
    fun getAllstudent():ArrayList<studentmodel>{
        val stdlist:ArrayList<studentmodel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_NAME"
        val db = this.readableDatabase
        val cursor :Cursor?
        try {
  cursor = db.rawQuery(selectQuery,null)
        }
        catch (e:Exception){
            e.printStackTrace()
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id:Int
        var name:String
        var email:String
        if(cursor.moveToFirst()){
            do{
                id = cursor.getInt(cursor.getColumnIndex("id"))
                name = cursor.getString(cursor.getColumnIndex("name"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                val std=studentmodel(id = id , name= name , email = email)
                stdlist.add(std)
            } while (cursor.moveToNext())
        }
        return stdlist
    }
}