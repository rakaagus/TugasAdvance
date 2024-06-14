package com.infinitelearning.tugasadvance.presentation.screen.auth.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.infinitelearning.tugasadvance.utils.ImoKeyboard

@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    text: String,
    keyboardIme: ImoKeyboard,
    onValueChange: (String) -> Unit,
    label: String,
) {
    val passwordVisible = remember {
        mutableStateOf(false)
    }

    val typeImeKeyboard = when(keyboardIme){
        ImoKeyboard.NEXT -> ImeAction.Next
        ImoKeyboard.END-> ImeAction.Done
    }

    OutlinedTextField(
        value = text,
        shape = ShapeDefaults.Medium,
        onValueChange = onValueChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password, imeAction = typeImeKeyboard),
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Lock,
                contentDescription = "Password"
            )
        },
        trailingIcon = {
            val iconImage = if (passwordVisible.value) {
                Icons.Outlined.Visibility
            } else {
                Icons.Outlined.VisibilityOff
            }

            val description = if (passwordVisible.value) {
                "Password Visible"
            } else {
                "Password Not Visible"
            }

            IconButton(
                onClick = { passwordVisible.value = !passwordVisible.value }
            ) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedLabelColor = Color.White,
            unfocusedLabelColor = Color.White,
            cursorColor = Color.White,
            focusedContainerColor = Color(0xFF1C1B1F),
            unfocusedContainerColor = Color(0xFF1C1B1F),
            focusedIndicatorColor = Color.White,
            unfocusedIndicatorColor = Color.White,
        ),
        singleLine = true,
        visualTransformation = if (passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        label = { Text(text = label) },
        modifier = modifier
            .fillMaxWidth()
    )
}