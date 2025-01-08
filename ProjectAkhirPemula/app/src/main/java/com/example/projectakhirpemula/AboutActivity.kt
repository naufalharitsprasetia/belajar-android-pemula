package com.example.projectakhirpemula

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        // Enable back button in ActionBar
        supportActionBar?.apply {
            setHomeAsUpIndicator(R.drawable.ic_back) // Set drawable custom
            setDisplayHomeAsUpEnabled(true)
            title = "About"
        }
    }
    // Handle back button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}