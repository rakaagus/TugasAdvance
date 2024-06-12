package com.infinitelearning.tugasadvance.presentation.screen.auth.login

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.EmailTextField
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.GoogleButton
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.PasswordTextField
import com.infinitelearning.tugasadvance.presentation.screen.auth.component.TextChoice

@Composable
fun LoginScreen(modifier: Modifier = Modifier) {
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
            Spacer(modifier = Modifier.height(100.dp))

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

            Spacer(modifier = Modifier.height(64.dp))

            EmailTextField(
                value = "",
                onValueChange = {},
                imageVector = Icons.Outlined.Email,
                contentDescription = "Email",
                label = "Email",
            )

            PasswordTextField(
                text = "",
                onValueChange = {},
                label = "Password"
            )

            TextButton(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(bottom = 8.dp)
            ) {
                Text(
                    text = "Forgot password",
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            Button(
                onClick = {  },
                shape = MaterialTheme.shapes.large,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
                    .height(48.dp)
            ) {
                if (false) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.onPrimary,
                        strokeWidth = 3.dp,
                        modifier = Modifier
                            .size(32.dp)
                    )
                } else {
                    Text(
                        text = "Login",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            TextChoice()

            GoogleButton(
                clicked = {},
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
                TextButton(onClick = {}) {
                    Text(
                        text = "Daftar",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            }

            Spacer(modifier = Modifier.height(128.dp))
        }
    }
}