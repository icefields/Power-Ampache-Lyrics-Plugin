package luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase

import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import javax.inject.Inject

class ClearStoredLyricsUseCase @Inject constructor(
    private val lyricsFetcher: LyricsFetcher
) {
    suspend operator fun invoke() = lyricsFetcher.clearStoredLyrics()
}
