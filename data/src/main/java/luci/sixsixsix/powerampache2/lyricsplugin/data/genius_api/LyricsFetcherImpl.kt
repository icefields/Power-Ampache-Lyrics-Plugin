package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local.PluginDatabase
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local.entity.toLyricsEntity
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local.entity.toSongLyrics
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models.toSongGenius
import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics
import javax.inject.Inject

class LyricsFetcherImpl @Inject constructor(
    private val api: MainNetwork,
    private val db: PluginDatabase,
    private val sharedPreferencesManager: SharedPreferencesManager,
    private val applicationCoroutineScope: CoroutineScope
): LyricsFetcher {
    private var fetchJob: Job? = null

    override fun fetchLyrics(
        songTitle: String,
        albumTitle: String,
        artistName: String,
        callback: (SongLyrics?) -> Unit
    ) {
        fetchJob?.cancel()
        fetchJob = applicationCoroutineScope.launch {
            val lyricsDb = db.dao.getSongLyrics(songTitle = songTitle, artistName = artistName)
            if(lyricsDb != null) {
                callback(lyricsDb.toSongLyrics())
            } else {
                val lyrics = try {
                    api.getLyrics(
                        bearerToken = "Bearer ${sharedPreferencesManager.token}",
                        songTitle = songTitle
                    ).toSongGenius(songTitle, artistName).also {
                        val isLyrics = it.lyrics.isNotBlank() || it.lyricsUrl.isNotBlank()
                        if (isLyrics && songTitle.isNotBlank() && artistName.isNotBlank()) {
                            db.dao.updateLyrics(it.toLyricsEntity(artistName = artistName, songTitle = songTitle))
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
                callback(lyrics)
            }
        }
    }

    override suspend fun clearStoredLyrics() {
        db.dao.clearLyrics()
    }
}
