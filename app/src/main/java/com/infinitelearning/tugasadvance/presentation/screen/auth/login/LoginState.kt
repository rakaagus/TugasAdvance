package com.infinitelearning.tugasadvance.presentation.screen.auth.login

import com.infinitelearning.tugasadvance.domain.model.SignInResult

data class LoginState(
    val email: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val isConnectLoading: Boolean = false,
    val signInResult: SignInResult? = null,
    val isError: String? = null
)
