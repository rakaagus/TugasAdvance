package com.infinitelearning.tugasadvance.data.repository

import android.content.Intent
import com.infinitelearning.tugasadvance.data.Result
import com.infinitelearning.tugasadvance.data.datastore.AppDataStore
import com.infinitelearning.tugasadvance.data.datastore.AuthDataStore
import com.infinitelearning.tugasadvance.domain.model.SignInResult
import com.infinitelearning.tugasadvance.domain.model.UserData
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import com.infinitelearning.tugasadvance.domain.repository.FireBaseAuth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepositoryImpl @Inject constructor(
    private val fireBaseAuth: FireBaseAuth,
    private val appDataStore: AppDataStore,
    private val authDataStore: AuthDataStore
) :AuthRepository {
    override fun login(email: String, password: String): Flow<Result<SignInResult>> = flow {
        emit(Result.Loading())
        try {
            val response = fireBaseAuth.signInWithEmail(email, password)
            val data = response.data
            emit(Result.Success(response))
        }catch (e: Exception){
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun register(
        name: String,
        email: String,
        password: String
    ): Flow<Result<SignInResult>> = flow {
        emit(Result.Loading())
        try {
            val response = fireBaseAuth.signUpWithEmail(name, email, password)
            emit(Result.Success(response))
        }catch (e: Exception){
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun signInWithIntent(intent: Intent): Flow<Result<SignInResult>> = flow {
        emit(Result.Loading())
        try {
            val response = fireBaseAuth.signInWithIntent(intent)
            emit(Result.Success(response))
        }catch (e: Exception){
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSignedUser(): Flow<UserData?> = flow {
        val response = fireBaseAuth.getSignedUser()
        emit(response)
    }.flowOn(Dispatchers.IO)

    override suspend fun signOut() {
        fireBaseAuth.signOut()
    }

    override fun getStatusOnboardingUser(): Flow<Boolean> = appDataStore.getStatusOnboardingUser()

    override suspend fun saveStatusOnboardingUser(status: Boolean) {
        appDataStore.saveStatusOnboardingUser(status)
    }

    override fun isLoggedIn(): Flow<Boolean> = authDataStore.isLoggedIn()

    override fun getToken(): Flow<String?> = authDataStore.getToken()

    override fun getUserName(): Flow<String?> = authDataStore.getUserName()

    override suspend fun setLoginStatus(isLogin: Boolean) {
        authDataStore.setLoginStatus(isLogin)
    }

    override suspend fun saveToken(token: String) {
        authDataStore.saveToken(token)
    }

    override suspend fun saveUserName(userName: String) {
        authDataStore.saveUserName(userName)
    }

    override suspend fun deleteToken() = authDataStore.deleteToken()

    override suspend fun deleteUserName() = authDataStore.deleteUserName()
}