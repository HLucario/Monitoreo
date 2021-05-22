package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarHijo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_hijo)
        val btnGuardarH = findViewById<Button>(R.id.btnGuardarH)
        btnGuardarH.setOnClickListener{
            val editNombreH = findViewById<EditText>(R.id.editNombreH)
            val nombre = editNombreH.text.toString()
            val editApPatH = findViewById<EditText>(R.id.editApPatH)
            val ap_paterno = editApPatH.text.toString()
            val editApMatH = findViewById<EditText>(R.id.editApMatH)
            val ap_materno = editApMatH.text.toString()
            val editEdadH = findViewById<EditText>(R.id.editEdadH)
            val edad_t = editEdadH.text.toString()
            val editDispositivo = findViewById<EditText>(R.id.editDispo)
            val dispositivo = editDispositivo.text.toString()
            val editCorreoT = findViewById<EditText>(R.id.editCorreoT)
            val correo_t = editCorreoT.text.toString()
            if(nombre.isEmpty())
            {
                editNombreH.error="El nombre es requerido"
                editNombreH.requestFocus()
                return@setOnClickListener
            }
            if(ap_paterno.isEmpty())
            {
                editApPatH.error="El apellido paterno es requerido"
                editApPatH.requestFocus()
                return@setOnClickListener
            }
            if(ap_materno.isEmpty())
            {
                editApMatH.error="El apellido materno es requerido"
                editApMatH.requestFocus()
                return@setOnClickListener
            }
            if(correo_t.isEmpty())
            {
                editCorreoT.error="El correo es requerido"
                editCorreoT.requestFocus()
                return@setOnClickListener
            }
            if(dispositivo.isEmpty())
            {
                editDispositivo.error="La contraseña es requerida"
                editDispositivo.requestFocus()
                return@setOnClickListener
            }
            if(edad_t.isEmpty())
            {
                editEdadH.error="La edad es requerida"
                editEdadH.requestFocus()
                return@setOnClickListener
            }
            val edad = edad_t.toInt()
            val hijo = Hijo(nombre,ap_paterno,ap_materno,edad,dispositivo,correo_t)
            val gson = Gson()
            val hijo_j:String=gson.toJson(hijo)
            /*RetrofitClient.instance.registrarHijo(hijo_j)
                .enqueue(object: Callback<DefaultResponse> {
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                    }
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext,response.body()?.message, Toast.LENGTH_LONG).show()
                    }
                }*/
        }
    }
}