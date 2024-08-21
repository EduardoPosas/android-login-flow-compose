package com.example.loginflowapp.datastore.data

import android.util.Log
import androidx.datastore.core.DataStore
import com.example.loginflowapp.datastore.UserPreferences
import com.example.loginflowapp.datastore.domain.UserPreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import java.io.IOException
import javax.inject.Inject

private const val TAG: String = "UserPreferencesRepo"

class UserPreferencesRepositoryImpl @Inject constructor(
    private val userPreferencesStore: DataStore<UserPreferences>
) : UserPreferencesRepository {
    /* Reading data
    * Proto DataStore exposes the data stored in a Flow<UserPreferences> using userPreferencesStore.data
    * While reading data, IOException is throw when an error occurs while reading data
    * we can handle errors using catch flow transformation and emitting default instance
    * */
    override val userPreferencesFlow: Flow<UserPreferences> = userPreferencesStore.data
        .catch { exception ->
            // dataStore.data throws an IOException when an error is encountered when reading data
            if (exception is IOException) {
                Log.e(TAG, "Error reading user preferences.", exception)
                emit(UserPreferences.getDefaultInstance())
            } else {
                throw exception
            }
        }

    /* Updating data
    * To write data, DataStore offers a suspending DataStore.updateData() function, where we get
    * as parameter the current state of UserPreferences.
    * updateData() updates the data transactionally in an atomic read-write-modify operation, the
    * coroutine completes once the data is persisted on disk.
    * */
    override suspend fun updateAccessToken(accessToken: String): Unit {
        userPreferencesStore.updateData { preferences ->
            preferences.toBuilder().setAccessToken(accessToken).build()
        }
    }
}