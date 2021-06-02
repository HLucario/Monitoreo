package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import okhttp3.ResponseBody
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
            RetrofitClient.instance.recuperaPass(correo,pass)
                .enqueue(object: Callback<ResponseBody> {
                    override fun onResponse(call: Call<ResponseBody>,response: Response<ResponseBody>) {
                        if(response.code()==200)
                        {
                            Log.d("REALIZADO: ",response.body().toString())
                            Log.d("REALIZADO: ",response.code().toString())
                            Toast.makeText(applicationContext,response.body().toString(),Toast.LENGTH_LONG).show()
                        }
                        else
                        {
                            Log.d("REALIZADO: ",response.errorBody()!!.string())
                        }
                    }
                    override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                        Log.d("REALIZADO: ", "Falló")
                        Log.d("REALIZADO: ",t.localizedMessage ?: "No hay mensaje")
                        Log.d("REALIZADO: ",t.stackTraceToString())
                        //Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
                    }

                })
        }
    }
}