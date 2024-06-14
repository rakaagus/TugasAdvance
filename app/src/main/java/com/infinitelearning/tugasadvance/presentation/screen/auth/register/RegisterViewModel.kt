package com.infinitelearning.tugasadvance.presentation.screen.auth.register

import androidx.lifecycle.ViewModel
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
}