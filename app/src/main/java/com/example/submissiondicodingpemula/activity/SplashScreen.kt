package com.example.submissiondicodingpemula.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.submissiondicodingpemula.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


    }
}