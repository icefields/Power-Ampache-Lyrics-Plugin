package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import luci.sixsixsix.powerampache2.lyricsplugin.R
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.mainFontSize
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.smallFontSize

@Composable
fun ClearDbButton(
    modifier: Modifier = Modifier,
    onClearLyrics: () -> Unit
) {
    Column(modifier) {
        Button(onClick = onClearLyrics) {
            Text(
                text = stringResource(R.string.clear_stored_title),
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                textAlign = TextAlign.Start,
                fontSize = mainFontSize
            )
        }
        Text(
            text = stringResource(R.string.clear_stored_subtitle),
            fontSize = smallFontSize,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            textAlign = TextAlign.Start
        )
    }
}
