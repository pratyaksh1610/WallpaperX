package com.company_name.wallpaperx.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.company_name.wallpaperx.R

class FavouritesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(requireContext(), "favourites fragment", Toast.LENGTH_SHORT).show()

        return inflater.inflate(R.layout.fragment_favourites, container, false)
    }


}