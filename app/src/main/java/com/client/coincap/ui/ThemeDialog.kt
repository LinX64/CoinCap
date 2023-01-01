package com.client.coincap.ui

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat

@Composable
fun ThemeDialog(
    onDismiss: () -> Unit = {},
    onThemeSelected: (Themes) -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Theme") },
        text = { AlertContent(onThemeSelected) },
        confirmButton = {
            Text(
                text = "OK",
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .clickable { onDismiss() }
            )
        }
    )
}

@Composable
fun AlertContent(
    onClick: (Themes) -> Unit
) {
    Divider()
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(8.dp))
        Column(Modifier.selectableGroup()) {
            SettingsDialogThemeChooserRow(
                text = "Light",
                selected = true,
                onClick = { onClick(Themes.LIGHT) }
            )

            SettingsDialogThemeChooserRow(
                text = "Follow system",
                selected = false,
                onClick = { onClick(Themes.FOLLOW_SYSTEM) }
            )

            SettingsDialogThemeChooserRow(
                text = "Dark",
                selected = false,
                onClick = { onClick(Themes.DARK) }
            )
            Spacer(modifier = Modifier.height(8.dp))

            Divider()

            LinksPanel()
        }
    }
}

@Composable
fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
        )
        Text(text)
    }
}

@Composable
private fun LinksPanel() {
    Row(
        modifier = Modifier.padding(top = 16.dp)
    ) {
        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Row {
                TextLink(
                    text = "Privacy Policy",
                    url = "Some url"
                )
                Spacer(Modifier.width(16.dp))
                TextLink(
                    text = "Licenses",
                    url = "Some url"
                )
            }
        }
    }
}

@Composable
private fun TextLink(text: String, url: String) {
    val launchResourceIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    val context = LocalContext.current

    Text(
        text = text,
        style = MaterialTheme.typography.labelLarge,
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier
            .clickable {
                ContextCompat.startActivity(context, launchResourceIntent, null)
            }
    )
}

@Preview
@Composable
fun ThemeDialogPreview() {
    ThemeDialog()
}

enum class Themes {
    LIGHT, FOLLOW_SYSTEM, DARK
}
