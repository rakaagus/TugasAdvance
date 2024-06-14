package com.infinitelearning.tugasadvance.presentation.screen.splash

import androidx.lifecycle.ViewModel
import com.infinitelearning.tugasadvance.domain.repository.AuthRepository
import com.infinitelearning.tugasadvance.domain.repository.FireBaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val repository: FireBaseAuth
): ViewModel(){

    fun getStatusLogin() = repository.getLoginStatus()

}