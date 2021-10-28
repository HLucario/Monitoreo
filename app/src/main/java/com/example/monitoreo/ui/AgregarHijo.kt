package com.example.monitoreo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.monitoreo.R
import com.example.monitoreo.api.RetrofitClient
import com.example.monitoreo.api.HijoNetwork
import com.example.monitoreo.api.asNetwork
import com.example.monitoreo.models.Hijo
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AgregarHijo : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_hijo)
        val btnGuardarH = findViewById<Button>(R.id.btnGuardarH)
        btnGuardarH.setOnClickListener {
            val editNombreH = findViewById<EditText>(R.id.editNombreH)
            val nombre = editNombreH.text.toString()
            val editApPatH = findViewById<EditText>(R.id.editApPatH)
            val ap_paterno = editApPatH.text.toString()
            val editApMatH = findViewById<EditText>(R.id.editApMatH)
            val ap_materno = editApMatH.text.toString()
            val editEdadH = findViewById<EditText>(R.id.editEdadH)
            val edad_t = editEdadH.text.toString()
            if (nombre.isEmpty())
            {
                editNombreH.error = "El nombre es requerido"
                editNombreH.requestFocus()
                return@setOnClickListener
            }
            if (ap_paterno.isEmpty())
            {
                editApPatH.error = "El apellido paterno es requerido"
                editApPatH.requestFocus()
                return@setOnClickListener
            }
            if (ap_materno.isEmpty())
            {
                editApMatH.error = "El apellido materno es requerido"
                editApMatH.requestFocus()
                return@setOnClickListener
            }
            if (edad_t.isEmpty())
            {
                editEdadH.error = "La edad es requerida"
                editEdadH.requestFocus()
                return@setOnClickListener
            }
            val edad = edad_t.toInt()
            val correo_t = intent.getStringExtra("email").toString()
            val hijo: HijoNetwork = Hijo(0, nombre, ap_paterno, ap_materno, edad, "", correo_t).asNetwork()
            RetrofitClient.instance.registrarHijo(hijo)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                applicationContext,
                                response.body()!!.string(),
                                Toast.LENGTH_LONG
                            ).show()
                        } else {
                            Toast.makeText(
                                applicationContext,
                                response.errorBody()!!.string(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }

                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                    }
                })
        }
    }
}