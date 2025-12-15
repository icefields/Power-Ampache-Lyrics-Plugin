package luci.sixsixsix.powerampache2.lyricsplugin.data.api

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.PowerLyricsMainNetwork
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.models.toLyricsError
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.models.toSongLyrics
import luci.sixsixsix.powerampache2.lyricsplugin.data.di.GeniusApi
import luci.sixsixsix.powerampache2.lyricsplugin.data.di.PowerLyricsApi
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.MainNetwork
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.local.PluginDatabase
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.local.entity.toLyricsEntity
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.local.entity.toSongLyrics
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.models.toSongGenius
import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.exceptions.LyricsException
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics
import javax.inject.Inject

/**
 * Copyright (C) 2025  Antonio Tari
 *
 * This file is a part of Power Ampache 2
 * Ampache Android client application
 * @author Antonio Tari
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
class LyricsFetcherImpl @Inject constructor(
    @GeniusApi private val geniusApi: MainNetwork,
    @PowerLyricsApi private val powerLyricsApi: PowerLyricsMainNetwork,
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
                println("lyrics DB for $songTitle - $artistName")
            } else {
                val lyrics = try {
                    val lyricsDto = powerLyricsApi.getLyrics(
                        trackName = songTitle,
                        artistName = artistName
                    )

                    val error = lyricsDto.error?.toLyricsError(
                        songTitle = songTitle,
                        albumName = albumTitle,
                        artistName = artistName
                    )

                    lyricsDto.toSongLyrics().also {
                        val isLyrics = it.lyrics.isNotBlank() || it.lyricsUrl.isNotBlank()
                        if (isLyrics && songTitle.isNotBlank() && artistName.isNotBlank()) {
                            println("lyrics fetched for $songTitle - $artistName")
                            db.dao.updateLyrics(it.toLyricsEntity(artistName = artistName, songTitle = songTitle))
                        } else {
                            throw error?.let { err ->
                                LyricsException(err)
                            } ?: Exception("lyrics for song $songTitle - $artistName not found in Power Lyrics")
                        }
                    }
                } catch (e: Exception) {
                    println("fetchLyrics ${e.localizedMessage}")
                    fetchGeniusLyrics(songTitle, artistName)
                }
                callback(lyrics)
            }
        }
    }

    private suspend fun fetchGeniusLyrics(songTitle: String, artistName: String)  = try {
        geniusApi.getLyrics(
            bearerToken = "Bearer ${sharedPreferencesManager.token}",
            songTitle = songTitle
        ).toSongGenius(songTitle, artistName).also {
            val isLyrics = it.lyrics.isNotBlank() || it.lyricsUrl.isNotBlank()
            if (isLyrics && songTitle.isNotBlank() && artistName.isNotBlank()) {
                db.dao.updateLyrics(it.toLyricsEntity(artistName = artistName, songTitle = songTitle))
            }
        }
    } catch (e: Exception) {
        println("fetchGeniusLyrics ${e.localizedMessage}\n${e.stackTraceToString()}\n\n")
        null
    }

    override suspend fun clearStoredLyrics() {
        db.dao.clearLyrics()
    }
}