package com.example.monitoreo.fragments

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import com.example.monitoreo.databinding.FragmentGestionarHijosBinding
import androidx.lifecycle.lifecycleScope
import com.example.monitoreo.adapters.HijoResponseAdapter
import com.example.monitoreo.api.RetrofitClient
import com.example.monitoreo.models.HijoResponse
import com.example.monitoreo.ui.AgregarHijo
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GestionarHijos : Fragment()
{
    private var _binding: FragmentGestionarHijosBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HijoResponseAdapter
    private lateinit var hijosR: List<HijoResponse>
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGestionarHijosBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        hijosR = emptyList()
        var email = ""
        arguments?.takeIf { it.containsKey("email") }?.apply {
            email = getString("email").toString()
        }
        super.onViewCreated(view, savedInstanceState)
        val recycler = binding.hijos
        lifecycleScope.launch {
            RetrofitClient.instance.listarHijos(email)
                .enqueue(object : Callback<List<HijoResponse>> {
                    override fun onResponse(
                        call: Call<List<HijoResponse>>,
                        response: Response<List<HijoResponse>>
                    ) {
                        if (response.code() == 200) {
                            hijosR = response.body()!!
                            adapter = HijoResponseAdapter(hijosR)
                            recycler.adapter = adapter

                        } else {
                            hijosR = emptyList()
                        }
                    }

                    override fun onFailure(call: Call<List<HijoResponse>>, t: Throwable) {
                        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
                        hijosR = emptyList()
                    }
                })
        }
        binding.btnAgregar.setOnClickListener {
            val intent = Intent(context, AgregarHijo::class.java)
            intent.putExtra("email",email)
            startActivity(intent)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}