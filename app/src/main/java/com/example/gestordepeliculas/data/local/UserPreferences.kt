package com.example.gestordepeliculas.data.local

import android.app.Application
import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserPreferences @Inject constructor(
    private val context: Application
) {
    companion object {
        const val USER_PREFERENCES = "User_Preferences"
        private val KEY_AUTH = stringPreferencesKey(name = "")
    }
    private val Context.dataStore by preferencesDataStore(name = USER_PREFERENCES)
    private val myDataStore : DataStore<Preferences> = context.dataStore

    val authToken: Flow<String?>
        get() = myDataStore.data
            .catch {exception ->
                if (exception is IOException) {
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preference ->
                preference[KEY_AUTH]
            }

    suspend fun saveAuthToken(authToken: String) {
        myDataStore.edit { preferences ->
            preferences[KEY_AUTH] = authToken
        }
    }

}