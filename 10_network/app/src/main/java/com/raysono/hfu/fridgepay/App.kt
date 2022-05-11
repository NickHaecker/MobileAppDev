package com.raysono.hfu.fridgepay

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.database.AppDatabase
import com.raysono.hfu.fridgepay.data.network.WebService

/**
 * Main entry point into the application process.
 * Registered in the AndroidManifest.xml file.
 */
class App : Application() {

    private val userSettingsStore: DataStore<Preferences> by preferencesDataStore(name = "userSettings")

    override fun onCreate() {
        super.onCreate()
        userSettingsRepo = UserSettingsRepository(userSettingsStore)
        database = Room
            .databaseBuilder(this, AppDatabase::class.java, "app")
            .apply {
                if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
            }
            .build()

        // TODO
        //  instantiate web service using retrofit
        //  provide custom OkHttp client for logging
        //  add interceptor to set Authorization header
        //  add converter factory for JSON
        webService = object : WebService {}
    }

    companion object {
        /** Singleton [UserSettingsRepository] instance. */
        lateinit var userSettingsRepo: UserSettingsRepository
        lateinit var database: AppDatabase
        lateinit var webService: WebService
    }
}
