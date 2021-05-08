package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText

class Registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
    }
    fun Registrar()
    {
        val editNombre = findViewById<EditText>(R.id.editNombre)
        val nombre = editNombre.text.toString()
        val editApPat = findViewById<EditText>(R.id.editApPat)
        val ap_paterno = editApPat.text.toString()
        val editApMat = findViewById<EditText>(R.id.editApMat)
        val ap_materno = editApMat.text.toString()
        val editCorreo = findViewById<EditText>(R.id.editNombre)
        val correo = editCorreo.text.toString()
        val editContra = findViewById<EditText>(R.id.editNombre)
        val password = editContra.text.toString()
    }
}