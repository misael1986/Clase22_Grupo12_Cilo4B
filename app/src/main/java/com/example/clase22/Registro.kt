package com.example.clase22

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class Registro : AppCompatActivity() {
    lateinit var etEmail: EditText
    lateinit var etConfPass: EditText
    private lateinit var etPass: EditText
    private lateinit var btnCrear: Button
    lateinit var tvRedirectLogin: TextView


    private lateinit var auth: FirebaseAuth// creacion de Firebase authentication object


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etEmail = findViewById(R.id.etEmail)
        etConfPass = findViewById(R.id.etConfClave)
        etPass = findViewById(R.id.etClave)
        btnCrear = findViewById(R.id.btnCrear)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        auth = Firebase.auth

        btnCrear.setOnClickListener {
            CrearUsuario()
        }

        tvRedirectLogin.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun CrearUsuario() {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        // check pass
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank()) {
            Toast.makeText(this, "Email y/o Password NO pueden estar en Blanco",
                Toast.LENGTH_LONG).show()
            return
        }

        if (pass != confirmPassword) {
            Toast.makeText(this, "Password y Confirmacion no son iguales",
                Toast.LENGTH_LONG)
                .show()
            return
        }

        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this) {
            try {
                if (it.isSuccessful) {
                    Toast.makeText(this, "Creacion de Usuario existosa", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Error creado Usuario!", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception){
                println(e.stackTrace)
            }
        }
    }
}