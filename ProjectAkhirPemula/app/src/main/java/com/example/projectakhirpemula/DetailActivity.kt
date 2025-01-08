package com.example.projectakhirpemula

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import java.io.File
import java.io.FileOutputStream

class DetailActivity : AppCompatActivity() {
    private lateinit var name : String
    private lateinit var description : String
    private lateinit var photo : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Ambil data dari Intent
        name = intent.getStringExtra(EXTRA_NAME).toString()
        description = intent.getStringExtra(EXTRA_DESCRIPTION).toString()
        photo = intent.getStringExtra(EXTRA_PHOTO).toString()

        // Temukan view
        val tvName: TextView = findViewById(R.id.tv_name)
        val tvDescription: TextView = findViewById(R.id.tv_description)
        val ivPhoto: ImageView = findViewById(R.id.iv_photo)

        // Set data ke view
        tvName.text = name
        tvDescription.text = description
        Glide.with(this)
            .load(photo)
            .into(ivPhoto)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_share -> {
                shareHero() // Panggil fungsi share
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // Fungsi untuk berbagi data
    private fun shareHero() {
        val heroName = name
        val heroDescription = description
        Log.d("DetailActivity", "Hero Name: $heroName")
        // Get image as Bitmap
        val imageView = findViewById<ImageView>(R.id.iv_photo)
        imageView.isDrawingCacheEnabled = true
        val bitmap = imageView.drawingCache

        // Save Bitmap to File
        val file = File(cacheDir, "shared_image.png")
        FileOutputStream(file).use {
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, it)
        }

        // Get URI using FileProvider
        val imageUri: Uri = FileProvider.getUriForFile(
            this,
            "$packageName.provider",
            file
        )

        // Create Share Intent
        val shareIntent = Intent().apply {
            action = Intent.ACTION_SEND
            type = "image/png"
            putExtra(Intent.EXTRA_STREAM, imageUri)
            putExtra(Intent.EXTRA_TEXT, "Hero Name: $heroName\n\nDescription:\n$heroDescription")
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
        startActivity(Intent.createChooser(shareIntent, "Share Hero via"))
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_PHOTO = "extra_photo"
    }

}