package com.infinitelearning.tugasadvance.presentation.screen.auth.login

import android.app.Activity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.infinitelearning.tugasadvance.R
import com.infinitelearning.tugasadvance.presentation.navigation.Screen
import com.infinitelearning.tugasadvance.presentation.screen.auth.common.signInIntentSender
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.EmailTextField
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.GoogleButton
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.PasswordTextField
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.TextChoice
import com.infinitelearning.tugasadvance.ui.theme.primaryColor
import com.infinitelearning.tugasadvance.utils.ImoKeyboard
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(
    moveToHome: () -> Unit,
    navController: NavController,
    moveToRegister: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = hiltViewModel()
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val loginLoading = viewModel.loginState.collectAsState().value.isLoading
    val loginSuccess = viewModel.loginState.collectAsState().value.isSuccess

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.StartIntentSenderForResult(),
        onResult = { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                scope.launch {
                    viewModel.loginWithGmail(
                        result.data ?: return@launch
                    )
                }
            }
        }
    )

    var isLoadingDialogShow by remember {
        mutableStateOf(false)
    }
    var isSuccessDialogShow by remember {
        mutableStateOf(false)
    }

    if (loginLoading) {
        LoadingDialog(onDismissRequest = { isLoadingDialogShow = false })
    }

    if (loginSuccess) {
        DialogLoginSuccess(
            onDismissRequest = { isSuccessDialogShow = false },
            moveToHome = {
                navController.navigate(Screen.HomeScreen.route) {
                    popUpTo(Screen.LoginScreen.route) {
                        inclusive = true
                    }
                }
            }
        )
    }

    LoginContent(
        email = email,
        password = password,
        onEmailChange = { email = it },
        onPasswordChange = { password = it },
        onLoginClick = { viewModel.login(email, password) },
        onGoogleClick = {
            scope.launch {
                val signInIntentSender = signInIntentSender(context)
                launcher.launch(
                    IntentSenderRequest.Builder(
                        signInIntentSender ?: return@launch
                    ).build()
                )
            }
        },
        moveToRegister = moveToRegister
    )
}

@Composable
fun LoginContent(
    email: String,
    password: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onGoogleClick: () -> Unit,
    moveToRegister: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        ) {

            Spacer(modifier = Modifier.height(60.dp))

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = null)

            Text(
                text = "Sign In",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "Login Disini",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(top = 4.dp)
            )

            Spacer(modifier = Modifier.height(50.dp))

            EmailTextField(
                value = email,
                onValueChange = onEmailChange,
                imageVector = Icons.Outlined.Email,
                contentDescription = "Email",
                label = "Email",
                keyboardIme = ImoKeyboard.NEXT
            )

            PasswordTextField(
                text = password,
                onValueChange = onPasswordChange,
                label = "Password",
                keyboardIme = ImoKeyboard.END
            )

            Button(
                onClick = onLoginClick,
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White, containerColor = Color(0xFF1C1B1F)
                ),
                border = BorderStroke(1.dp, Color.White),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 20.dp)
                    .height(48.dp)
            ) {
                Text(
                    text = "Login",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            TextChoice()

            GoogleButton(
                clicked = onGoogleClick,
                isConnectLoading = false,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            )

            Row(
                modifier = Modifier.padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Apakah Kamu belum punya akun?")
                TextButton(onClick = moveToRegister) {
                    Text(
                        text = "Daftar",
                        color = Color.White,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(128.dp))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = modifier
        ) {
            CircularProgressIndicator(
                modifier = modifier,
                color = primaryColor,
                trackColor = MaterialTheme.colorScheme.surfaceVariant
            )
        }
    }
}


@Composable
fun DialogLoginSuccess(
    onDismissRequest: () -> Unit,
    moveToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        onDismissRequest = { onDismissRequest() },
        text = {
            Text(
                text = "Yey, Berhasil Login",
                style = MaterialTheme.typography.bodyMedium
            )
        },
        confirmButton = {
            TextButton(onClick = { moveToHome() }) {
                Text(
                    text = "Go to Home",
                    style = MaterialTheme.typography.bodyLarge.copy(
                        fontSize = 14.sp
                    ),
                    color = Color.White
                )
            }
        }
    )
}