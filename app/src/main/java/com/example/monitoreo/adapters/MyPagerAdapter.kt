package com.example.monitoreo.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.monitoreo.fragments.Configuracion
import com.example.monitoreo.fragments.GestionarHijos

class MyPagerAdapter(fm: FragmentManager, var bundle: Bundle) : FragmentPagerAdapter(fm)
{
    override fun getItem(position: Int): Fragment
    {
        when (position)
        {
            0 ->
            {
                val fragment = GestionarHijos()
                fragment.arguments = Bundle().apply {
                    putString("email", bundle.getString("email"))
                }
                return fragment
            }
            else -> {
                val fragment = Configuracion()
                fragment.arguments = Bundle().apply {
                    putString("email", bundle.getString("email"))
                    putString("nombre", bundle.getString("nombre"))
                    putString("ap_pat", bundle.getString("ap_pat"))
                    putString("ap_Mat", bundle.getString("ap_Mat"))
                    putInt("edad", bundle.getInt("edad"))
                }
                return fragment
            }

        }
    }

    override fun getCount(): Int
    {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence?
    {
        return when (position)
        {
            0 -> "Gestionar Hijios"
            else ->
                return "Configuración"
        }
    }

}