package com.example.monitoreo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ModificarDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_modificar_datos)
        val btnGMT = findViewById<Button>(R.id.bntGMT)
        val txtMN=findViewById<TextView>(R.id.txtMN)
        val txtMAP=findViewById<TextView>(R.id.txtMAP)
        val txtMAM=findViewById<TextView>(R.id.txtMAM)
        val txtME=findViewById<TextView>(R.id.txtME)
        val txtMCE=findViewById<TextView>(R.id.txtMCE)
        val txtCA=findViewById<TextView>(R.id.txtCA)
        val txtMNC=findViewById<TextView>(R.id.txtMNC)
        var correo=intent.getStringExtra("email").toString()
        var nombre=intent.getStringExtra("nombre").toString()
        var ap_pat=intent.getStringExtra("ap_pat").toString()
        var ap_Mat=intent.getStringExtra("ap_Mat").toString()
        var edad=intent.getIntExtra("edad",0)
        var password=intent.getStringExtra("password").toString()
        putData(txtMN,txtMAP,txtMAM,txtME,txtMCE,nombre,ap_pat,ap_Mat,correo,edad)
        btnGMT.setOnClickListener {
            val result=validateData(txtMN,txtMAP,txtMAM,txtME,txtMCE,txtCA,nombre,ap_pat,ap_Mat,correo,edad,password)
            if(result)
            {
                return@setOnClickListener
            }
            else
            {

            }
        }
    }
    fun putData(txtMN:TextView,txtMAP:TextView,txtMAM:TextView,txtME:TextView,txtMCE:TextView,nombre:String,ap_pat:String,ap_Mat:String,correo:String,edad:Int)
    {
        txtMN.text=nombre
        txtMAP.text=ap_pat
        txtMAM.text=ap_Mat
        txtMCE.text=correo
        txtME.text=edad.toString()
    }
    fun validateData(txtMN:TextView,txtMAP:TextView,txtMAM:TextView,txtME:TextView,txtMCE:TextView,txtCA:TextView,nombre:String,ap_pat:String,ap_Mat:String,correo:String,edad:Int,password:String):Boolean
    {
        if(txtMN.text.isEmpty())
        {
            txtMN.error="Este campo es requerido"
            txtMN.requestFocus()
            return true
        }
        if(txtMAP.text.isEmpty())
        {
            txtMAP.error="Este campo es requerido"
            txtMAP.requestFocus()
            return true
        }
        if(txtMAM.text.isEmpty())
        {
            txtMAM.error="Este campo es requerido"
            txtMAM.requestFocus()
            return true
        }
        if(txtMCE.text.isEmpty())
        {
            txtMCE.error="Este campo es requerido"
            txtMCE.requestFocus()
            return true
        }
        if(txtME.text.isEmpty())
        {
            txtME.error="Este campo es requerido"
            txtME.requestFocus()
            return true
        }
        if(txtCA.text.isEmpty())
        {
            txtCA.error="ES NECESARIO INTRODUCIR LA CONTRASEÑA PARA GUARDAR LOS CAMBIOS"
            txtCA.requestFocus()
            return true
        }
        return false
    }
    fun changeData(txtMN:TextView,txtMAP:TextView,txtMAM:TextView,txtME:TextView,txtMCE:TextView,txtCA:TextView,txtMNC:TextView,nombre:String,ap_pat:String,ap_Mat:String,correo:String,edad:Int,password:String)
    {
        var changes=0
        if(!txtCA.text.isEmpty())
        {
            if(!txtCA.text.equals(password))
            {
                password=txtCA.text.toString()
            }
            changes++
        }
        if(txtMN.equals(nombre) && txtMAP)
        {
            changes++
        }

    }
}