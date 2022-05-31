package com.example.bettergithubmobile.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

/** Provides CRUD operations for user settings. */
class UserSettingsRepository(
    private val dataStore: DataStore<Preferences>,
) {
    /** Returns the current user settings. */
    suspend fun getSettings(): UserSettings = dataStore.data.map(::settingsFromPreferences).first()

    /** Emits the current user settings. */
    fun observeSettings(): Flow<UserSettings> = dataStore.data.map(::settingsFromPreferences)

    /**
     * Updates the current user settings and returns the new settings.
     * @param f Invoked with the current settings; The settings returned from this function will replace the current ones.
     */
    suspend fun updateSettings(f: (UserSettings) -> UserSettings): UserSettings {

        return settingsFromPreferences(prefs)
    }

    private fun settingsFromPreferences(prefs: Preferences) = UserSettings(

    )

    private companion object {
        private val KEY_CART_ID = stringPreferencesKey("shoppingCartId")
        private val KEY_CREDENTIALS = stringPreferencesKey("credentials")
        private val KEY_IS_LOGGING_IN = booleanPreferencesKey("isLoggingIn")
    }
}

/** Settings associated with the current user. */
data class UserSettings(
    /** The ID of the shopping cart used by this user. */
    val cartId: ShoppingCartId,

    /** The current login state. */
    val loginState: LoginState,
)

/** Describes the different states of a user logging in. */
sealed class LoginState {
    /** No user is logged in. */
    object LoggedOut : LoginState()

    /**
     * A user is currently in the process of logging in.
     * @param credentials Credentials to authenticate against the backend.
     */
    class LoggingIn(val credentials: String) : LoginState()

    /**
     * A user is currently logged in.
     * @param credentials Credentials to authenticate against the backend.
     */
    class LoggedIn(val credentials: String) : LoginState()
}