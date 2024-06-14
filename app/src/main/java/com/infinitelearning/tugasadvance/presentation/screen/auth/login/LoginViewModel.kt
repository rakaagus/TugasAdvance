package com.infinitelearning.tugasadvance.presentation.screen.auth.login

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinitelearning.tugasadvance.data.Result
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _loginState = MutableStateFlow(LoginState())
    val loginState = _loginState.asStateFlow()

    fun login(email: String, password: String) = viewModelScope.launch {
        authRepository.login(email, password).collect { result ->
            when (result) {
                is Result.Loading -> {
                    _loginState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Result.Success -> {
                    _loginState.update {
                        it.copy(
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                }

                is Result.Error -> {
                    _loginState.update {
                        it.copy(
                            isError = result.message,
                            isConnectLoading = true
                        )
                    }
                }
            }
        }
    }

    fun loginWithGmail(intent: Intent) = viewModelScope.launch {
        authRepository.signInWithIntent(intent).collect { result ->
            when (result) {
                is Result.Error -> {
                    _loginState.update {
                        it.copy(
                            isError = result.message,
                            isConnectLoading = false
                        )
                    }
                }

                is Result.Loading -> {
                    _loginState.update {
                        it.copy(
                            isConnectLoading = true
                        )
                    }
                }

                is Result.Success -> {
                    _loginState.update {
                        it.copy(
                            signInResult = result.data,
                            isSuccess = true,
                            isConnectLoading = false
                        )
                    }
                }
            }
        }
    }
}