package com.infinitelearning.tugasadvance.domain.preferences

import androidx.datastore.preferences.core.Preferences
import kotlinx.coroutines.flow.Flow

interface AppPreferences {

    val statusOnboarding : Preferences.Key<Boolean>

    fun getStatusOnboardingUser(): Flow<Boolean>

    suspend fun saveStatusOnboardingUser(status: Boolean)

}