package com.example.monitoreo.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.monitoreo.databinding.FragmentGestionarHijosBinding
import androidx.lifecycle.lifecycleScope
import com.example.monitoreo.*
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestionarHijos : Fragment() {
    private var _binding: FragmentGestionarHijosBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HijoAdapter
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?
        ): View? {
        _binding = FragmentGestionarHijosBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view:View,savedInstanceState: Bundle?)
    {
        super.onViewCreated(view,savedInstanceState)
        val recycler = binding.hijos
        val hijo1=Hijo(1,"Hector","Hernandez","Martínez",10,"00000ABB28FC","luis.shdw.gen@gmail.com")
        val hijo2=Hijo(2,"David","Hernandez","Martínez",15,"00000ABB28FD","luis.shdw.gen@gmail.com")
        val hijos= listOf<Hijo>(hijo1,hijo2)
        lifecycleScope.launch {
            RetrofitClient.instance.listarHijos("luis.shdw.gen@gmail.com")
                .enqueue(object: Callback<HijoResponse> {
                    override fun onResponse(call: Call<HijoResponse>, response: Response<HijoResponse>) {
                        Log.d("PRUEBA","HECHO")
                        val defaultResponse=response.body()!!
                        //hijos = defaultResponse.hijos
                    }
                    override fun onFailure(call: Call<HijoResponse>, t: Throwable) {
                        Log.d("PRUEBA","FALLÓ")
                        //hijos= emptyList()
                    }
                })
            adapter = HijoAdapter(hijos)
            recycler.adapter = adapter
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}