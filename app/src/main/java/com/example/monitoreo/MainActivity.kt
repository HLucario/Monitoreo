package com.example.monitoreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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
                            Toast.makeText(applicationContext,response.message(), Toast.LENGTH_LONG).show()
                            val defaultResponse=response.body()!!
                            val bundle=Bundle()
                            bundle.putString("email",defaultResponse.email)
                            bundle.putString("nombre",defaultResponse.nombre)
                            bundle.putString("ap_pat",defaultResponse.ap_pat)
                            bundle.putString("ap_Mat",defaultResponse.ap_Mat)
                            bundle.putInt("edad",defaultResponse.edad)
                            bundle.putString("password",defaultResponse.password)
                            val intent = Intent(this@MainActivity, Menu::class.java)
                            intent.putExtras(bundle)
                            startActivity(intent)
                        }
                        else
                        {
                            Toast.makeText(applicationContext,response.errorBody()!!.string(),Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<LoginResponse>,t: Throwable) {
                        Toast.makeText(applicationContext,t.message,Toast.LENGTH_LONG).show()
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
