package com.company_name.wallpaperx

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.graphics.Color.green
import android.os.Build
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.company_name.wallpaperx.Fragments.CollectionsFragment
import com.company_name.wallpaperx.Fragments.FavouritesFragment
import com.company_name.wallpaperx.Fragments.HomeFragment
import com.company_name.wallpaperx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        Toast.makeText(applicationContext, "Reached main activity", Toast.LENGTH_SHORT).show()

        //initially mark home as default
        binding.homeImg.setColorFilter(getColor(R.color.neon))

        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, HomeFragment())
                .commit()
        }

        binding.home.setOnClickListener {
            binding.homeImg.setColorFilter(getColor(R.color.neon))
            binding.collectionsImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.favouritesImg.setColorFilter(getColor(R.color.bottomBarIconColor))

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }

        binding.collections.setOnClickListener {
            binding.homeImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.collectionsImg.setColorFilter(getColor(R.color.neon))
            binding.favouritesImg.setColorFilter(getColor(R.color.bottomBarIconColor))

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, CollectionsFragment())
                    .commit()
            }
        }

        binding.favourites.setOnClickListener {
            binding.homeImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.collectionsImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.favouritesImg.setColorFilter(getColor(R.color.neon))

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, FavouritesFragment())
                    .commit()
            }
        }

    }
}