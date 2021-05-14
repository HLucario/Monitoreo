package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Registrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registrar)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        btnRegistrar.setOnClickListener{
            val editNombre = findViewById<EditText>(R.id.editNombre)
            val nombre = editNombre.text.toString()
            val editApPat = findViewById<EditText>(R.id.editApPat)
            val ap_paterno = editApPat.text.toString()
            val editApMat = findViewById<EditText>(R.id.editApMat)
            val ap_materno = editApMat.text.toString()
            val editCorreo = findViewById<EditText>(R.id.editNombre)
            val correo = editCorreo.text.toString()
            val editContra = findViewById<EditText>(R.id.editContra)
            val password = editContra.text.toString()
            val editEdad = findViewById<EditText>(R.id.editEdad)
            val edad_t = editEdad.text.toString()
            if(nombre.isEmpty())
            {
                editNombre.error="El nombre es requerido"
                editNombre.requestFocus()
                return@setOnClickListener
            }
            if(ap_paterno.isEmpty())
            {
                editApPat.error="El apellido paterno es requerido"
                editApPat.requestFocus()
                return@setOnClickListener
            }
            if(ap_materno.isEmpty())
            {
                editApMat.error="El apellido materno es requerido"
                editApMat.requestFocus()
                return@setOnClickListener
            }
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
            if(edad_t.isEmpty())
            {
                editEdad.error="La edad es requerida"
                editEdad.requestFocus()
                return@setOnClickListener
            }
            val edad = edad_t.toInt()
            val tutor = Tutor(correo,nombre,ap_paterno,ap_materno,edad,password)
            RetrofitClient.instance.registrarTutor(tutor)
                .enqueue(object: Callback<DefaultResponse>{
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext,response.body()?.message,Toast.LENGTH_LONG).show()
                    }
                })
        }
    }

}