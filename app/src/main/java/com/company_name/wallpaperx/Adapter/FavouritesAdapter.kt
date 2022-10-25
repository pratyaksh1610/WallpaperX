package com.company_name.wallpaperx.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company_name.wallpaperx.R
import com.company_name.wallpaperx.SaveImages.ImgEntity

class FavouritesAdapter(
    private val context: Context,
    private val data: List<ImgEntity>,
    private val listener: OnClick
) :
    RecyclerView.Adapter<FavouritesAdapter.FavouritesViewHolder>() {


    class FavouritesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)
        val love: ImageView = itemView.findViewById(R.id.love)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        val viewHolder =
            LayoutInflater.from(context).inflate(R.layout.favourites_item_view, parent, false)
        return FavouritesViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val curr = data[position]
        Glide.with(context).load(curr.url).into(holder.img)
        Glide.with(context).load(R.drawable.love).into(holder.love)
        holder.love.setOnClickListener {
            listener.delImg(curr)
        }
        holder.img.setOnClickListener{
            listener.onImgClick(curr)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}

interface OnClick {
    fun delImg(obj: ImgEntity)
    fun onImgClick(obj:ImgEntity)
}