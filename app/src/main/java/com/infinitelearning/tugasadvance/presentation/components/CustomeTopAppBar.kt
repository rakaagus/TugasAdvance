package com.example.weebspedia.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController

@Composable
fun CustomTopNavigationBar(
    title: String,
    navController: NavController,
    onClickAction: () -> Unit = {
        navController.navigateUp()
    },
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 12.dp)
    ) {
        IconButton(
            onClick = onClickAction,
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back Button")
        }
        Text(
            text = title,
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 18.sp),
        )
    }
}