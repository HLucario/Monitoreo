package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

import android.widget.TextView
import android.widget.Toast
import com.example.monitoreo.api.HijoNetwork
import com.example.monitoreo.api.asNetwork
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModificarHijo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_hijo)
        val btnMDH = findViewById<Button>(R.id.btnMDH)
        val editNH = findViewById<TextView>(R.id.editNH)
        val editAP = findViewById<TextView>(R.id.editAP)
        val editAM = findViewById<TextView>(R.id.editAM)
        val editEH = findViewById<TextView>(R.id.editEH)
        val id = intent.getIntExtra("id", 0)
        val nombre = intent.getStringExtra("nombre").toString()
        val ap_pat = intent.getStringExtra("ap_pat").toString()
        val ap_Mat = intent.getStringExtra("ap_Mat").toString()
        val edad = intent.getIntExtra("edad", 0)
        val dispositivo = intent.getStringExtra("dispositivo").toString()
        val tutor_email = intent.getStringExtra("tutor_email").toString()
        var nombre2 = ""
        var ap_pat2 = ""
        var ap_Mat2 = ""
        var edad_t = ""
        var edad2 = 0
        putData(editNH, editAP, editAM, editEH, nombre, ap_pat, ap_Mat, edad)
        btnMDH.setOnClickListener {
            val result = validateData(editNH, editAP, editAM, editEH)
            if (result) {
                return@setOnClickListener
            } else {
                nombre2 = editNH.text.toString()
                ap_pat2 = editAP.text.toString()
                ap_Mat2 = editAM.text.toString()
                edad_t = editEH.text.toString()
                edad2 = edad_t.toInt()
                changeData(
                    id,
                    nombre,
                    ap_pat,
                    ap_Mat,
                    edad,
                    dispositivo,
                    tutor_email,
                    nombre2,
                    ap_pat2,
                    ap_Mat2,
                    edad2,
                )
            }
        }
    }


    fun putData(
        editNH: TextView,
        editAP: TextView,
        editAM: TextView,
        editEH: TextView,
        nombre: String,
        ap_pat: String,
        ap_Mat: String,
        edad: Int
    ) {
        editNH.text = nombre
        editAP.text = ap_pat
        editAM.text = ap_Mat
        editEH.text = edad.toString()
    }

    fun validateData(
        editNH: TextView,
        editAP: TextView,
        editAM: TextView,
        editEH: TextView
    ): Boolean {
        if (editNH.text.isEmpty()) {
            editNH.error = "Este campo es requerido"
            editNH.requestFocus()
            return true
        }
        if (editAP.text.isEmpty()) {
            editAP.error = "Este campo es requerido"
            editAP.requestFocus()
            return true
        }
        if (editAM.text.isEmpty()) {
            editAM.error = "Este campo es requerido"
            editAM.requestFocus()
            return true
        }
        if (editEH.text.isEmpty()) {
            editEH.error = "Este campo es requerido"
            editEH.requestFocus()
            return true
        }
        return false
    }

    fun changeData(
        id: Int,
        nombre: String,
        ap_pat: String,
        ap_Mat: String,
        edad: Int,
        dispositivo: String,
        tutor_email: String,
        nombre2: String,
        ap_pat2: String,
        ap_Mat2: String,
        edad2: Int,
    ) {
        var changeD = 0
        if (nombre != nombre2 || ap_pat != ap_pat2 || ap_Mat != ap_Mat2 || edad != edad2) {
            changeD = 1
        }
        if (changeD == 1) {
            var hijo: HijoNetwork =
                Hijo(id, nombre2, ap_pat2, ap_Mat2, edad2, dispositivo, tutor_email).asNetwork()
            updateData(hijo)
        } else {
            Toast.makeText(applicationContext, "NO HAY MODIFICACIONES", Toast.LENGTH_LONG).show()
        }
    }

    fun updateData(hijo: HijoNetwork) {
        RetrofitClient.instance.actualizaHijo(hijo)
            .enqueue(object : Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: Response<ResponseBody>
                ) {
                    if (response.code() == 200) {
                        Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG)
                            .show()
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