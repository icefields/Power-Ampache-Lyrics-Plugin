package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import luci.sixsixsix.powerampache2.lyricsplugin.R
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.screenPadding

@Composable
fun MainTopBar(modifier: Modifier = Modifier) {
    Column(modifier) {
        Spacer(Modifier
            .fillMaxWidth()
            .height(WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
            .background(MaterialTheme.colors.onBackground)
        )

        Spacer(Modifier.Companion.height(screenPadding))
        Text(
            text = stringResource(R.string.app_name_topBar),
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary,
            modifier = Modifier.fillMaxWidth().padding(horizontal = screenPadding),
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.Companion.height(screenPadding))
    }
}