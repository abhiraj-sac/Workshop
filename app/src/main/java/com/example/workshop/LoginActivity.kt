package com.example.workshop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var emails: TextView
    private lateinit var password: TextView
    private lateinit var mauth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        val valueofsign =0
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        mauth= FirebaseAuth.getInstance()
        emails=findViewById(R.id.email_text)
        password=findViewById(R.id.id)
        loginbutton.setOnClickListener {
            val email = emails.text.toString()
            val  pass=password.text.toString()
            login(email,pass)
        }
        createbtn.setOnClickListener {
            val intent= Intent(this,signup::class.java)
            startActivity(intent)
        }
    }

    private fun login(email: String, password: String) {
        mauth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val valueofsign = 1
                    val intent= Intent(this@LoginActivity,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
                }
            }
    }
}