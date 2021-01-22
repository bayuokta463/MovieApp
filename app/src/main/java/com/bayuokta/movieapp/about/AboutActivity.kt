package com.bayuokta.movieapp.about

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bayuokta.movieapp.R
import com.bayuokta.movieapp.databinding.ActivityAboutBinding

import com.bumptech.glide.Glide

class AboutActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAboutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load(getString(R.string.author_profile))
            .into(binding.ivMe)

        binding.ibBack.setOnClickListener {
            onBackPressed()
        }


    }
}