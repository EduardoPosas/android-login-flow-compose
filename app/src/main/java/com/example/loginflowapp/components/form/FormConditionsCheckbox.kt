package com.example.loginflowapp.components.form

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loginflowapp.auth.domain.UiText

@Composable
fun FormConditionsCheckbox(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    isError: Boolean,
    errorText: UiText?,
    modifier: Modifier = Modifier,
    onPrivacyClicked: () -> Unit,
    onTermsClicked: () -> Unit
) {

    val clickableText = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        ) {
            append("By continuing you accept our ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        ) {
            pushStringAnnotation("privacy", "privacy")
            append("Privacy Policy")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 14.sp
            )
        ) {
            append(" and ")
        }
        withStyle(
            style = SpanStyle(
                color = MaterialTheme.colorScheme.primary,
                textDecoration = TextDecoration.Underline,
                fontSize = 14.sp
            )
        ) {
            pushStringAnnotation("terms", "terms")
            append("Term of Use")
        }
    }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(checked = checked, onCheckedChange = { onCheckedChange(it) })
            ClickableText(text = clickableText) { chrOffset ->
//                Log.d("CLICKABLE", "$offset -th character is clicked.")
                clickableText.getStringAnnotations(chrOffset, chrOffset)
                    .firstOrNull()?.tag?.let { tag ->
                        Log.d("CLICKABLE", "Tag: $tag was clicked")
                        if (tag == "privacy") {
                            onPrivacyClicked()
                        }
                        if (tag == "terms") {
                            onTermsClicked()
                        }
                    }
            }
        }
        if (isError) {
            Text(
                text = errorText!!.asString(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(horizontal = 18.dp),
            )
        }
    }

}