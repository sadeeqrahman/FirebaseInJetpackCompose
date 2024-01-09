package com.sadeeq.encoders.firebaseinjetpackcompose.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.sadeeq.encoders.firebaseinjetpackcompose.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Password(onValueChange: (String) -> Unit, onImeAction: () -> Unit) {
    var password by remember { mutableStateOf("") }
    var isFocused by remember { mutableStateOf(false) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    TextField(
        value = password,
        onValueChange = { newText ->
            password = newText
            onValueChange(newText)
        },
        singleLine = true,
        colors = TextFieldDefaults.textFieldColors(
            containerColor = Color.White,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.Black,
        ),
        placeholder = {
            MyPlaceholderText("Enter Password")
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Password,
            imeAction = ImeAction.Done
        ),
        leadingIcon = {
            Icon(
                imageVector = Icons.Default.Lock,
                contentDescription = null,
                tint = if (isFocused) Color.Green else Color.Red
            )
        },
        trailingIcon = {
            if (password.isNotEmpty()) {
                IconButton(
                    onClick = {
                        isPasswordVisible = !isPasswordVisible
                    },
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(id = if (isPasswordVisible) R.drawable.baseline_do_disturb_off_24 else R.drawable.baseline_remove_red_eye_24),
                        contentDescription = null,
                        tint = Color.Gray
                    )
                }
            }
        },
        modifier = Modifier
            .focusModifier()
            .onFocusChanged {
                isFocused = it.isFocused
            }
            .padding(2.dp)
            .fillMaxWidth()
            .clip(CircleShape)
            .border(1.dp, if (isFocused) Color.Green else Color.Red, CircleShape)
            .background(MaterialTheme.colorScheme.primary, CircleShape),
        visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        keyboardActions = KeyboardActions(
            onDone = {
                onImeAction()
            }
        )
    )
}
