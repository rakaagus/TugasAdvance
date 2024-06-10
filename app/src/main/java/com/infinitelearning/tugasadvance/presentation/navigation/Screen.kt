package com.infinitelearning.tugasadvance.presentation.navigation

sealed class Screen(val route: String) {
    object SplashScreen : Screen("splash_screen")
    object LoginScreen : Screen("login_screen")
    object HomeScreen : Screen("home_screen")
    object MapScreen : Screen("map_screen")
    object AlarmScreen : Screen("alarm_screen")
    object FavScreen : Screen("favorite_screen")
}