package com.example.workshop

import android.content.Intent
import android.database.sqlite.SQLiteOpenHelper
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var sqlhelper: Sqlhelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login.setOnClickListener {
            val intent = Intent(this , LoginActivity::class.java)
            startActivity(intent)
        }

        sqlhelper = Sqlhelper(this)
        add.setOnClickListener{addStudent()}
    view.setOnClickListener{getstudent()}
//        var helper = Mysqldatabase(applicationContext)
//        var db = helper.readableDatabase
//        var rs = db.rawQuery("SELECT * FROM USERS" , null)


//        if(rs.moveToNext()){
//           Toast.makeText(applicationContext
//                , rs.getString(1), Toast.LENGTH_SHORT).show()
//            val name = rs.getString(1)
//            val name2 = rs.getString(2)
////            val name3 =rs.getString(3)
////            val name4 =rs.getString(4)
//            id1.text = name.toString()
//            id2.text = name2.toString()
////            id3.text = name3.toString()
////            id4.text = name4.toString()
//        }
    }

    private fun getstudent() {
        val stdlist = sqlhelper.getAllstudent()
    Log.e("taghere", "${stdlist.size}")
    }

    private fun addStudent() {
val name = etname.text.toString()
val email = etemal.text.toString()
val std= studentmodel(name = name , email = email)
   val status = sqlhelper.insertstudent(std)
        if(status > -1){
            Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
        }
        else{
            Toast.makeText(this, "failes", Toast.LENGTH_SHORT).show()
        }
    }



}