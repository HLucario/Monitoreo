package com.example.monitoreo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.monitoreo.R
import com.example.monitoreo.api.RetrofitClient
import com.example.monitoreo.api.TutorNetwork
import com.example.monitoreo.api.asNetwork
import com.example.monitoreo.models.Tutor
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ModificarDatos : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_datos)
        val btnGMT = findViewById<Button>(R.id.bntGMT)
        val txtMN = findViewById<TextView>(R.id.txtMN)
        val txtMAP = findViewById<TextView>(R.id.txtMAP)
        val txtMAM = findViewById<TextView>(R.id.txtMAM)
        val txtME = findViewById<TextView>(R.id.txtME)
        val txtCA = findViewById<TextView>(R.id.txtCA)
        val txtMNC = findViewById<TextView>(R.id.txtMNC)
        val correo = intent.getStringExtra("email").toString()
        val nombre = intent.getStringExtra("nombre").toString()
        val ap_pat = intent.getStringExtra("ap_pat").toString()
        val ap_Mat = intent.getStringExtra("ap_Mat").toString()
        val password = intent.getStringExtra("password").toString()
        val edad = intent.getIntExtra("edad", 0)
        var nombre2 = ""
        var ap_pat2 = ""
        var ap_Mat2 = ""
        var edad_t = ""
        var edad2 = 0
        var password2 = ""
        var passwordC = ""
        putData(txtMN, txtMAP, txtMAM, txtME, nombre, ap_pat, ap_Mat, correo, edad)
        btnGMT.setOnClickListener {
            val result = validateData(txtMN, txtMAP, txtMAM, txtME, txtCA)
            if (result)
            {
                return@setOnClickListener
            }
            else
            {
                nombre2 = txtMN.text.toString()
                ap_pat2 = txtMAP.text.toString()
                ap_Mat2 = txtMAM.text.toString()
                edad_t = txtME.text.toString()
                edad2 = edad_t.toInt()
                password2 = txtMNC.text.toString()
                passwordC = txtCA.text.toString()
                changeData(
                    nombre,
                    ap_pat,
                    ap_Mat,
                    correo,
                    edad,
                    password,
                    nombre2,
                    ap_pat2,
                    ap_Mat2,
                    edad2,
                    password2
                )
            }
        }
    }

    fun putData(
        txtMN: TextView,
        txtMAP: TextView,
        txtMAM: TextView,
        txtME: TextView,
        nombre: String,
        ap_pat: String,
        ap_Mat: String,
        correo: String,
        edad: Int
    ) {
        txtMN.text = nombre
        txtMAP.text = ap_pat
        txtMAM.text = ap_Mat
        txtME.text = edad.toString()
    }

    fun validateData(
        txtMN: TextView,
        txtMAP: TextView,
        txtMAM: TextView,
        txtME: TextView,
        txtCA: TextView
    ): Boolean {
        if (txtMN.text.isEmpty()) {
            txtMN.error = "Este campo es requerido"
            txtMN.requestFocus()
            return true
        }
        if (txtMAP.text.isEmpty()) {
            txtMAP.error = "Este campo es requerido"
            txtMAP.requestFocus()
            return true
        }
        if (txtMAM.text.isEmpty()) {
            txtMAM.error = "Este campo es requerido"
            txtMAM.requestFocus()
            return true
        }
        if (txtME.text.isEmpty()) {
            txtME.error = "Este campo es requerido"
            txtME.requestFocus()
            return true
        }
        if (txtCA.text.isEmpty()) {
            txtCA.error = "ES NECESARIO INTRODUCIR LA CONTRASEÑA PARA GUARDAR LOS CAMBIOS"
            txtCA.requestFocus()
            return true
        }
        return false
    }

    fun changeData(
        nombre: String,
        ap_pat: String,
        ap_Mat: String,
        correo: String,
        edad: Int,
        password: String,
        nombre2: String,
        ap_pat2: String,
        ap_Mat2: String,
        edad2: Int,
        password2: String
    ) {
        var changeC = 0
        var changeD = 0
        if (password2.isNotEmpty())
        {
            if (password2 != password)
            {
                changeC = 1
            }
        }
        if (nombre != nombre2 || ap_pat != ap_pat2 || ap_Mat != ap_Mat2 || edad != edad2)
        {
            changeD = 1
        }
        if (changeC == 1 || changeD == 1)
        {
            lateinit var tutor: TutorNetwork
            if (changeC == 1 && changeD == 1)
            {
                tutor = Tutor(correo, nombre2, ap_pat2, ap_Mat2, edad2, password).asNetwork()
                updateData(tutor)
                updatePassword(correo, password, password2)
            }
            else if (changeC == 1 && changeD == 0)
            {
                updatePassword(correo, password, password2)
            }
            else
            {
                tutor = Tutor(correo, nombre2, ap_pat2, ap_Mat2, edad2, password).asNetwork()
                updateData(tutor)
            }

        }
        else
        {
            Toast.makeText(applicationContext, "NO HAY MODIFICACIONES", Toast.LENGTH_LONG).show()
        }
    }

    fun updateData(tutor: TutorNetwork)
    {
        RetrofitClient.instance.actualizaTutor(tutor).enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
            {
                if (response.code() == 200)
                {
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
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

    fun updatePassword(email: String, old_pass: String, new_pass: String)
    {
        RetrofitClient.instance.actualizaPass(email, old_pass, new_pass).enqueue(object : Callback<ResponseBody>
        {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>)
            {
                if (response.code() == 200)
                {
                    Toast.makeText(applicationContext, response.message(), Toast.LENGTH_LONG).show()
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