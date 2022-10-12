package com.company_name.wallpaperx.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.company_name.wallpaperx.Adapter.ColorTone
import com.company_name.wallpaperx.Adapter.HomeAdapter
import com.company_name.wallpaperx.DataClass.Photo
import com.company_name.wallpaperx.Retrofit.ApiInterface
import com.company_name.wallpaperx.Retrofit.RetrofitInstance
import com.company_name.wallpaperx.databinding.FragmentCollectionsBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CollectionsFragment : Fragment() {
    private lateinit var binding: FragmentCollectionsBinding
    private lateinit var retrofitBuilder: ApiInterface
    private var colorTone = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(requireContext(), "collection fragment", Toast.LENGTH_SHORT).show()
        binding = FragmentCollectionsBinding.inflate(layoutInflater, container, false)

        retrofitBuilder = RetrofitInstance().initialiseRetrofitBuilderObject()

        passDataToColorToneAdapterForImageColor()
        showBestOfMonth()

        return binding.root
    }

    private fun showBestOfMonth() {
        val retrofit = retrofitBuilder.getPhotosBestOfMonth()

        retrofit.enqueue(object : Callback<List<Photo>?> {
            override fun onResponse(call: Call<List<Photo>?>, response: Response<List<Photo>?>) {
                val response = response.body()!!

                binding.recyclerViewBestOfMonth.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                val adapter = HomeAdapter(requireContext(), response)
                binding.recyclerViewBestOfMonth.adapter = adapter

            }

            override fun onFailure(call: Call<List<Photo>?>, t: Throwable) {
                Log.e("best of month", "error")
            }
        })

    }

    private fun passDataToColorToneAdapterForImageColor() {
        colorTone.add("Y")
        colorTone.add("G")
        colorTone.add("B")
        colorTone.add("O")
        colorTone.add("R")
        colorTone.add("V")
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        val adapter = ColorTone(requireContext(), colorTone)
        binding.recyclerView.adapter = adapter

    }

    private fun getDataColorTone() {


    }

}