package com.example.loginflowapp.components.form

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun FormIconButton(
    @DrawableRes image: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.surfaceContainerHighest
        ),
        contentPadding = PaddingValues(horizontal = 4.dp, vertical = 12.dp)
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "google sign logo",
            modifier = Modifier
                .size(28.dp)
        )
    }
}