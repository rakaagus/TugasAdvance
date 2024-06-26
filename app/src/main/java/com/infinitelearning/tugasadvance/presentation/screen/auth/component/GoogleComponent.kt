package com.infinitelearning.tugasadvance.presentation.screen.auth.component

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.infinitelearning.tugasadvance.R

@Composable
fun GoogleButton(
    clicked: () -> Unit,
    isConnectLoading: Boolean,
    modifier: Modifier = Modifier
) {

    OutlinedButton(
        onClick = clicked,
        shape = RoundedCornerShape(12.dp),
        modifier = modifier.fillMaxWidth()
    ) {
        if (isConnectLoading) {
            CircularProgressIndicator(
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = 3.dp,
                modifier = Modifier
                    .size(32.dp)
            )
        } else {
            Icon(
                painter = painterResource(R.drawable.google_icon),
                contentDescription = "Icon Google",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Google Content",
                color = Color.White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}