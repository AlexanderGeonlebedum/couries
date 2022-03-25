package org.wit.couries.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.wit.couries.R
import org.wit.couries.databinding.CardCouriesBinding
import org.wit.couries.models.CouriesModel

interface CouriesClickListener {
    fun onCouriesClick(couries: CouriesModel)
}

class CouriesAdapter(private var couries: List<CouriesModel>,
                     private val listener: CouriesClickListener
)
    : RecyclerView.Adapter<CouriesAdapter.MainHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val binding = CardCouriesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)

        return MainHolder(binding)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        val couries = couries[holder.adapterPosition]
        holder.bind(couries,listener)
    }

    override fun getItemCount(): Int = couries.size

    inner class MainHolder(val binding : CardCouriesBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(couries: CouriesModel, listener: CouriesClickListener) {
            binding.couries = couries
            binding.imageIcon.setImageResource(R.drawable.menu_client_icon)
            binding.root.setOnClickListener { listener.onCouriesClick(couries) }
            binding.executePendingBindings()
        }
    }
}