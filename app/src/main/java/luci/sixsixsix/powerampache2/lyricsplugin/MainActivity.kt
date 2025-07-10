package luci.sixsixsix.powerampache2.lyricsplugin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import dagger.hilt.android.AndroidEntryPoint
import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var lyricsFetcher: LyricsFetcher

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                ) {
                    Button(
                        onClick = {
                            lyricsFetcher.fetchLyrics("dream on", "", "aerosmith") { lyrics ->
                                println("aaaa callback resp:$lyrics")
                            }
                        }
                    ) { Text("Fetch Lyrics Test", textAlign = TextAlign.Center) }
                }
            }
        }
    }
}
