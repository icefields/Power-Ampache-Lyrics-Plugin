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
package luci.sixsixsix.powerampache2.lyricsplugin

import android.app.Service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.os.Message
import android.os.Messenger
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import luci.sixsixsix.powerampache2.lyricsplugin.common.KEY_REQUEST_ALBUM_TITLE
import luci.sixsixsix.powerampache2.lyricsplugin.common.KEY_REQUEST_ARTIST_NAME
import luci.sixsixsix.powerampache2.lyricsplugin.common.KEY_REQUEST_JSON
import luci.sixsixsix.powerampache2.lyricsplugin.common.KEY_REQUEST_SONG_TITLE
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.isLyricsAvailable
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.toJson
import luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase.FetchLyricsUseCase
import org.json.JSONObject
import javax.inject.Inject

@AndroidEntryPoint
class LyricsFetcherService : Service() {

    @Inject
    lateinit var fetchLyricsUseCase: FetchLyricsUseCase

    @Inject
    lateinit var applicationCoroutineScope: CoroutineScope

    private val messenger = Messenger(object : Handler(Looper.getMainLooper()) {
        override fun handleMessage(msg: Message) {
            val requestStr = msg.data.getString(KEY_REQUEST_JSON) ?: return
            val request = JSONObject(requestStr)

            val song = request.optString(KEY_REQUEST_SONG_TITLE)
            val album = request.optString(KEY_REQUEST_ALBUM_TITLE)
            val artist = request.optString(KEY_REQUEST_ARTIST_NAME)

            val replyTo = msg.replyTo ?: return

            fetchLyricsUseCase(song, album, artist) { songLyrics ->
                if (songLyrics?.isLyricsAvailable() == true) {
                    val reply = Message.obtain().apply {
                        data = Bundle().apply {
                            putString(KEY_REQUEST_JSON, songLyrics.toJson())
                        }
                    }
                    replyTo.send(reply)
                }

            }
        }
    })

    override fun onBind(intent: Intent?): IBinder {
        return messenger.binder
    }
}
