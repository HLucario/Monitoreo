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
                .enqueue(object: Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        if(response.code()==200)
                        {
                            val defaultResponse=response.body()!!
                            tutor=Tutor(defaultResponse.email,defaultResponse.nombre,defaultResponse.ap_pat,defaultResponse.ap_Mat,defaultResponse.edad,defaultResponse.password)
                            val intent = Intent(this@MainActivity, Menu::class.java)
                            startActivity(intent)
                        }
                        else
                        {
                            val message=response.errorBody()!!.string()
                            Log.d("Prueba:",message)
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>,t: Throwable) {
                        Toast.makeText(applicationContext,"Usuario o contraseña incorrecta",Toast.LENGTH_LONG).show()
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
