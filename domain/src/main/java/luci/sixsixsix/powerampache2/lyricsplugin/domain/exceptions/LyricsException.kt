package luci.sixsixsix.powerampache2.lyricsplugin.domain.exceptions

import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.LyricsError

class LyricsException(error: LyricsError):
    Exception("LyricsException(code: ${error.code}):\n${error.description}\nsongTitle: ${error.songTitle}, artistName: ${error.artistName}, albumName: ${error.albumName}")