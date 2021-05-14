package com.example.monitoreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIniciar=findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciar.setOnClickListener {
            val editCorreo = findViewById<EditText>(R.id.editNombre)
            val correo = editCorreo.text.toString().trim()
            val editContra = findViewById<EditText>(R.id.editNombre)
            val password = editContra.text.toString().trim()
            if(correo.isEmpty())
            {
                editCorreo.error="El correo es requerido"
                editCorreo.requestFocus()
                return@setOnClickListener
            }
            if(password.isEmpty())
            {
                editContra.error="La contraseña es requerida"
                editContra.requestFocus()
                return@setOnClickListener
            }
        }
    }

    fun comeRegistrar(view: View) {
        val intent = Intent(this@MainActivity, Registrar::class.java)
        startActivity(intent)
    }

    fun comeRecuperar(view: View) {
        val intent = Intent(this@MainActivity, RecuperarContra::class.java)
        startActivity(intent)
    }
}
