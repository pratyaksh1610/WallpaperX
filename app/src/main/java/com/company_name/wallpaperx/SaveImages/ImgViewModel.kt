package com.company_name.wallpaperx.SaveImages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class ImgViewModel(application: Application):AndroidViewModel(application) {


    private val repo: repository

    init {
        val dao = ImgDatabase.getDatabase(application).dao()
        repo = repository(dao)
    }

    fun insertImg(obj: ImgEntity) {
        repo.insertImg(obj)
    }

    fun getAllImages(): LiveData<List<ImgEntity>> {
        return repo.getAllImages()
    }

    fun deleteImg(obj: ImgEntity) {
        repo.deleteImg(obj)
    }


}