package com.company_name.wallpaperx.SaveImages

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.IGNORE
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface ImgDao {

    @Insert(onConflict = REPLACE)
    fun insertImg(obj: ImgEntity)

    @Query("SELECT * FROM ImgEntity ORDER BY date DESC")
    fun getAllImages(): LiveData<List<ImgEntity>>

    @Delete
    fun deleteImg(obj: ImgEntity)


}
