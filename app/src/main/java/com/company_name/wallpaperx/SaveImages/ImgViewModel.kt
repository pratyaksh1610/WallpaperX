package com.company_name.wallpaperx.SaveImages

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ImgViewModel(application: Application) : AndroidViewModel(application) {


    private val repo: repository

    init {
        val dao = ImgDatabase.getDatabase(application).dao()
        repo = repository(dao)
    }

    fun insertImg(obj: ImgEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.insertImg(obj)
        }
    }

    fun getAllImages(): LiveData<List<ImgEntity>> {
        return repo.getAllImages()
    }

    fun deleteImg(obj: ImgEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            repo.deleteImg(obj)
        }
    }


}