package com.example.monitoreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnIniciar=findViewById<Button>(R.id.btnIniciarSesion)
        btnIniciar.setOnClickListener {
            val editCorreo = findViewById<EditText>(R.id.editCorreoI)
            val correo = editCorreo.text.toString().trim()
            val editContra = findViewById<EditText>(R.id.editPassI)
            val password = editContra.text.toString().trim()
            var tutor:Tutor
            val gson= Gson()
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
            RetrofitClient.instance.Login(correo,password)
                .enqueue(object: Callback<DefaultResponse> {
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        val defaultResponse=response.body()!!
                        tutor=gson.fromJson(defaultResponse.message,Tutor::class.java)
                        Log.d("prueba",tutor.nombre)
                    }
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                    }

                })
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
