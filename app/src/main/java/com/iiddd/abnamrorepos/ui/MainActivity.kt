package com.iiddd.abnamrorepos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iiddd.abnamrorepos.R
import com.iiddd.abnamrorepos.databinding.ActivityMainBinding
import com.iiddd.abnamrorepos.navigator.AppNavigator
import com.iiddd.abnamrorepos.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.REPOS)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}