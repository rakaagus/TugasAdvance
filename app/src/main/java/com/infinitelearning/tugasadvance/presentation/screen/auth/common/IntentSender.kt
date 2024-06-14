package com.infinitelearning.tugasadvance.presentation.screen.auth.common

import android.content.Context
import android.content.IntentSender
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.auth.api.identity.Identity
import com.infinitelearning.tugasadvance.R
import kotlinx.coroutines.tasks.await

suspend fun signInIntentSender(context: Context): IntentSender? {
    return try {
        val oneTapClient = Identity.getSignInClient(context)
        val result = oneTapClient.beginSignIn(
            buildSignInRequest(context)
        ).await()
        result?.pendingIntent?.intentSender
    } catch (e: Exception) {
        null
    }
}

fun buildSignInRequest(context: Context): BeginSignInRequest {
    return BeginSignInRequest.Builder()
        .setGoogleIdTokenRequestOptions(
            BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                .setSupported(true)
                .setFilterByAuthorizedAccounts(false)
                .setServerClientId("649036315199-buc17pfub55jdvjcq1adlan9bpnpsthj.apps.googleusercontent.com")
                .build()
        )
        .setAutoSelectEnabled(true)
        .build()
}