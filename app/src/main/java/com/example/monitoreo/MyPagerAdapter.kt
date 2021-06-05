package com.example.monitoreo

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.monitoreo.fragments.Configuracion
import com.example.monitoreo.fragments.GestionarHijos

class MyPagerAdapter (fm:FragmentManager, bundle:Bundle):FragmentPagerAdapter(fm){

    override fun getItem(position: Int): Fragment {
        when(position){
            0->
            {
                GestionarHijos().arguments = Bundle().apply {
                    putString("email",bundle.)
                }
                return GestionarHijos()
            }
            else->
                return Configuracion()
        }
    }
    override fun getCount():Int{
        return 2
    }
    override fun getPageTitle(position: Int):CharSequence?{
        return when (position){
            0->"Gestionar Hijios"
            else->
                return "Configuración"
        }
    }
}