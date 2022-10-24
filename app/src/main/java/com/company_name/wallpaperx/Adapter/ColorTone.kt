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


class ColorTone(
    private val context: Context, private val colorData: List<String>,
    private val listener: OnClickColorTone
) :
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
                holder.img.setImageResource(R.drawable.yellow)
            }
            "G" -> {
                holder.img.setImageResource(R.drawable.green)
            }
            "B" -> {
                holder.img.setImageResource(R.drawable.blue)
            }
            "O" -> {
                holder.img.setImageResource(R.drawable.orange)
            }
            "R" -> {
                holder.img.setImageResource(R.drawable.red)
            }
            "P" -> {
                holder.img.setImageResource(R.drawable.pink)
            }
        }
        holder.img.setOnClickListener {
            when (position) {
                0 -> {
                    listener.onClickColorTone("Yellow")
                }
                1 -> {
                    listener.onClickColorTone("Green")
                }
                2 -> {
                    listener.onClickColorTone("Blue")
                }
                3 -> {
                    listener.onClickColorTone("Orange")
                }
                4 -> {
                    listener.onClickColorTone("Red")
                }
                5 -> {
                    listener.onClickColorTone("Pink")
                }
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

interface OnClickColorTone {
    fun onClickColorTone(color: String)
}