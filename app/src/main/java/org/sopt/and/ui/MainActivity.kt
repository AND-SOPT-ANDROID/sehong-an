package org.sopt.and.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import dagger.hilt.android.AndroidEntryPoint
import org.sopt.and.data.UserManager
import org.sopt.and.ui.navigation.WavveNavigation
import org.sopt.and.ui.theme.ANDANDROIDTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var userManager: UserManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ANDANDROIDTheme {
                Surface {
                    WavveNavigation(userManager = userManager)
                }
            }
        }
    }
}