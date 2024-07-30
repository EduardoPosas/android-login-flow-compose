package com.example.loginflowapp.components.form

import android.util.Log
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun FormBottomLabel(
    noClickableText: String,
    clickableText: String,
    modifier: Modifier = Modifier,
    onTagClicked: () -> Unit,
) {
    val annotatedText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        ) {
            append("$noClickableText ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                fontSize = 14.sp
            )
        ) {
            pushStringAnnotation("clickable", annotation = "clickable")
            append(clickableText)
        }
    }
    ClickableText(
        text = annotatedText,
        modifier = modifier
    ) { chrOffset ->
        annotatedText.getStringAnnotations(chrOffset, chrOffset).firstOrNull()?.tag?.let { tag ->
            Log.d("CLICKABLE", "Tag: $tag was clicked")
            if (tag == "clickable") {
                onTagClicked()
            }
        }
    }
}