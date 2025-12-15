package luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics

import luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.models.PowerLyricsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface PowerLyricsMainNetwork {
    @GET("/api/lyrics")
    suspend fun getLyrics(
        @Query("artist_name") artistName: String,
        @Query("track_name") trackName: String,
        @Query("album_name") albumName: String? = null,
    ): PowerLyricsDto
}
