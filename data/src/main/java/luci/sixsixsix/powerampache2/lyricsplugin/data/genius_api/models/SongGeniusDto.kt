package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.SongLyrics

@Keep
data class SongGeniusDto(
    @SerializedName("meta")
    val meta: Meta = Meta(),
    @SerializedName("response")
    val response: Response = Response()
)

fun SongGeniusDto.toSongGenius(songTitle: String, artistName: String): SongLyrics {
    for (hit in response.hits) {
        val isArtistMatch = (artistName == hit.result.primaryArtist.name ||
                hit.result.artistNames?.contains(artistName) == true)
        val isSongMatch = (songTitle == hit.result.title ||
                hit.result.fullTitle?.contains(songTitle) == true ||
                hit.result.titleWithFeatured?.contains(songTitle) == true)
        if (isArtistMatch && isSongMatch) {
            return hit.result.run {
                SongLyrics(lyricsUrl = url)
            }
        }
    }
    throw Exception("No Lyrics Found")
}



//                    artistHeaderImageUrl = primaryArtist.headerImageUrl ?: "",
//                    artistId = primaryArtist.id ?: 0,
//                    artistImageUrl = primaryArtist.imageUrl ?: "",
//                    artistName = primaryArtist.name ?: "",
//                    artistUrl = primaryArtist.url ?: "",
//                    day = releaseDateComponents?.day ?: 0,
//                    month = releaseDateComponents?.month ?: 0,
//                    year = releaseDateComponents?.year ?: 0,
//                    artistNames = artistNames ?: "",
//                    fullTitle = fullTitle ?: "",
//                    headerImageThumbnailUrl = headerImageThumbnailUrl ?: "",
//                    headerImageUrl = headerImageUrl ?: "",
//                    id = id,
//                    primaryArtistNames = primaryArtistNames ?: "",
//                    releaseDateForDisplay = releaseDateForDisplay ?: "",
//                    releaseDateWithAbbreviatedMonthForDisplay = releaseDateWithAbbreviatedMonthForDisplay ?: "",
//                    songArtImageThumbnailUrl = songArtImageThumbnailUrl ?: "",
//                    songArtImageUrl = songArtImageUrl ?: "",
//                    title = title ?: "",
//                    titleWithFeatured = titleWithFeatured ?: "",




//    response.hits.firstOrNull()?.result?.run {
//    SongGenius(
//        artistHeaderImageUrl = primaryArtist.headerImageUrl ?: "",
//        artistId = primaryArtist.id ?: 0,
//        artistImageUrl = primaryArtist.imageUrl ?: "",
//        artistName = primaryArtist.name ?: "",
//        artistUrl = primaryArtist.url ?: "",
//        day = releaseDateComponents?.day ?: 0,
//        month = releaseDateComponents?.month ?: 0,
//        year = releaseDateComponents?.year ?: 0,
//        artistNames = artistNames ?: "",
//        fullTitle = fullTitle ?: "",
//        headerImageThumbnailUrl = headerImageThumbnailUrl ?: "",
//        headerImageUrl = headerImageUrl ?: "",
//        id = id,
//        primaryArtistNames = primaryArtistNames ?: "",
//        releaseDateForDisplay = releaseDateForDisplay ?: "",
//        releaseDateWithAbbreviatedMonthForDisplay = releaseDateWithAbbreviatedMonthForDisplay ?: "",
//        songArtImageThumbnailUrl = songArtImageThumbnailUrl ?: "",
//        songArtImageUrl = songArtImageUrl ?: "",
//        title = title ?: "",
//        titleWithFeatured = titleWithFeatured ?: "",
//        lyricsUrl = url
//    )
//}