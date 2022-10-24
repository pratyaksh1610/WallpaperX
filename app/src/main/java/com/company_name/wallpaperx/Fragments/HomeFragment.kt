package com.company_name.wallpaperx.Fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import com.company_name.wallpaperx.Adapter.HomeAdapter
import com.company_name.wallpaperx.Adapter.OnClickImage
import com.company_name.wallpaperx.DataClass.Photo
import com.company_name.wallpaperx.DataClass.PhotoByQuery
import com.company_name.wallpaperx.DataClass.results
import com.company_name.wallpaperx.Retrofit.ApiInterface
import com.company_name.wallpaperx.Retrofit.RetrofitInstance
import com.company_name.wallpaperx.ViewImage
import com.company_name.wallpaperx.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(), OnClickImage {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var retrofitBuilder: ApiInterface

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        Toast.makeText(requireContext(), "home fragment", Toast.LENGTH_SHORT).show()

        retrofitBuilder = RetrofitInstance().initialiseRetrofitBuilderObject()

        //for images under featured section initially
        getDataHomeFragment()

        //search images from search text field
        binding.search.setOnClickListener {
            if (binding.searchText.text.isEmpty()) {
                Toast.makeText(requireContext(), "Empty field", Toast.LENGTH_SHORT).show()
            } else {
                getDataCategory(binding.searchText.text.toString())
            }
        }

        //search from keyboard also enabling ime in searchText id to actionSend
        binding.searchText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                if (binding.searchText.text.isEmpty()) {
                    Toast.makeText(requireContext(), "Empty field", Toast.LENGTH_SHORT).show()
                } else {
                    getDataCategory(binding.searchText.text.toString().trim())
                }
            }
            true
        }

        return binding.root
    }

    private fun getDataCategory(category: String) {
        val retrofitData = retrofitBuilder.getCat(category)

        retrofitData.enqueue(object : Callback<PhotoByQuery?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<PhotoByQuery?>, response: Response<PhotoByQuery?>) {
                Log.e("success by category", "success by category")
                val data = response.body()!!
                if (data.results.isEmpty()) {
                    Toast.makeText(requireContext(), "No results found", Toast.LENGTH_SHORT).show()
                }

                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
                val adapter = HomeAdapter(requireContext(), data.results, this@HomeFragment)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<PhotoByQuery?>, t: Throwable) {
                Log.e("error by category", "error  by category")
            }
        })
    }

    private fun getDataHomeFragment() {
        val retrofitData = retrofitBuilder.getPhotos()

        retrofitData.enqueue(object : Callback<List<Photo>?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<List<Photo>?>, response: Response<List<Photo>?>) {
                Log.e("success by featured", "success by featured")

                val data = response.body()!!
                binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)

                val adapter = HomeAdapter(requireContext(), data, this@HomeFragment)
                binding.recyclerView.adapter = adapter
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<List<Photo>?>, t: Throwable) {
                Log.e("error by featured", "error by featured")
            }
        })
    }

    override fun onClickImg(url: String, twitter: String, instagram: String, name: String) {
        val i = Intent(requireContext(), ViewImage::class.java)
        i.putExtra("url", url)
        i.putExtra("home", "home")
        i.putExtra("twitter",twitter)
        i.putExtra("instagram",instagram)
        i.putExtra("name",name)
        startActivity(i)
    }

}