package com.infinitelearning.tugasadvance.presentation.screen.splash


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.infinitelearning.tugasadvance.R
import com.infinitelearning.tugasadvance.presentation.navigation.Screen
import com.infinitelearning.tugasadvance.ui.theme.Pasifico
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navController: NavHostController,
    viewModel: SplashViewModel = hiltViewModel()
) {

    var logoVisible by remember { mutableStateOf(false) }
    var logoRotated by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }

    val statusLogin = viewModel.getStatusLogin().collectAsState(initial = false)

    LaunchedEffect(Unit) {
        delay(1000)
        showText = true
        delay(1000)
        logoRotated = true
        logoVisible = true
        delay(1000)
        if (statusLogin.value){
            navController.navigate(Screen.HomeScreen.route){
                popUpTo(Screen.SplashScreen.route){
                    inclusive = true
                }
            }
        }else {
            navController.navigate(Screen.LoginScreen.route){
                popUpTo(Screen.SplashScreen.route){
                    inclusive = true
                }
            }
        }

    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedLogo(
            logoVisible = logoVisible,
            logoRotated = logoRotated,
            showText = showText
        )
    }
}

@Composable
fun AnimatedLogo(
    logoVisible: Boolean,
    logoRotated: Boolean,
    showText: Boolean
) {
    val alpha by animateFloatAsState(
        targetValue = if (logoVisible) 1f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )
    val rotation by animateFloatAsState(
        targetValue = if (logoRotated) 90f else 0f,
        animationSpec = tween(durationMillis = 1000)
    )


    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Icon",
            modifier = Modifier
                .size(150.dp)
                .rotate(rotation)
        )
        AnimatedVisibility(visible = showText) {
            Text(
                text = "NontonAja",
                fontFamily = Pasifico,
                fontSize = 32.sp,
                color = Color.White,
                modifier = Modifier.alpha(alpha)
            )
        }
    }
}

