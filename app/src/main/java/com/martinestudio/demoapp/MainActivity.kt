package com.martinestudio.demoapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.hide()
        val bottomMenu = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        bottomMenu.itemIconTintList = null

        replaceWithFragment(Home())

        bottomMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.itemHome -> replaceWithFragment(Home())
                R.id.itemDishes -> replaceWithFragment(Dishes())
                R.id.itemGallery -> replaceWithFragment(Gallery())
                R.id.itemProfile ->replaceWithFragment(Profile())
                else ->{

                }
            }
            true
        }



    }

    private fun replaceWithFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout, fragment)
        fragmentTransaction.commit()
    }
}