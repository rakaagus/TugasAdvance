package com.infinitelearning.tugasadvance.presentation.screen.auth.register

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.infinitelearning.tugasadvance.data.Result
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import com.infinitelearning.tugasadvance.presentation.screen.auth.login.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _registerState = MutableStateFlow(LoginState())
    val registerState = _registerState.asStateFlow()

    fun register(name: String, email: String, password: String) = viewModelScope.launch {
        authRepository.register(name, email, password).collect{ result ->
            when(result){
                is Result.Loading -> {
                    _registerState.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _registerState.update {
                        it.copy(
                            isSuccess = true,
                            isLoading = false
                        )
                    }
                }
                is Result.Error -> {
                    _registerState.update {
                        it.copy(
                            isError = result.message,
                            isConnectLoading = true
                        )
                    }
                }
            }
        }
    }

    fun registerWithGmail(intent: Intent) = viewModelScope.launch {
        authRepository.signInWithIntent(intent).collect { result ->
            when (result) {
                is Result.Error -> {
                    _registerState.update {
                        it.copy(
                            isError = result.message,
                            isConnectLoading = false
                        )
                    }
                }

                is Result.Loading -> {
                    _registerState.update {
                        it.copy(
                            isConnectLoading = true
                        )
                    }
                }

                is Result.Success -> {
                    _registerState.update {
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