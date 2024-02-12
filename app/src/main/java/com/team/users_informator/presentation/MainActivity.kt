package com.team.users_informator.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.team.users_informator.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.main_fragment_container, FragmentMain.newInstance())
            .addToBackStack(FragmentMain.TAG_FRAGMENT)
            .commit()
    }
}