package luci.sixsixsix.powerampache2.lyricsplugin.data.api.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics

@Entity
data class LyricsEntity(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "lyricsUrl", defaultValue = "")
    val lyricsUrl: String = "",
    @ColumnInfo(name = "lyrics", defaultValue = "")
    val lyrics: String = "",
    @ColumnInfo(name = "artistName", defaultValue = "")
    val artistName: String = "",
    @ColumnInfo(name = "songTitle", defaultValue = "")
    val songTitle: String = ""
)

fun LyricsEntity.toSongLyrics() = SongLyrics(
    lyrics = lyrics,
    lyricsUrl = lyricsUrl
)

fun SongLyrics.toLyricsEntity(artistName: String, songTitle: String) = LyricsEntity(
    artistName = artistName,
    songTitle = songTitle,
    lyrics = lyrics,
    lyricsUrl = lyricsUrl,
    id = "$artistName|$songTitle"
)
