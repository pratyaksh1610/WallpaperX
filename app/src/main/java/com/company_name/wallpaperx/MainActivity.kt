package com.company_name.wallpaperx

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils.replace
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.company_name.wallpaperx.Fragments.CollectionsFragment
import com.company_name.wallpaperx.Fragments.FavouritesFragment
import com.company_name.wallpaperx.Fragments.HomeFragment
import com.company_name.wallpaperx.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("CommitTransaction")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Toast.makeText(applicationContext, "Reached main activity", Toast.LENGTH_SHORT).show()

        //initially mark home as default
        binding.homeImg.setColorFilter(Color.parseColor("#ffffff"))

        binding.home.setOnClickListener {
            binding.homeImg.setColorFilter(Color.parseColor("#ffffff"))
            binding.collectionsImg.setColorFilter(Color.parseColor("#3c3c3c"))
            binding.favouritesImg.setColorFilter(Color.parseColor("#3c3c3c"))

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, HomeFragment())
                    .commit()
            }
        }

        binding.collections.setOnClickListener {
            binding.homeImg.setColorFilter(Color.parseColor("#3c3c3c"))
            binding.collectionsImg.setColorFilter(Color.parseColor("#ffffff"))
            binding.favouritesImg.setColorFilter(Color.parseColor("#3c3c3c"))

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, CollectionsFragment())
                    .commit()
            }
        }

        binding.favourites.setOnClickListener {
            binding.homeImg.setColorFilter(Color.parseColor("#3c3c3c"))
            binding.collectionsImg.setColorFilter(Color.parseColor("#3c3c3c"))
            binding.favouritesImg.setColorFilter(Color.parseColor("#ffffff"))

            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragment_container, FavouritesFragment())
                    .commit()
            }
        }

    }

    override fun onBackPressed() {
        val a = Intent(Intent.ACTION_MAIN)
        a.addCategory(Intent.CATEGORY_HOME)
        a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(a)
    }
}