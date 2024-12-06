package org.sopt.and.presentation.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable

object WavveTheme {
    val colors: WavveColors
        @Composable
        @ReadOnlyComposable
        get() = LocalColors.current

    val typography: WavveTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalTypography.current
}