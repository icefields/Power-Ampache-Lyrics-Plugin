package luci.sixsixsix.powerampache2.lyricsplugin.domain.models

import com.google.gson.Gson

data class SongLyrics(
//    //  remove and include the PrimaryArtist object
//    //    val primaryArtist: PrimaryArtist = PrimaryArtist(),
//    //    val primaryArtists: List<PrimaryArtist> = listOf(),
//    val artistHeaderImageUrl: String = "",
//    val artistId: Int = 0,
//    val artistImageUrl: String = "",
//    val artistName: String = "",
//    val artistUrl: String = "",
//
//    val day: Int = 0,
//    val month: Int = 0,
//    val year: Int = 0,
//    val artistNames: String = "",
//    val fullTitle: String = "",
//    val headerImageThumbnailUrl: String = "",
//    val headerImageUrl: String = "",
//    val id: Int = 0,
//    val primaryArtistNames: String = "",
//    val releaseDateForDisplay: String = "",
//    val releaseDateWithAbbreviatedMonthForDisplay: String = "",
//    val songArtImageThumbnailUrl: String = "",
//    val songArtImageUrl: String = "",
//    val title: String = "",
//    val titleWithFeatured: String = "",

    val lyricsUrl: String = "",
    val lyrics: String = ""
)

fun SongLyrics.toJson(): String = Gson().toJson(this)
fun SongLyrics.isLyricsAvailable() = lyrics.isNotBlank() || lyricsUrl.isNotBlank()