package com.example.projectakhirpemula

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class AboutUsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)
        // Enable back button in ActionBar
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = "About Us"
        }
    }
    // Handle back button click
    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}