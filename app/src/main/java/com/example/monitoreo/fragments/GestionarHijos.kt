package com.example.monitoreo.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.Menu
import androidx.fragment.app.Fragment
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