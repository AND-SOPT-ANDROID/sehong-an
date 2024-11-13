package org.sopt.and.ui.components.container

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.sopt.and.R
import org.sopt.and.ui.theme.WavveTheme

@Composable
fun ProfileContainer(
//    modifier: Modifier = Modifier,
    profileName: String,
    onLogoutClick: () -> Unit,
) {
    Row(
        modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_icon),
            contentDescription = "profile_logo",
            modifier = Modifier.height(60.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = profileName,
            color = WavveTheme.colors.white,
        )
        Text(
            text = "님",
            color = WavveTheme.colors.white,
        )
        Spacer(modifier = Modifier.weight(1f))
        Icon(
            imageVector = Icons.Outlined.Notifications,
            contentDescription = "알림",
            tint = WavveTheme.colors.white,
            modifier = Modifier.padding(bottom = 4.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Icon(
            imageVector = Icons.Outlined.Settings,
            contentDescription = "세팅",
            tint = WavveTheme.colors.white,
            modifier = Modifier
                .padding(bottom = 4.dp)
                .clickable { onLogoutClick() }
        )
    }
}

@Preview
@Composable
fun ProfileContainerPreview() {
    ProfileContainer(
        profileName = "안세홍"
    ) {

    }
}
