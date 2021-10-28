package com.example.monitoreo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.monitoreo.R
import com.example.monitoreo.api.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RecuperarContra : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recuperar_contra)
        val btnNC = findViewById<Button>(R.id.btnNC)
        val txtCorreoR = findViewById<EditText>(R.id.editCorreoR)
        val txtNC = findViewById<EditText>(R.id.editNuevaC)
        btnNC.setOnClickListener {
            val correo = txtCorreoR.text.toString()
            val pass = txtNC.text.toString()
            RetrofitClient.instance.recuperaPass(correo, pass).enqueue(object : Callback<ResponseBody>
            {
                override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
                {
                    if (response.code() == 200)
                    {
                        Toast.makeText(
                            applicationContext,
                            response.body().toString(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else
                    {
                        Toast.makeText(
                            applicationContext,
                            response.errorBody()!!.string(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
                override fun onFailure(call: Call<ResponseBody>, t: Throwable)
                {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }
            })
        }
    }
}