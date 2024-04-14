package com.company_name.wallpaperx

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
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

//        Toast.makeText(applicationContext, "Reached main activity", Toast.LENGTH_SHORT).show()

        //initially mark home as default and open home fragment
        binding.homeImg.setColorFilter(getColor(R.color.neon))

        // home fragment by default
        openRequiredFragment(HomeFragment(), HomeFragment::class.java.name)

        binding.home.setOnClickListener {
            // change bottom navigation icon colours
            binding.homeImg.setColorFilter(getColor(R.color.neon))
            binding.collectionsImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.favouritesImg.setColorFilter(getColor(R.color.bottomBarIconColor))

            openRequiredFragment(HomeFragment(), HomeFragment::class.java.name)
        }

        binding.collections.setOnClickListener {
            binding.homeImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.collectionsImg.setColorFilter(getColor(R.color.neon))
            binding.favouritesImg.setColorFilter(getColor(R.color.bottomBarIconColor))

            openRequiredFragment(CollectionsFragment(), CollectionsFragment::class.java.name)
        }

        binding.favourites.setOnClickListener {
            binding.homeImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.collectionsImg.setColorFilter(getColor(R.color.bottomBarIconColor))
            binding.favouritesImg.setColorFilter(getColor(R.color.neon))

            openRequiredFragment(FavouritesFragment(), FavouritesFragment::class.java.name)
        }

    }

    private fun openRequiredFragment(fragment: Fragment, tag: String) {
        if (fragment is HomeFragment) {
            supportFragmentManager.popBackStack(tag, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment)
            addToBackStack(tag)
                .commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }
}