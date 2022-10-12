package com.company_name.wallpaperx.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.company_name.wallpaperx.R

class ColorTone(private val context: Context, private val colorData: List<String>) :
    RecyclerView.Adapter<ColorTone.ColorToneViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorToneViewHolder {
        val viewHolder = LayoutInflater.from(context)
            .inflate(R.layout.color_tone_item_recycler_view, parent, false)
        return ColorToneViewHolder(viewHolder)
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ColorToneViewHolder, position: Int) {
        when (colorData[position]) {
            "Y" -> {
                holder.img.setBackgroundColor(Color.parseColor("#FFFB00"))
            }
            "G" -> {
                holder.img.setBackgroundColor(Color.parseColor("#00FF0D"))
            }
            "B" -> {
                holder.img.setBackgroundColor(Color.parseColor("#0037FF"))
            }
            "O" -> {
                holder.img.setBackgroundColor(Color.parseColor("#FF7300"))
            }
            "R" -> {
                holder.img.setBackgroundColor(Color.parseColor("#FF0000"))
            }
            "V" -> {
                holder.img.setBackgroundColor(Color.parseColor("#FF0070"))
            }
        }
    }

    override fun getItemCount(): Int {
        return colorData.size
    }

    class ColorToneViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)

    }
}