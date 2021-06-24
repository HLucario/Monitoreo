package com.example.monitoreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ListAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoreo.api.HijoNetwork
import com.example.monitoreo.api.asNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HijoInformacion : AppCompatActivity() {
    private lateinit var adapter: AlertaResponseAdapter
    private lateinit var alertasR: List<AlertaResponse>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alertasR = emptyList()
        setContentView(R.layout.activity_hijo_informacion)
        val id = intent.getIntExtra("id", 0)
        val nombre = intent.getStringExtra("nombre")
        val ap_pat = intent.getStringExtra("ap_pat")
        val ap_Mat = intent.getStringExtra("ap_Mat")
        val edad = intent.getIntExtra("edad", 0)
        val dispositivo = intent.getStringExtra("dispositivo")
        val tutor_email = intent.getStringExtra("tutor_email")
        val hijo: HijoNetwork = Hijo(
            id,
            nombre.toString(),
            ap_pat.toString(),
            ap_Mat.toString(),
            edad,
            dispositivo.toString(),
            tutor_email.toString()
        ).asNetwork()
        val txtNI = findViewById<TextView>(R.id.editNI)
        txtNI.text = nombre + " " + ap_pat + " " + ap_Mat
        val txtEI = findViewById<TextView>(R.id.editEI)
        txtEI.text = edad.toString()
        val recycler = findViewById<RecyclerView>(R.id.alertas)
        lifecycleScope.launch {
            obtenerAlertas(tutor_email.toString(), id, recycler)
        }
        val btnE = findViewById<Button>(R.id.btnE)
        btnE.setOnClickListener {
            RetrofitClient.instance.eliminaHijo(hijo)
                .enqueue(object : Callback<ResponseBody> {
                    override fun onResponse(
                        call: Call<ResponseBody>,
                        response: Response<ResponseBody>
                    ) {
                        if (response.code() == 200) {
                            Toast.makeText(
                                applicationContext,
                                response.message(),
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

    suspend fun obtenerAlertas(tutor_email: String, id: Int, recycler: RecyclerView) =
        withContext(Dispatchers.Main) {
            RetrofitClient.instance.tablaAlertasLast(tutor_email, id)
                .enqueue(object : Callback<List<AlertaResponse>> {
                    override fun onResponse(
                        call: Call<List<AlertaResponse>>,
                        response: Response<List<AlertaResponse>>
                    ) {
                        if (response.code() == 200) {
                            alertasR = response.body()!!
                            adapter = AlertaResponseAdapter(alertasR)

                            recycler.adapter = adapter
                        } else {
                            alertasR = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<List<AlertaResponse>>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        alertasR = emptyList()
                    }
                })
        }
}