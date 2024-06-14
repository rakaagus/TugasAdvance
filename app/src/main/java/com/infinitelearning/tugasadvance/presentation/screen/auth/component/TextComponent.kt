package com.infinitelearning.tugasadvance.presentation.screen.auth.component

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TextChoice(
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Text(
            text = "Atau",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}