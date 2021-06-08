package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HijoInformacion : AppCompatActivity() {
    private lateinit var adapter: AlertaResponseAdapter
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hijo_informacion)
        val id=intent.getIntExtra("id",0)
        val nombre=intent.getStringExtra("nombre")
        val ap_pat=intent.getStringExtra("ap_pat")
        val ap_Mat=intent.getStringExtra("ap_Mat")
        val edad=intent.getIntExtra("edad",0)
        val dispositivo=intent.getStringExtra("dispositivo")
        val tutor_email=intent.getStringExtra("tutor_email")
        val txtNI=findViewById<TextView>(R.id.editNI)
        txtNI.text=nombre+" "+ap_pat+ap_Mat
        val txtEI=findViewById<TextView>(R.id.editEI)
        txtEI.text=edad.toString()
        var alertasR= emptyList<AlertaResponse>()
        val recycler = findViewById<RecyclerView>(R.id.alertas)
        lifecycleScope.launch{
            RetrofitClient.instance.tablaAlertasLast(tutor_email.toString(),id)
                .enqueue(object: Callback<List<AlertaResponse>> {
                    override fun onResponse(call: Call<List<AlertaResponse>>, response: Response<List<AlertaResponse>>) {
                        if(response.code()==200)
                        {
                            alertasR=response.body()!!
                            adapter = AlertaResponseAdapter(alertasR)
                            recycler.adapter = adapter
                        }
                        else
                        {
                            alertasR= emptyList()
                        }
                    }
                    override fun onFailure(call: Call<List<AlertaResponse>>, t: Throwable) {
                        Toast.makeText(applicationContext,t.message, Toast.LENGTH_LONG).show()
                        alertasR= emptyList()
                    }
                })
        }
    }
}