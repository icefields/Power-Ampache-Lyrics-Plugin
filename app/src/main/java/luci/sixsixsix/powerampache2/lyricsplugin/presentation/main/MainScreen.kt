package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import luci.sixsixsix.powerampache2.lyricsplugin.R
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.BEARER_TOKEN
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components.ChangeTokenView
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components.ClearDbButton
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components.MainTopBar

val mainFontSize = 16.sp
val smallFontSize = 12.sp
val screenPadding
    @Composable get() = dimensionResource(R.dimen.screen_padding)


@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel = hiltViewModel()
) {
    val token = mainScreenViewModel.tokenStateFlow.collectAsStateWithLifecycle()
    MainScreenContent(
        modifier = modifier,
        token = token.value,
        onTokenChange = mainScreenViewModel::setToken,
        onClearLyrics = mainScreenViewModel::clearStoredLyrics
    )
}

@Composable
private fun MainScreenContent(
    modifier: Modifier = Modifier,
    token: String,
    onTokenChange: (String) -> Unit,
    onClearLyrics: () -> Unit
) {
    Scaffold(
        modifier = modifier,
        topBar = { MainTopBar(Modifier.fillMaxWidth().background(MaterialTheme.colors.primary)) }
    ) {
        Box(Modifier.fillMaxSize().padding(it)) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = screenPadding).padding(horizontal = screenPadding)

            ) {
                ChangeTokenView(Modifier.fillMaxWidth(), token = token, onTokenChange)
                Divider(Modifier.fillMaxWidth().padding(vertical = screenPadding))
                ClearDbButton(Modifier.fillMaxWidth(), onClearLyrics)
                Divider(Modifier.fillMaxWidth().padding(vertical = screenPadding))
                //FetchLyricsDebugButton(mainScreenViewModel)
            }
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

@Preview
@Composable
fun previewMainScreen(){
    MainScreenContent(
        token = "abcde666", onTokenChange =  { }, onClearLyrics =  { })
}