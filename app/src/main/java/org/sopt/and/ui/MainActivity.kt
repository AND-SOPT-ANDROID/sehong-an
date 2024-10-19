package org.sopt.and.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import org.sopt.and.data.PreferencesManager
import org.sopt.and.data.UserManager
import org.sopt.and.ui.screen.myPage.composable.MyActivity
import org.sopt.and.ui.screen.signIn.composable.SignInActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesManager = PreferencesManager(this)
        val userManager = UserManager(preferencesManager)
        if (userManager.isLoggedIn()) {
            /** isLoggedIn True -> MyActivity 이동 */
            val intent = Intent(this, MyActivity::class.java)
            startActivity(intent)
        } else {
            /** isLoggedIn False -> SignInActivity 이동 */
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        /** MainActivity 종료 */
        finish()

    }
}