package com.raysono.hfu.fridgepay

import android.app.Application
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.raysono.hfu.fridgepay.data.UserSettingsRepository
import com.raysono.hfu.fridgepay.data.database.AppDatabase
import com.raysono.hfu.fridgepay.data.productRepo
import com.raysono.hfu.fridgepay.domain.AddDemoProductsUseCase
import com.raysono.hfu.fridgepay.domain.InitializeShoppingCartIdUseCase
import kotlinx.coroutines.runBlocking

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

        /*
         In a real app we should never use runBlocking {}. Especially not on app start up.
         However, we would need to refactor the ProductsRepository to use Flow. Therefore, we accept this hack for now. After all, it is
         just a demo app.
         The real solution would be to launch a coroutine in the app scope:
         private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
         scope.launch {  }
         */
        runBlocking {
            InitializeShoppingCartIdUseCase(userSettingsRepo)()
            AddDemoProductsUseCase(productRepo)()
        }
    }

    companion object {
        /** Singleton [UserSettingsRepository] instance. */
        lateinit var userSettingsRepo: UserSettingsRepository
        lateinit var database: AppDatabase
    }
}
