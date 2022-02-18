package com.example.rocketapp

import android.app.Application
import com.bumptech.glide.Glide
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class RocketApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }

}