package com.infinitelearning.tugasadvance

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.infinitelearning.tugasadvance.presentation.TugasAdvanceApp
import com.infinitelearning.tugasadvance.ui.theme.TugasAdvanceTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    @OptIn(ExperimentalPermissionsApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TugasAdvanceTheme {
                // A surface container using the 'background' color from the theme
                val postNotificationPermission = rememberPermissionState(permission = android.Manifest.permission.POST_NOTIFICATIONS)

                LaunchedEffect(key1 = true) {
                    if (!postNotificationPermission.status.isGranted) {
                        postNotificationPermission.launchPermissionRequest()
                    }
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TugasAdvanceApp()
                }
            }
        }
    }
}
