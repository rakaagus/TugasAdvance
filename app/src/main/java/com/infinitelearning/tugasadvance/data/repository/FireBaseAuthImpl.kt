package com.infinitelearning.tugasadvance.data.repository

import android.content.Intent
import com.google.android.gms.auth.api.identity.SignInClient
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.infinitelearning.tugasadvance.data.datastore.AuthDataStore
import com.infinitelearning.tugasadvance.domain.model.SignInResult
import com.infinitelearning.tugasadvance.domain.model.UserData
import com.infinitelearning.tugasadvance.domain.repository.FireBaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FireBaseAuthImpl @Inject constructor(
    private val client: SignInClient,
    private val authDataStore: AuthDataStore,
) : FireBaseAuth {

    private val firebaseAuth = Firebase.auth

    override suspend fun signInWithIntent(intent: Intent): SignInResult {
        val credential = client.getSignInCredentialFromIntent(intent)
        val googleIdToken = credential.googleIdToken
        val googleCredential = GoogleAuthProvider.getCredential(googleIdToken, null)
        val singInTask = firebaseAuth.signInWithCredential(googleCredential).await()
        val user = singInTask.user

        setLoginUser(
            user.let {
                UserData(
                    userId = it?.uid,
                    username = it?.displayName,
                    email = it?.email,
                    profilePicture = it?.photoUrl.toString()
                )
            }
        )
        saveLoginStatus(true)
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePicture = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signInWithEmail(email: String, password: String): SignInResult {
        val signInTask = firebaseAuth.signInWithEmailAndPassword(email, password).await()
        val user = signInTask.user
        setLoginUser(
            user.let {
                UserData(
                    userId = it?.uid,
                    username = it?.displayName,
                    email = it?.email,
                    profilePicture = it?.photoUrl.toString()
                )
            }
        )
        saveLoginStatus(true)
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePicture = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signUpWithEmail(
        name: String,
        email: String,
        password: String
    ): SignInResult {
        val registerTask = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
        registerTask.user?.updateProfile(
            UserProfileChangeRequest.Builder().setDisplayName(name).build()
        )
        val user = registerTask.user
        setLoginUser(
            user.let {
                UserData(
                    userId = it?.uid,
                    username = it?.displayName,
                    email = it?.email,
                    profilePicture = it?.photoUrl.toString()
                )
            }
        )
        saveLoginStatus(true)
        return SignInResult(
            data = user?.run {
                UserData(
                    userId = uid,
                    username = displayName,
                    email = email,
                    profilePicture = photoUrl?.toString()
                )
            },
            errorMessage = null
        )
    }

    override suspend fun signOut() {
        client.signOut()
        firebaseAuth.signOut()
        clearSessionUser()
    }

    override fun getSessionUser(): Flow<UserData> = authDataStore.getSessionUser()

//    override suspend fun getSignedUser(): UserData? = firebaseAuth.currentUser?.run {
//        UserData(
//            userId = uid,
//            username = displayName,
//            email = email,
//            profilePicture = photoUrl?.toString()
//        )
//    }

    override suspend fun setLoginUser(user: UserData) = authDataStore.saveSessionUser(user)

    override suspend fun clearSessionUser() = authDataStore.clearSessionUser()

    override fun getLoginStatus(): Flow<Boolean> = authDataStore.isLoggedIn()

    override suspend fun saveLoginStatus(isLogin: Boolean) = authDataStore.setLoginStatus(isLogin)
}