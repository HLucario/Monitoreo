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
        val nombre = intent.getStringExtra("nombre").toString()
        val ap_pat = intent.getStringExtra("ap_pat").toString()
        val ap_Mat = intent.getStringExtra("ap_Mat").toString()
        val edad = intent.getIntExtra("edad", 0)
        val dispositivo = intent.getStringExtra("dispositivo").toString()
        val tutor_email = intent.getStringExtra("tutor_email").toString()
        val hijo: HijoNetwork = Hijo(
            id,
            nombre,
            ap_pat,
            ap_Mat,
            edad,
            dispositivo,
            tutor_email
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
        val btnMH = findViewById<Button>(R.id.btnMH)
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
        btnMH.setOnClickListener {
            putData(id,nombre,ap_pat,ap_Mat,edad,dispositivo,tutor_email)
        }
    }
    fun putData(
        id: Int,
        nombre: String,
        ap_pat: String,
        ap_Mat: String,
        edad: Int,
        dispositivo: String,
        tutor_email: String,
    ) {
        val intent = Intent(this@HijoInformacion, ModificarHijo::class.java)
        intent.putExtra("id", id)
        intent.putExtra("nombre", nombre)
        intent.putExtra("ap_pat", ap_pat)
        intent.putExtra("ap_Mat", ap_Mat)
        intent.putExtra("edad", edad)
        intent.putExtra("dispositivo", dispositivo)
        intent.putExtra("tutor_email",tutor_email)
        startActivity(intent)
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
                        Log.d("ERROR:",t.message.toString())
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                        alertasR = emptyList()
                    }
                })
        }
}