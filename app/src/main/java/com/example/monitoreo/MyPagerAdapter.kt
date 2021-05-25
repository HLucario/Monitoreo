package com.example.monitoreo

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.monitoreo.fragments.Configuracion
import com.example.monitoreo.fragments.GestionarHijos

class MyPagerAdapter (fm:FragmentManager):FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0-> GestionarHijos()
            else-> return Configuracion()
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