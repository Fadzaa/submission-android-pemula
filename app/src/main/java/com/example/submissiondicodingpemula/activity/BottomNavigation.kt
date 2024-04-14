package com.example.submissiondicodingpemula.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.fragment.app.Fragment
import com.example.submissiondicodingpemula.R
import com.example.submissiondicodingpemula.databinding.ActivityBottomNavigationBinding
import com.example.submissiondicodingpemula.fragment.AboutFragment
import com.example.submissiondicodingpemula.fragment.HomeFragment
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener


class BottomNavigation : AppCompatActivity(), OnItemSelectedListener{

    private lateinit var binding: ActivityBottomNavigationBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        installSplashScreen()

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.bottomNavigationView.setOnItemSelectedListener(this)

        replaceFragment(HomeFragment())

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_item_home -> replaceFragment(HomeFragment())
            R.id.menu_item_about -> replaceFragment(AboutFragment())
        }
        return true
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, fragment)
        fragmentTransaction.commit()
    }
}