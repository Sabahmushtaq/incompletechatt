package com.example.chattapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class Login : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnLogin: Button
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.email)
        edtpassword=findViewById(R.id.password)
        btnLogin=findViewById(R.id.login)
        btnSignup=findViewById(R.id.signup)
        btnSignup.setOnClickListener(){
            val intent = Intent(this,SignUp::class.java)
            startActivity(intent)
    }
        btnLogin.setOnClickListener(){
            val email= edtEmail.text.toString()
            val password =edtpassword.text.toString()
            login(email,password)
        }
}
    private fun login(email:String,password:String){
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this@Login,MainActivity::class.java)
                    startActivity(intent)

                } else {

                    Toast.makeText(this@Login,"user does not exist",Toast.LENGTH_LONG).show()
                }
            }
    }
}