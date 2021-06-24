package com.example.monitoreo.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import com.example.monitoreo.*
import com.example.monitoreo.databinding.FragmentConfiguracionBinding
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Configuracion : Fragment() {
    private var _binding: FragmentConfiguracionBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var email = ""
        arguments?.takeIf { it.containsKey("email") }?.apply {
            email = getString("email").toString()
        }
        var nombre = ""
        arguments?.takeIf { it.containsKey("nombre") }?.apply {
            nombre = getString("nombre").toString()
        }
        var ap_pat = ""
        arguments?.takeIf { it.containsKey("ap_pat") }?.apply {
            ap_pat = getString("ap_pat").toString()
        }
        var ap_Mat = ""
        arguments?.takeIf { it.containsKey("ap_Mat") }?.apply {
            ap_Mat = getString("ap_Mat").toString()
        }
        var edad = 0
        arguments?.takeIf { it.containsKey("edad") }?.apply {
            edad = getInt("edad")
        }
        var password = ""
        arguments?.takeIf { it.containsKey("password") }?.apply {
            password = getString("password").toString()
        }
        binding.txtCfN.text = nombre + " " + ap_pat + " " + ap_Mat
        binding.txtCfC.text = email
        binding.txtCfE.text = edad.toString()
        binding.btnConf.setOnClickListener {
            putData(email, nombre, ap_pat, ap_Mat, edad, password)
        }
        binding.btnCerrarS.setOnClickListener {
            val intent = Intent(Intent.ACTION_MAIN)
            intent.addCategory(Intent.CATEGORY_HOME)
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
        }
    }

    fun putData(
        email: String,
        nombre: String,
        ap_pat: String,
        ap_Mat: String,
        edad: Int,
        password: String
    ) {
        val intent = Intent(context, ModificarDatos::class.java)
        intent.putExtra("email", email)
        intent.putExtra("nombre", nombre)
        intent.putExtra("ap_pat", ap_pat)
        intent.putExtra("ap_Mat", ap_Mat)
        intent.putExtra("edad", edad)
        intent.putExtra("password", password)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}