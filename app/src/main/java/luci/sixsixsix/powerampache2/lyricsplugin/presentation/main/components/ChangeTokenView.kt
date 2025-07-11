package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import luci.sixsixsix.powerampache2.lyricsplugin.R
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.BEARER_TOKEN
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.mainFontSize
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.screenPadding

@Composable
fun ChangeTokenView(
    modifier: Modifier = Modifier,
    token: String,
    onTokenChange: (String) -> Unit
) {
    // Do not visualize token if using the default one.
    val tokenVisibleValue = if (token != BEARER_TOKEN) token.value else ""

    Column(modifier) {
        Text(
            text = stringResource(R.string.token_insert_title),
            fontSize = mainFontSize,
            modifier = Modifier.fillMaxWidth()
                .padding(top = screenPadding),
            textAlign = TextAlign.Start
        )
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = tokenVisibleValue,
            onValueChange = onTokenChange,
            label = { Text(stringResource(R.string.token_insert_label)) }
        )
    }
}
