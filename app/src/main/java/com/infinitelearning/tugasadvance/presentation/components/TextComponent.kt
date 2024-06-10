package com.example.weebspedia.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ChipBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.infinitelearning.tugasadvance.ui.theme.colorInContainer

@Composable
fun TextInfoProfile(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 15.dp),
    ) {
        Text(
            text = title + ": ",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
        Text(
            text = value,
            style = MaterialTheme.typography.labelLarge,
            color = Color.White
        )
    }
}

@Composable
fun SectionText(title: String, content: @Composable () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(top = 20.dp)) {
        Text(text = title, style = MaterialTheme.typography.labelLarge, color = Color.White, modifier = Modifier.padding(horizontal = 12.dp))
        content()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GenreTextButton(
    genreId: Int,
    name: String,
    clickToGenreScreen: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    FilterChip(
        onClick = { clickToGenreScreen(genreId) },
        label = {
            Text(text = name, style = MaterialTheme.typography.labelMedium, color = colorInContainer)
        },
        shape = MaterialTheme.shapes.small,
        selected = true,
        modifier = modifier.height(35.dp)
    )
}