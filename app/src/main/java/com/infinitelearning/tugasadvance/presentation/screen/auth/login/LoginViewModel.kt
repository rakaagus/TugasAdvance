package com.infinitelearning.tugasadvance.presentation.screen.auth.login

import androidx.lifecycle.ViewModel
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel(){

}