package com.example.monitoreo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HijoAdapter(var listHijo: List<Hijo>)
    : ListAdapter<Hijo,HijoAdapter.HijoViewHolder>(HijosComparator()){

    override fun getItemCount():Int=listHijo.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HijoViewHolder {
        return HijoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HijoViewHolder, position: Int) {
        val currentHijo = listHijo[position]
        holder.bind(currentHijo)
    }

    class HijoViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val e_hijo=view.findViewById<TextView>(R.id.e_hijo)
        fun bind(hijo: Hijo) {
            e_hijo.text=hijo.nombre+" "+hijo.ap_paterno+" "+hijo.ap_materno
        }
        companion object{
            fun create(parent: ViewGroup):HijoViewHolder{
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.view_hijo,parent,false)
                return HijoViewHolder(view)
            }
        }
    }
    class HijosComparator : DiffUtil.ItemCallback<Hijo>(){
        override fun areItemsTheSame(oldItem: Hijo, newItem: Hijo): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Hijo, newItem: Hijo): Boolean {
            return oldItem.nombre == newItem.nombre
        }
    }
}