package com.company_name.wallpaperx.Adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.company_name.wallpaperx.DataClass.Photo
import com.company_name.wallpaperx.Fragments.HomeFragment
import com.company_name.wallpaperx.R

class HomeAdapter(
    private val context: Context, private val dataFromApi: List<Photo>,
    private val listener: OnClickImage
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val viewHolder = LayoutInflater.from(context)
            .inflate(R.layout.recycler_view_home_fragment_featured_item_view, parent, false)
        return HomeViewHolder(viewHolder)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val curr = dataFromApi[position]
        Glide.with(context).load(curr.urls.full).into(holder.img)
        holder.img.setOnClickListener {
            curr.user.social.twitter_username.let {
                curr.user.social.instagram_username.let {
                    listener.onClickImg(
                        curr.urls.full, curr.user.username,
                        curr.user.username, curr.user.name
                    )
                }
            }
        }

//        working below code
//        Log.e("insta",curr.user.social.instagram_username)


    }

    override fun getItemCount(): Int {
        return dataFromApi.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.img)

    }


}

interface OnClickImage {
    fun onClickImg(url: String, twitter: String, instagram: String, name: String)
}