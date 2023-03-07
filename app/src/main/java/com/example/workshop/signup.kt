package com.example.workshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class signup : AppCompatActivity() {
    private lateinit var df: DatabaseReference
    private lateinit var name: TextView
    private lateinit var mauth:FirebaseAuth
    private lateinit var id: TextView
    private lateinit var button: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        name=findViewById(R.id.username)
        id=findViewById(R.id.userid)
        mauth= FirebaseAuth.getInstance()
        button=findViewById(R.id.btn)
        button.setOnClickListener {
            val name=name.text.toString()
            val id=id.text.toString()
            save(name ,id)
        }

    }

    private fun save(email: String, password: String) {
        mauth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent= Intent(this@signup,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "sorry", Toast.LENGTH_SHORT).show()
                }
            }
    }
    }
