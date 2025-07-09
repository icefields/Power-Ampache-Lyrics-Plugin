package luci.sixsixsix.powerampache2.lyricsplugin

import android.os.Bundle
import android.widget.EditText
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.LyricsFetcherImpl

class MainActivity : ComponentActivity() {
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
                    Text(
                        "Main Activity Lyrics",
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .clickable {
                                LyricsFetcherImpl().fetchLyrics("dream on", "", "aerosmith") { lyrics ->
                                    println("aaaa callback resp:$lyrics")
                                }
                            }
                    )
                }
            }
        }
    }
}
