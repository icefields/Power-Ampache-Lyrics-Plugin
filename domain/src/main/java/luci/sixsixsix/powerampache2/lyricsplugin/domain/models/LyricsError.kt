package luci.sixsixsix.powerampache2.lyricsplugin.domain.models

import androidx.annotation.Keep

@Keep
data class LyricsError(
    val code: String,
    val description: String,
    val songTitle: String,
    val albumName: String?,
    val artistName: String
)
