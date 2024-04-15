package com.company_name.wallpaperx.SaveImages

import androidx.lifecycle.LiveData
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

class repository(private val dao: ImgDao) {


    suspend fun insertImg(obj: ImgEntity) {
        dao.insertImg(obj)
    }

    fun getAllImages(): LiveData<List<ImgEntity>> {
        return dao.getAllImages()
    }

    suspend fun deleteImg(obj: ImgEntity) {
        dao.deleteImg(obj)
    }


}