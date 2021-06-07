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
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConfiguracionBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view:View,savedInstanceState: Bundle?)
    {
        super.onViewCreated(view,savedInstanceState)
        var email=""
        arguments?.takeIf { it.containsKey("email") }?.apply {
            email=getString("email").toString()
        }
        var nombre=""
        arguments?.takeIf { it.containsKey("nombre") }?.apply {
            nombre=getString("nombre").toString()
        }
        var ap_pat=""
        arguments?.takeIf { it.containsKey("ap_pat") }?.apply {
            ap_pat=getString("ap_pat").toString()
        }
        var ap_Mat=""
        arguments?.takeIf { it.containsKey("ap_Mat") }?.apply {
            ap_Mat=getString("ap_Mat").toString()
        }
        var edad=0
        arguments?.takeIf { it.containsKey("edad") }?.apply {
            edad=getInt("edad")
        }
        binding.txtCfN.text=nombre+" "+ap_pat+" "+ap_Mat
        binding.txtCfC.text=email
        binding.txtCfE.text=edad.toString()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}