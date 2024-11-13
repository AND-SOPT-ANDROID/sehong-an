package org.sopt.and.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

class WavveColors(
    main_blue: Color = Color(0xFF1351F9),
    white: Color = Color(0xFFFFFFFF),
    gray_1: Color = Color(0xFF252525),
    gray_2: Color = Color(0xFF2F2F2F),
    gray_3: Color = Color(0xFFA5A5A5),
    gray_4: Color = Color(0xFF717171),
    gray_5: Color = Color(0xFF1B1B1B),
    error_1: Color = Color(0xFFFF27A3),
    text_1: Color = Color(0xFF222222),
    text_2: Color = Color(0xFF616161),
    text_3: Color = Color(0xFF949494),
) {
    var main_blue by mutableStateOf(main_blue)
        private set
    var white by mutableStateOf(white)
        private set

    var gray_1 by mutableStateOf(gray_1)
        private set
    var gray_2 by mutableStateOf(gray_2)
        private set
    var gray_3 by mutableStateOf(gray_3)
        private set
    var gray_4 by mutableStateOf(gray_4)
        private set
    var gray_5 by mutableStateOf(gray_5)
        private set
    var error_1 by mutableStateOf(error_1)
        private set
    var text_1 by mutableStateOf(text_1)
        private set
    var text_2 by mutableStateOf(text_2)
        private set
    var text_3 by mutableStateOf(text_3)
        private set

    fun copy(
        main_blue: Color = this.main_blue,
        white: Color = this.white,
        gray_1: Color = this.gray_1,
        gray_2: Color = this.gray_2,
        gray_3: Color = this.gray_3,
        gray_4: Color = this.gray_4,
        gray_5: Color = this.gray_5,
        error_1: Color = this.error_1,
        text_1: Color = this.text_1,
        text_2: Color = this.text_2,
        text_3: Color = this.text_3,
    ): WavveColors {
        return WavveColors(
            main_blue = main_blue,
            white = white,
            gray_1 = gray_1,
            gray_2 = gray_2,
            gray_3 = gray_3,
            gray_4 = gray_4,
            gray_5 = gray_5,
            error_1 = error_1,
            text_1 = text_1,
            text_2 = text_2,
            text_3 = text_3,
        )
    }

    fun updateColorFrom(other: WavveColors) {
        main_blue = other.main_blue
        white = other.white
        gray_1 = other.gray_1
        gray_2 = other.gray_2
        gray_3 = other.gray_3
        gray_4 = other.gray_4
        gray_5 = other.gray_5
        error_1 = other.error_1
        text_1 = other.text_1
        text_2 = other.text_2
        text_3 = other.text_3
    }
}

val LocalColors = staticCompositionLocalOf { WavveColors() }