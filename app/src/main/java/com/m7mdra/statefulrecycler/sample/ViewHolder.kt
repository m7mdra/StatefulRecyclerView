package com.m7mdra.statefulrecycler.sample

import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder(val view:View) : RecyclerView.ViewHolder(view) {
    val text1 = itemView.findViewById<TextView>(android.R.id.text1)
}