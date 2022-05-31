package com.example.bettergithubmobile

import android.app.Application

class App : Application(){
    override fun onCreate() {
        super.onCreate()

    }
    companion object{
        lateinit var userSettingsRepo: UserSettingsRepository
    }
}