package org.sopt.and

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import org.sopt.and.data.PreferencesManager
import org.sopt.and.ui.myPage.MyActivity
import org.sopt.and.ui.signIn.SignInActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val preferencesManager = PreferencesManager(this)
        if (preferencesManager.isLoggedIn()) {
            // isLoggedIn True -> MyActivity 이동
            val intent = Intent(this, MyActivity::class.java)
            startActivity(intent)
        } else {
            // isLoggedIn False -> SignInActivity 이동
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }
        // MainActivity 종료
        finish()
    }
}