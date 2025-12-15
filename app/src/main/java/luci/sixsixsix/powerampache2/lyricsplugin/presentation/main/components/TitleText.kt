package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components

import androidx.annotation.StringRes
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import luci.sixsixsix.powerampache2.lyricsplugin.R

@Composable fun TitleText(@StringRes stringRes: Int, modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = stringResource(stringRes),
        style = MaterialTheme.typography.titleMedium.copy(
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    )
}
