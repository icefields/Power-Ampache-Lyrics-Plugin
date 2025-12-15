package luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics

@Keep
data class PowerLyricsDto(
    @SerializedName("album_name")
    val albumName: String? = null,
    @SerializedName("artist_name")
    val artistName: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("fetched_at")
    val fetchedAt: String? = null,
    @SerializedName("id")
    val id: Int? = null,
    @SerializedName("instrumental")
    val instrumental: Boolean? = null,
    @SerializedName("mapped_lyrics")
    val mappedLyrics: Map<Int, String>? = null,
    @SerializedName("plain_lyrics")
    val plainLyrics: String? = null,
    @SerializedName("synced_lyrics")
    val syncedLyrics: String? = null,
    @SerializedName("track_name")
    val trackName: String? = null,
    @SerializedName("error")
    val error: PowerLyricsErrorDto? = null
)

fun PowerLyricsDto.toSongLyrics() = SongLyrics(
    lyrics = plainLyrics?.replace("\n", "<br>") ?: ""
)
