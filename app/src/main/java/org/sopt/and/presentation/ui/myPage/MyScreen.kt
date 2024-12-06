package org.sopt.and.presentation.ui.myPage

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.sopt.and.R
import org.sopt.and.presentation.components.container.ProfileContainer
import org.sopt.and.presentation.components.text.ColumnIconText
import org.sopt.and.presentation.theme.WavveTheme

@Composable
fun MyScreen(
    navController: androidx.navigation.NavHostController,
    modifier: Modifier,
    viewModel: MyScreenViewModel = hiltViewModel()
) {
    val context = LocalContext.current
    val hobby by viewModel.hobby.collectAsStateWithLifecycle()
    val effects = viewModel.effect.collectAsState(initial = null).value
    LaunchedEffect(effects) {
        when (effects) {
            is MyPageContract.Effect.NavigateToLogin -> {
                navController.navigate("login")
            }

            is MyPageContract.Effect.ShowErrorMessage -> {
                Toast.makeText(context, effects.message, Toast.LENGTH_SHORT).show()
            }

            else -> {}
        }
    }
    /** 스크롤이 가능하도록 scrollState 설정 */
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(WavveTheme.colors.gray_5)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveTheme.colors.gray_1)
                .padding(top = 15.dp, start = 15.dp, end = 15.dp, bottom = 30.dp),
        ) {
            ProfileContainer(
                hobby,
            ) {
                viewModel.logout()
            }
        }
        Text(
            text = stringResource(id = R.string.my_first_payment),
            color = WavveTheme.colors.gray_3,
            modifier = Modifier
                .background(WavveTheme.colors.gray_1)
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveTheme.colors.gray_1)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.purchase),
                color = WavveTheme.colors.white,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.go_purchase_page),
                tint = WavveTheme.colors.white,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(1.dp))
        Text(
            text = stringResource(id = R.string.no_have_pass),
            color = WavveTheme.colors.gray_3,
            modifier = Modifier
                .background(WavveTheme.colors.gray_1)
                .fillMaxWidth()
                .padding(top = 15.dp, start = 15.dp, end = 15.dp)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(WavveTheme.colors.gray_1)
                .padding(horizontal = 15.dp)
        ) {
            Text(
                text = stringResource(id = R.string.purchase),
                color = WavveTheme.colors.white,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowRight,
                contentDescription = stringResource(id = R.string.go_purchase_page),
                tint = WavveTheme.colors.white,
                modifier = Modifier.padding(bottom = 24.dp)
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.total_program),
            color = WavveTheme.colors.white,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            style = WavveTheme.typography.body2
        )
        ColumnIconText(
            text = stringResource(id = R.string.my_no_history),
            iconResId = R.drawable.exclamation_mark_icon,
            iconContentDescription = "exclamation_mark_icon"
        )
        Text(
            text = stringResource(id = R.string.my_interest_program),
            color = WavveTheme.colors.white,
            modifier = Modifier
                .padding(horizontal = 20.dp),
            fontWeight = FontWeight.Bold,
            style = WavveTheme.typography.body1
        )
        ColumnIconText(
            text = stringResource(id = R.string.my_no_interest_program),
            iconResId = R.drawable.exclamation_mark_icon,
            iconContentDescription = "exclamation_mark_icon"
        )
    }
}