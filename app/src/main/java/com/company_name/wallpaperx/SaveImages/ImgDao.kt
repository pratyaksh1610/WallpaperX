package com.company_name.wallpaperx.SaveImages

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ImgDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImg(obj: ImgEntity)

    @Query("SELECT * FROM ImgEntity ORDER BY date DESC")
    fun getAllImages(): LiveData<List<ImgEntity>>

    @Delete
    suspend fun deleteImg(obj: ImgEntity)


}
