package com.example.submissiondicodingpemula.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.submissiondicodingpemula.databinding.ActivityDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)

        setContentView(binding.root)


    }
}