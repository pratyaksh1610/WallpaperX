package com.company_name.wallpaperx.SaveImages

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ImgEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val url:String,
    val date:String
)
