package com.example.monitoreo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun comeRegistrar(view: View)
    {
        val intent = Intent(this@MainActivity, Registrar::class.java)
        startActivity(intent)
    }
    fun comeRecuperar(view:View)
    {
        val intent = Intent(this@MainActivity, RecuperarContra::class.java)
        startActivity(intent)
    }
}