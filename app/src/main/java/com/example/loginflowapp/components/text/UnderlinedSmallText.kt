package com.example.loginflowapp.components.text

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun UnderlinedSmallText(
    text: String,
    modifier: Modifier = Modifier
) {
    val annotatedString = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp,
                textDecoration = TextDecoration.Underline
            )
        ) {
            append(text)
        }
    }
    ClickableText(
        text = annotatedString,
        modifier = modifier
    ) {}
}