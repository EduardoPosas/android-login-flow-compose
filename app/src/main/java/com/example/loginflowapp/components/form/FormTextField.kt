package com.example.loginflowapp.components.form

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import com.example.loginflowapp.auth.domain.UiText

@Composable
fun FormTextField(
    label: String,
    value: String,
    isError: Boolean,
    errorText: UiText?,
    modifier: Modifier = Modifier,
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = modifier.fillMaxWidth(),
        label = {
            Text(
                text = label,
                color = MaterialTheme.colorScheme.onSecondaryContainer
            )
        },
        leadingIcon = {
            Icon(
                imageVector = Icons.Outlined.Person,
                contentDescription = null
            )
        },
        supportingText = {
            if (isError) Text(text = errorText!!.asString())
        },
        isError = isError,
        singleLine = true,
        shape = ShapeDefaults.Medium,
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            unfocusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer,
            focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedTextColor = MaterialTheme.colorScheme.onSecondaryContainer
        ),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Next
        )
    )
}