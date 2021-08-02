package com.haidv.customnavigationdrawer.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.haidv.customnavigationdrawer.R
import com.haidv.customnavigationdrawer.models.DailyActivity

class DailyActivityAdapter(private val list: MutableList<DailyActivity>) :
    RecyclerView.Adapter<DailyActivityAdapter.ViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DailyActivityAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_daily_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binItems(list, position)
        holder.itemView.width
    }

    override fun getItemCount(): Int {
        if (list.isNotEmpty()) {
            return list.size
        }
        return 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("ResourceAsColor", "SetTextI18n")
        fun binItems(listDay: List<DailyActivity>, position: Int) {
            val textViewText = itemView.findViewById<TextView>(R.id.textViewText)
            val textViewNumber = itemView.findViewById<TextView>(R.id.textViewNumber)

            textViewText.text = listDay[position].text
            textViewNumber.text = listDay[position].number
        }
    }
}