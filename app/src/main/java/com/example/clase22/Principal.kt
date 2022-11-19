package com.example.clase22

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class Principal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_principal)
        Toast.makeText(this, "Ingreso exitoso", Toast.LENGTH_LONG).show()
    }
}