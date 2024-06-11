package com.infinitelearning.tugasadvance.presentation.screen.splash

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.infinitelearning.tugasadvance.R
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreenTheme {
                Surface(color = MaterialTheme.colors.background) {
                    SplashScreen()
                }
            }
        }
    }

    private fun SplashScreen() {
        TODO("Not yet implemented")
    }
}

class SplashScreenTheme(function: @Composable () -> Unit) {

}

@Composable
fun SplashScreen(
    navController: NavHostController
) {
    val pacificoFont = FontFamily(Font(R.font.pacifico_regular))
    val scope = rememberCoroutineScope()
    var startAnimation by remember { mutableStateOf(false) }
    var showText by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(1000)
        startAnimation = false
        delay(500)
        startAnimation = true
        delay(1000)
        showText = true
    }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        if (startAnimation) {
            CenterLogoAnimation(pacificoFont, showText)
        } else {
            MoveAndRotateLogoAnimation(pacificoFont, showText)
        }
    }
}

@Composable
fun CenterLogoAnimation(pacificoFont: FontFamily, showText: Boolean) {
    val infiniteTransition = rememberInfiniteTransition()
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -200f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )
    val angle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = -38.99f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.offset(x = offset.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Icon",
            modifier = Modifier
                .size(50.dp)
                .rotate(angle)
        )
        if (showText) {
            Text(
                text = "NontonAja",
                fontFamily = pacificoFont,
                fontSize = 32.sp,
                color = Color(0xFF031447)
            )
        }
    }
}

private fun InfiniteTransition.animateFloat(initialValue: Float, targetValue: Float, animationSpec: TweenSpec<Any>): State<Float> {

    return TODO("Provide the return value")
}

@Composable
fun MoveAndRotateLogoAnimation(pacificoFont: FontFamily, showText: Boolean) {
    val transition = rememberInfiniteTransition()
    val offset by transition.animateFloat(
        initialValue = 0f,
        targetValue = -200f,
        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
    )
    val angle by transition.animateFloat(
        initialValue = 0f,
        targetValue = -38.99f,
        animationSpec = tween(durationMillis = 1000, easing = LinearEasing)
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.offset(x = offset.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Icon",
            modifier = Modifier
                .size(50.dp)
                .rotate(angle)
        )
        if (showText) {
            Text(
                text = "NontonAja",
                fontFamily = pacificoFont,
                fontSize = 32.sp,
                color = Color(0xFF031447)
            )
        }
    }
}


