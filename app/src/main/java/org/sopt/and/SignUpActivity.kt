package org.sopt.and

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import org.sopt.and.components.TopBarCustom
import org.sopt.and.ui.theme.ANDANDROIDTheme

class SignUpActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            ANDANDROIDTheme {
                val context = LocalContext.current
                Scaffold(
                    topBar = {
                        TopBarCustom (
                            titleContent = {
                                Text(
                                    text = "회원가입"
                                )
                            },
                            hasLeftIcon = false,
                            hasRightIcon = true,
                            onCloseClicked = {
                                if (context is SignUpActivity) {
                                    context.finish()
                                }
                            }
                        )
                    },
                    content = { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            SignUpPage()
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun SignUpPage() {
    // 회원가입 페이지 UI 구성
    Column(

    ) {

    }
}

@Preview(showBackground = true)
@Composable
fun SignUpPagePreview() {
    ANDANDROIDTheme {
        SignUpPage()
    }
}