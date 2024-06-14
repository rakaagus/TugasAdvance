package com.infinitelearning.tugasadvance.data.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.infinitelearning.tugasadvance.domain.model.SignInResult
import com.infinitelearning.tugasadvance.domain.model.UserData
import com.infinitelearning.tugasadvance.domain.preferences.AuthPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthDataStore @Inject constructor(
    private val authDataStore: DataStore<Preferences>
) : AuthPreferences {

    override val tokenUser: Preferences.Key<String> = stringPreferencesKey(TOKEN_USER_KEY)
    override val gmailUser: Preferences.Key<String> = stringPreferencesKey(EMAIL_USER_KEY)
    override val userName: Preferences.Key<String> = stringPreferencesKey(USER_NAME_KEY)
    override val profile: Preferences.Key<String> = stringPreferencesKey(PROFILE_USER_KEY)
    override val statusLogin: Preferences.Key<Boolean> = booleanPreferencesKey(STATUS_LOGIN_KEY)

    override fun getSessionUser(): Flow<UserData> {
        return authDataStore.data.map {
            UserData(
                userId = it[tokenUser] ?: "",
                username = it[userName] ?: "",
                email = it[gmailUser] ?: "",
                profilePicture = it[profile] ?: "",
            )
        }
    }

    override suspend fun saveSessionUser(session: UserData) {
        authDataStore.edit {
            it[tokenUser] = session.userId ?: ""
            it[userName] = session.username ?: ""
            it[gmailUser] = session.email ?: ""
            it[profile] = session.profilePicture ?: ""
        }
    }

    override suspend fun clearSessionUser() {
        authDataStore.edit {preferences ->
            preferences.clear()
        }
    }

    override fun isLoggedIn(): Flow<Boolean> = authDataStore.data.map{
        it[statusLogin] ?: false
    }

    override suspend fun setLoginStatus(isLogin: Boolean) {
        authDataStore.edit {
            it[statusLogin] = isLogin
        }
    }

    companion object {
        private const val TOKEN_USER_KEY = "tokenUserKey"
        private const val USER_NAME_KEY = "userNameKey"
        private const val EMAIL_USER_KEY = "emailUserKey"
        private const val PROFILE_USER_KEY = "profileUserKey"
        private const val STATUS_LOGIN_KEY = "status_login_key"
    }

}