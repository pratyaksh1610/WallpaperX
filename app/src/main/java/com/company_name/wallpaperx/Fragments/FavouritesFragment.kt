package com.company_name.wallpaperx.Fragments


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.company_name.wallpaperx.Adapter.FavouritesAdapter
import com.company_name.wallpaperx.Adapter.OnClick
import com.company_name.wallpaperx.R
import com.company_name.wallpaperx.SaveImages.ImgEntity
import com.company_name.wallpaperx.SaveImages.ImgViewModel
import com.company_name.wallpaperx.ViewFavouritesImg
import com.company_name.wallpaperx.databinding.FragmentFavouritesBinding


class FavouritesFragment : Fragment(), OnClick {

    private val viewModel: ImgViewModel by viewModels()
    private lateinit var binding: FragmentFavouritesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavouritesBinding.inflate(layoutInflater, container, false)
//        Toast.makeText(requireContext(), "favourites fragment", Toast.LENGTH_SHORT).show()

        viewModel.getAllImages().observe(viewLifecycleOwner) { imgList ->

            binding.recyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.recyclerView.adapter = FavouritesAdapter(requireContext(), imgList, this)

        }

        return binding.root
    }


    override fun delImg(obj: ImgEntity) {
        viewModel.deleteImg(
            ImgEntity(
                obj.id,
                obj.url,
                obj.date
            )
        )
    }

    override fun onImgClick(obj: ImgEntity) {
        val i = Intent(requireContext(), ViewFavouritesImg::class.java)
        i.putExtra("url", obj.url)
        i.putExtra("date", obj.date)
        startActivity(i)
    }
}