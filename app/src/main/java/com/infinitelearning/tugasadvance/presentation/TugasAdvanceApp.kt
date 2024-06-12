package com.infinitelearning.tugasadvance.presentation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Alarm
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Map
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.infinitelearning.tugasadvance.R
import com.infinitelearning.tugasadvance.presentation.navigation.NavigationItem
import com.infinitelearning.tugasadvance.presentation.navigation.Screen
import com.infinitelearning.tugasadvance.presentation.screen.auth.login.LoginScreen
import com.infinitelearning.tugasadvance.presentation.screen.splash.SplashScreen
import com.infinitelearning.tugasadvance.ui.theme.primaryColor

@Composable
fun TugasAdvanceApp(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            when (currentRoute) {
                Screen.MapScreen.route, Screen.SplashScreen.route, Screen.LoginScreen.route -> {}
                Screen.HomeScreen.route, Screen.FavScreen.route, Screen.AlarmScreen.route -> {
                    BottomAppBar(navController = navController)
                }
            }
        },
        topBar = {
            when (currentRoute) {
                Screen.HomeScreen.route -> {
                    CenterTopAppBar(
                        title = R.string.title_home,
                        actionIcon = {
                            IconButton(onClick = {
                                navController.navigate(Screen.MapScreen.route)
                            }) {
                                Icon(imageVector = Icons.Filled.Map, contentDescription = "Map")
                            }
                            IconButton(onClick = {  }) {
                                Icon(imageVector = Icons.Filled.Logout, contentDescription = "Logout")
                            }
                        },
                    )
                }

                Screen.FavScreen.route -> {
                    CenterTopAppBar(
                        title = R.string.title_fav
                    )
                }

                Screen.AlarmScreen.route -> {
                    CenterTopAppBar(
                        title = R.string.title_alarm
                    )
                }

                Screen.MapScreen.route -> {
                    CenterTopAppBar(
                        title = R.string.title_map,
                        navigationIcon = {
                            IconButton(
                                onClick = {
                                    navController.navigate(Screen.HomeScreen.route){
                                        popUpTo(Screen.MapScreen.route){
                                            inclusive = true
                                        }
                                    }
                                },
                                modifier = Modifier
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ArrowBackIosNew,
                                    contentDescription = "Back",
                                    tint = Color.White,
                                    modifier = Modifier
                                )
                            }
                        }
                    )
                }

                Screen.LoginScreen.route, Screen.SplashScreen.route -> {

                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.SplashScreen.route,
            modifier = modifier.padding(innerPadding)
        ) {
            composable(Screen.SplashScreen.route) {
                SplashScreen(navController = navController)
            }
            composable(Screen.HomeScreen.route) {}
            composable(Screen.MapScreen.route) {}
            composable(Screen.AlarmScreen.route) {}
            composable(Screen.FavScreen.route) {}
            composable(Screen.LoginScreen.route) {
                LoginScreen()
            }
            composable(Screen.RegisterScreen.route){

            }
        }

    }
}

@Composable
fun BottomAppBar(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavigationBar(
        modifier = modifier
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        val navigationItems = listOf(
            NavigationItem(
                title = stringResource(id = R.string.title_home),
                icon = Icons.Filled.Home,
                screen = Screen.HomeScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.title_fav),
                icon = Icons.Filled.Favorite,
                screen = Screen.FavScreen
            ),
            NavigationItem(
                title = stringResource(id = R.string.title_alarm),
                icon = Icons.Filled.Alarm,
                screen = Screen.AlarmScreen
            ),
        )
        navigationItems.map { item ->
            NavigationBarItem(
                selected = currentRoute == item.screen.route,
                onClick = {
                    navController.navigate(item.screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        restoreState = true
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = item.title)
                },
                label = {
                    Text(
                        text = item.title
                    )
                })
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CenterTopAppBar(
    @StringRes title: Int? = null,
    actionIcon: @Composable () -> Unit = {},
    navigationIcon: @Composable () -> Unit = {},
    modifier: Modifier = Modifier
) {

    CenterAlignedTopAppBar(
        modifier = modifier
            .fillMaxWidth(),
        title = {
            if (title != null) {
                Text(
                    text = stringResource(id = title),
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontWeight = FontWeight.Bold
                    ), color = Color.White
                )
            } else {

            }
        },
        navigationIcon = {
            navigationIcon()
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = primaryColor
        ),
        actions = {
            actionIcon()
        }
    )
}
