package com.infinitelearning.tugasadvance.domain.repository

import android.content.Intent
import com.infinitelearning.tugasadvance.domain.model.SignInResult
import com.infinitelearning.tugasadvance.domain.model.UserData
import kotlinx.coroutines.flow.Flow
import com.infinitelearning.tugasadvance.data.Result

interface AuthRepository {
    fun login(email: String, password: String): Flow<Result<SignInResult>>
    fun register(name: String, email: String, password: String): Flow<Result<SignInResult>>
    fun signInWithIntent(intent: Intent): Flow<Result<SignInResult>>
//    fun getSignedUser(): Flow<UserData?>
    suspend fun signOut()
}