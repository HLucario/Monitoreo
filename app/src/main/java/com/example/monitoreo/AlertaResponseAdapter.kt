package com.example.monitoreo

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class AlertaResponseAdapter(var listAlerta: List<AlertaResponse>)
    : ListAdapter<AlertaResponse, AlertaResponseAdapter.AlertaResponseViewHolder>(AlertasResponseComparator()){

    override fun getItemCount():Int=listAlerta.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlertaResponseViewHolder {
        return AlertaResponseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: AlertaResponseViewHolder, position: Int) {
        val currentAlerta = listAlerta[position]

        holder.bind(currentAlerta)
    }

    class AlertaResponseViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private lateinit var context: Context
        private val alertas=view.findViewById<TextView>(R.id.alertas)
        fun bind(alerta: AlertaResponse) {
            alertas.text=alerta.id_alerta.toString()+" "+alerta.fecha+" "+alerta.texto
            alertas.setOnClickListener {
                context = itemView.context
                val intent = Intent(context,AgregarHijo::class.java)
                context.startActivity(intent)
            }
        }
        companion object{
            fun create(parent: ViewGroup):AlertaResponseViewHolder{
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.view_hijo,parent,false)
                return AlertaResponseViewHolder(view)
            }
        }
    }
    class AlertasResponseComparator : DiffUtil.ItemCallback<AlertaResponse>(){
        override fun areItemsTheSame(oldItem: AlertaResponse, newItem: AlertaResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: AlertaResponse, newItem: AlertaResponse): Boolean {
            return oldItem.id_alerta == newItem.id_alerta
        }
    }
}