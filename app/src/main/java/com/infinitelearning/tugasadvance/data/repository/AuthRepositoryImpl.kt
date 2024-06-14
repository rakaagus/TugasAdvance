package com.infinitelearning.tugasadvance.data.repository

import android.content.Intent
import com.infinitelearning.tugasadvance.data.Result
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
) : AuthRepository {
    override fun login(email: String, password: String): Flow<Result<SignInResult>> = flow {
        emit(Result.Loading())
        try {
            val response = fireBaseAuth.signInWithEmail(email, password)
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

//    override fun getSignedUser(): Flow<UserData?> = {
//
//    }

    override suspend fun signOut() {
        fireBaseAuth.signOut()
    }
}