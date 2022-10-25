package com.company_name.wallpaperx.SaveImages

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = [ImgEntity::class], version = 1, exportSchema = false)
public abstract class ImgDatabase : RoomDatabase() {

    abstract fun dao(): ImgDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: ImgDatabase? = null

        fun getDatabase(context: Context): ImgDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ImgDatabase::class.java,
                    "img_database"
                ).allowMainThreadQueries().build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}