package com.company_name.wallpaperx

import android.app.WallpaperManager
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.company_name.wallpaperx.databinding.ActivityViewFavouritesImgBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream

class ViewFavouritesImg : AppCompatActivity() {
    private lateinit var binding: ActivityViewFavouritesImgBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewFavouritesImgBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val url = intent.getStringExtra("url").toString()
        val date = intent.getStringExtra("date").toString()
        Glide.with(this).load(url).into(binding.img)
        binding.tv.text = getString(R.string.saved_on, date)


        Log.e("url", url)
        Log.e("date", date)

        binding.download.setOnClickListener {
            saveImg(binding.img)
        }

        binding.setAsBackground.setOnClickListener {

            val bitmap = getBitmapOfImgView(binding.img)
            val wallpaperManager = WallpaperManager.getInstance(this)

            try {
                wallpaperManager.setBitmap(bitmap)
                Toast.makeText(this, "Wallpaper set successfully \uD83C\uDF89", Toast.LENGTH_SHORT)
                    .show()
            } catch (ex: IOException) {
                Toast.makeText(this, "Error in setting wallpaper", Toast.LENGTH_SHORT).show()
                ex.printStackTrace()
            }
            // creating the instance of the WallpaperManager
            // creating the instance of the WallpaperManager
            // handle the set wallpaper button to set the desired wallpaper for home screen
            // handle the set wallpaper button to set the desired wallpaper for home screen
        }
    }


    private fun saveImg(view: View) {
        // on click of this btnSve button it will capture
        // screenshot of imgView and save it to gallery
        // extract the bitmap of the imgView using
        // getBitmapOfImgView
        val bitmap = getBitmapOfImgView(view)
        // if bitmap is not null then
        // save it to gallery
        if (bitmap != null) {
            saveImgViewToStorage(bitmap)
        }
    }

    private fun getBitmapOfImgView(v: View): Bitmap? {
        // create a bitmap object
        var screenshotOfImgView: Bitmap? = null
        try {
            // inflate screenshot object
            // with Bitmap.createBitmap it
            // requires three parameters
            // width and height of the view and
            // the background color
            screenshotOfImgView =
                Bitmap.createBitmap(v.measuredWidth, v.measuredHeight, Bitmap.Config.ARGB_8888)
            // Now draw this bitmap on a canvas
            val canvas = Canvas(screenshotOfImgView)
            v.draw(canvas)
        } catch (e: Exception) {
            Log.e("Error", "Failed to capture ! " + e.message)
        }
        // return the bitmap
        return screenshotOfImgView
    }


    // this method saves the image to gallery
    private fun saveImgViewToStorage(bitmap: Bitmap) {
        // Generating a file name
        val filename = "${System.currentTimeMillis()}.jpg"

        // Output stream
        var fos: OutputStream? = null

        // For devices running android >= Q
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            // getting the contentResolver
            this.contentResolver?.also { resolver ->

                // Content resolver will process the content values
                val contentValues = ContentValues().apply {

                    // putting file information in content values
                    put(MediaStore.MediaColumns.DISPLAY_NAME, filename)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }

                // Inserting the contentValues to
                // contentResolver and getting the Uri
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)

                // Opening an output stream with the Uri that we got
                fos = imageUri?.let { resolver.openOutputStream(it) }
            }
        } else {
            // These for devices running on android < Q
            val imagesDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imagesDir, filename)
            fos = FileOutputStream(image)
        }

        fos?.use {
            // Finally writing the bitmap to the output stream that we opened
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(this, "Saved to Gallery ! \uD83C\uDF89", Toast.LENGTH_SHORT).show()
        }
    }
}
