package com.bp.wmp.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

/**
 * WheresMyPetrolTheme is a composable function that applies the Material Design theme
 * to the content provided. It uses a light color scheme and custom typography.
 *
 * @param content The composable content to be themed.
 * @return A themed composable that applies the Material Design styles.
 */
@Suppress("ktlint:standard:function-naming")
@Composable
fun WheresMyPetrolTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = lightColorScheme(),
        typography = typography,
        content = content,
    )
}

/**
 * Typography is a custom typography configuration for the WheresMyPetrol app.
 * It defines the styles for text elements used throughout the app.
 */
private val typography =
    Typography(
        bodyLarge =
            TextStyle(
                fontFamily = FontFamily.Default,
                fontWeight = FontWeight.Normal,
                fontSize = DIMEN_16_SP,
                lineHeight = DIMEN_24_SP,
                letterSpacing = DIMEN_0_5_SP,
            ),
    )
