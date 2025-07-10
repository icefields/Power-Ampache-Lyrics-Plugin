package luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase

import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics
import javax.inject.Inject

class FetchLyricsUseCase @Inject constructor(
    private val lyricsFetcher: LyricsFetcher
) {
    operator fun invoke(
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (SongLyrics?) -> Unit
    ) = lyricsFetcher.fetchLyrics(songTitle, albumTitle, artistName, callback)
}
