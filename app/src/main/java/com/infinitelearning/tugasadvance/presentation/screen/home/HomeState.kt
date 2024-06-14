package com.infinitelearning.tugasadvance.presentation.screen.home

import com.infinitelearning.tugasadvance.domain.model.UserData

data class HomeState(
    val data: UserData? = null,
    val logoutSuccess: Boolean = false
)