package com.infinitelearning.tugasadvance.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import javax.inject.Inject
import javax.inject.Singleton
import com.infinitelearning.tugasadvance.domain.preferences.AppPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Singleton
class AppDataStore @Inject constructor(private val appDataStore: DataStore<Preferences>) : AppPreferences{
    override val statusOnboarding = booleanPreferencesKey(STATUS_ONBOARDING_KEY)

    override fun getStatusOnboardingUser(): Flow<Boolean> {
        return appDataStore.data.map {
            it[statusOnboarding] ?: false
        }
    }

    override suspend fun saveStatusOnboardingUser(status: Boolean) {
        appDataStore.edit {
            it[this.statusOnboarding] = status
        }
    }

    companion object{
        private const val STATUS_ONBOARDING_KEY = "statusOnboarding"
    }
}