package com.company_name.wallpaperx

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.GridLayoutManager
import com.company_name.wallpaperx.Adapter.HomeAdapter
import com.company_name.wallpaperx.Adapter.OnClickImage
import com.company_name.wallpaperx.DataClass.PhotoByQuery
import com.company_name.wallpaperx.Retrofit.ApiInterface
import com.company_name.wallpaperx.Retrofit.RetrofitInstance
import com.company_name.wallpaperx.databinding.ActivityViewImageColorToneBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ViewImageColorTone : AppCompatActivity(), OnClickImage {
    private lateinit var binding: ActivityViewImageColorToneBinding
    private lateinit var retrofitBuilder: ApiInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewImageColorToneBinding.inflate(layoutInflater)
        setContentView(binding.root)

        retrofitBuilder = RetrofitInstance().initialiseRetrofitBuilderObject()

        val color = intent.getStringExtra("color").toString()
        Log.e("c", color)
        if (color == "Blue") {
            binding.img.setImageResource(R.drawable.blue)
        } else if (color == "Yellow") {
            binding.img.setImageResource(R.drawable.yellow)
        } else if (color == "Green") {
            binding.img.setImageResource(R.drawable.green)
        } else if (color == "Red") {
            binding.img.setImageResource(R.drawable.red)
        } else if (color == "Orange") {
            binding.img.setImageResource(R.drawable.orange)
        } else if (color == "Pink") {
            binding.img.setImageResource(R.drawable.pink)
        }

        binding.colorText.text = color

        getDataCategory(color)
        binding.back.setOnClickListener {
            onBackPressed()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    private fun getDataCategory(category: String) {
        val retrofitData = retrofitBuilder.getCat(category)

        retrofitData.enqueue(object : Callback<PhotoByQuery?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<PhotoByQuery?>, response: Response<PhotoByQuery?>) {
                Log.e("success by category", "success by category")
                val data = response.body()!!

                binding.recyclerView.layoutManager = GridLayoutManager(this@ViewImageColorTone, 2)

                val adapter =
                    HomeAdapter(this@ViewImageColorTone, data.results, this@ViewImageColorTone)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PhotoByQuery?>, t: Throwable) {
                Log.e("error by category", "error  by category")
            }
        })
    }

    override fun onClickImg(url: String, twitter: String, instagram: String, name: String) {
        val i = Intent(this@ViewImageColorTone, ViewImage::class.java)
        i.putExtra("url", url)
        i.putExtra("home", "home")
        i.putExtra("twitter",twitter)
        i.putExtra("instagram",instagram)
        i.putExtra("name",name)
        startActivity(i)
    }
}