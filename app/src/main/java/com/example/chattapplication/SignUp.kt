package com.example.chattapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class SignUp : AppCompatActivity() {
    private lateinit var edtEmail: EditText
    private lateinit var edtpassword: EditText
    private lateinit var btnSignup: Button
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        edtEmail=findViewById(R.id.email)
        edtpassword=findViewById(R.id.password)
        btnSignup=findViewById(R.id.signup)
        btnSignup.setOnClickListener(){
            val email = edtEmail.text.toString()
            val password =edtpassword.text.toString()
            signUp(email,password)
        }
    }
    private fun signUp(email: String,password: String){
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, code for jumping to main
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                   Toast.makeText(this@SignUp,"some error ocurred",Toast.LENGTH_LONG).show()
                }
            }
    }
}