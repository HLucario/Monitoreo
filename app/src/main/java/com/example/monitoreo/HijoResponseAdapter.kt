package com.example.monitoreo

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class HijoResponseAdapter(var listHijo: List<HijoResponse>)
    : ListAdapter<HijoResponse, HijoResponseAdapter.HijoResponseViewHolder>(HijosResponseComparator()){

    override fun getItemCount():Int=listHijo.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HijoResponseViewHolder {
        return HijoResponseViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: HijoResponseViewHolder, position: Int) {
        val currentHijo = listHijo[position]

        holder.bind(currentHijo)
    }

    class HijoResponseViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private lateinit var context: Context
        private val e_hijo=view.findViewById<TextView>(R.id.e_hijo)
        fun bind(hijo: HijoResponse) {
            e_hijo.text=hijo.nombre+" "+hijo.ap_pat+" "+hijo.ap_Mat+"|"+hijo.dispositivo
            e_hijo.setOnClickListener {
                context = itemView.context
                val intent = Intent(context, HijoInformacion::class.java)
                intent.putExtra("id",hijo.id)
                intent.putExtra("nombre",hijo.nombre)
                intent.putExtra("ap_pat",hijo.ap_pat)
                intent.putExtra("ap_Mat",hijo.ap_Mat)
                intent.putExtra("edad",hijo.edad)
                intent.putExtra("dispositivo",hijo.dispositivo)
                intent.putExtra("tutor_email",hijo.tutor_email)
                context.startActivity(intent)
            }
        }
        companion object{
            fun create(parent: ViewGroup):HijoResponseViewHolder{
                val view = LayoutInflater
                    .from(parent.context)
                    .inflate(R.layout.view_hijo,parent,false)
                return HijoResponseViewHolder(view)
            }
        }
    }
    class HijosResponseComparator : DiffUtil.ItemCallback<HijoResponse>(){
        override fun areItemsTheSame(oldItem: HijoResponse, newItem: HijoResponse): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: HijoResponse, newItem: HijoResponse): Boolean {
            return oldItem.nombre == newItem.nombre
        }
    }
}