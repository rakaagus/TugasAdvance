package com.infinitelearning.tugasadvance.domain.preferences

import androidx.datastore.preferences.core.Preferences
import com.infinitelearning.tugasadvance.domain.model.SignInResult
import com.infinitelearning.tugasadvance.domain.model.UserData
import kotlinx.coroutines.flow.Flow

interface AuthPreferences {

    val tokenUser : Preferences.Key<String>
    val gmailUser : Preferences.Key<String>
    val userName : Preferences.Key<String>
    val profile : Preferences.Key<String>
    val statusLogin : Preferences.Key<Boolean>

    fun getSessionUser(): Flow<UserData>

    suspend fun saveSessionUser(session: UserData)

    suspend fun clearSessionUser()


    fun isLoggedIn(): Flow<Boolean>
    suspend fun setLoginStatus(isLogin: Boolean)


//    val tokenUser : Preferences.Key<String>
//    val userName : Preferences.Key<String>
//
//
//    fun getToken(): Flow<String?>
//
//    fun getUserName(): Flow<String?>
//
//    suspend fun saveToken(token: String)
//
//    suspend fun saveUserName(userName: String)
//
//    suspend fun deleteToken()
//
//    suspend fun deleteUserName()

}