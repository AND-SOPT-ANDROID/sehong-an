package org.sopt.and

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences = getSharedPreferences("MyAppPrefs", Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("IS_LOGGED_IN", false)
        if (isLoggedIn) {
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