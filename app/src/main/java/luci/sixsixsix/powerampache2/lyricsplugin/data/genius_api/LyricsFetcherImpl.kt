package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api

import com.google.gson.Gson
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.BEARER_TOKEN
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models.SongGeniusDto
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models.toSongGenius
import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URLEncoder

class LyricsFetcherImpl : LyricsFetcher {
    override fun fetchLyrics(songTitle: String, albumTitle: String, artistName: String, callback: (SongLyrics?) -> Unit) {
        makeRequest(artistName, songTitle) {
            val songGenius = try {
                Gson().fromJson(it, SongGeniusDto::class.java).toSongGenius(songTitle, artistName)
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            callback(songGenius)
        }
    }

    fun makeRequest(artistName: String, songTitle: String, callback: (String) -> Unit) {
        val client = OkHttpClient()
        val request = Request.Builder()
            .url("https://api.genius.com/search?q=${encodeString(artistName)}%20${encodeString(songTitle)}")
            .addHeader("Authorization", "Bearer $BEARER_TOKEN")
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
                callback("")
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!it.isSuccessful) {
                        callback("")
                    } else {
                        callback(it.body?.string() ?: "")
                    }
                }
            }
        })
    }

    private fun encodeString(str: String) = URLEncoder.encode(str, "UTF-8").replace("+", "%20")
}
