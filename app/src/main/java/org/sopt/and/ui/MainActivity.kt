package org.sopt.and.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import org.sopt.and.ui.navigation.WavveNavigation
import org.sopt.and.ui.theme.ANDANDROIDTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        val preferencesManager = PreferencesManager(this)
//        val userManager = UserManager(preferencesManager)
//        if (userManager.isLoggedIn()) {
//            /** isLoggedIn True -> MyActivity 이동 */
//            val intent = Intent(this, MyActivity::class.java)
//            startActivity(intent)
//        } else {
//            /** isLoggedIn False -> SignInActivity 이동 */
//            val intent = Intent(this, SignInActivity::class.java)
//            startActivity(intent)
//        }
//        /** MainActivity 종료 */
//        finish()
        setContent {
            ANDANDROIDTheme {
                Surface {
                    WavveNavigation()
                }
            }
        }
    }
}