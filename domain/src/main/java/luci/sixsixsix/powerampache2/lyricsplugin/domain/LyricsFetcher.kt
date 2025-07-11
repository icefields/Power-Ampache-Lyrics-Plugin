package luci.sixsixsix.powerampache2.lyricsplugin.domain

import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics

interface LyricsFetcher {
    fun fetchLyrics(
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (SongLyrics?) -> Unit
    )

    suspend fun clearStoredLyrics()
}
