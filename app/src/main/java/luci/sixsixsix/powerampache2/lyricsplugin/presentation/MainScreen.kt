package luci.sixsixsix.powerampache2.lyricsplugin.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import luci.sixsixsix.powerampache2.lyricsplugin.R

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val token = mainScreenViewModel.tokenStateFlow.collectAsStateWithLifecycle()
    val screenPadding = dimensionResource(R.dimen.screen_padding)

    Scaffold(modifier = modifier.padding(WindowInsets.systemBars.asPaddingValues())) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Text(
                text = stringResource(R.string.token_insert_title),
                modifier = Modifier.fillMaxWidth()
                    .padding(top = screenPadding).padding(horizontal = screenPadding),
                textAlign = TextAlign.Start
            )
            TextField(
                modifier = Modifier.padding(screenPadding),
                value = token.value,
                onValueChange = mainScreenViewModel::setToken,
                label = { Text(stringResource(R.string.token_insert_label)) }
            )

            //FetchLyricsDebugButton(mainScreenViewModel)
        }
    }
}

@Composable
private fun FetchLyricsDebugButton(mainScreenViewModel: MainScreenViewModel) {
    Button(
        onClick = {
            mainScreenViewModel.fetchLyricsDebug()
        }
    ) { Text("Fetch Lyrics Test", textAlign = TextAlign.Center) }
}
