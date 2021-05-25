package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecuperarContra : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contra)
        val btnNC = findViewById<Button>(R.id.btnNC)
        val txtCorreoR = findViewById<EditText>(R.id.editCorreoR)
        val txtNC = findViewById<EditText>(R.id.editNuevaC)
        btnNC.setOnClickListener {
            val correo = txtCorreoR.text.toString()
            val pass = txtNC.text.toString()
            RetrofitClient.instance.recuperarPass(correo,pass)
                .enqueue(object: Callback<DefaultResponse> {
                    override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        //val defaultResponse=response.body()!!
                        //Toast.makeText(applicationContext,response.body()?.entity,Toast.LENGTH_LONG).show()
                        Log.d("RESPONSE","HECHO")
                    }
                    override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                        //Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        Log.d("FAILURE","FALLÓ")
                    }

                })
        }
    }
}