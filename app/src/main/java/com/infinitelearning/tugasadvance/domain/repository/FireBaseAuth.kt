package com.infinitelearning.tugasadvance.domain.repository

import android.content.Intent
import com.infinitelearning.tugasadvance.domain.model.SignInResult
import com.infinitelearning.tugasadvance.domain.model.UserData

interface FireBaseAuth {
    suspend fun signInWithIntent(intent: Intent) : SignInResult
    suspend fun signInWithEmail(email: String, password: String): SignInResult
    suspend fun signUpWithEmail(name: String, email: String, password: String): SignInResult
    suspend fun signOut()
    suspend fun getSignedUser(): UserData?
}