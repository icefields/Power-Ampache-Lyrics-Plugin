package luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Result(
    @SerializedName("annotation_count")
    val annotationCount: Int = 0,
    @SerializedName("api_path")
    val apiPath: String = "",
    @SerializedName("artist_names")
    val artistNames: String? = "",
    @SerializedName("featured_artists")
    val featuredArtists: List<Any>? = listOf(),
    @SerializedName("full_title")
    val fullTitle: String? = "",
    @SerializedName("header_image_thumbnail_url")
    val headerImageThumbnailUrl: String? = "",
    @SerializedName("header_image_url")
    val headerImageUrl: String? = "",
    @SerializedName("id")
    val id: Int = 0,
    @SerializedName("lyrics_owner_id")
    val lyricsOwnerId: Int = 0,
    @SerializedName("lyrics_state")
    val lyricsState: String = "",
    @SerializedName("path")
    val path: String = "",
    @SerializedName("primary_artist")
    val primaryArtist: PrimaryArtist = PrimaryArtist(),
    @SerializedName("primary_artist_names")
    val primaryArtistNames: String? = "",
    @SerializedName("primary_artists")
    val primaryArtists: List<PrimaryArtist> = listOf(),
    @SerializedName("pyongs_count")
    val pyongsCount: Any = Any(),
    @SerializedName("relationships_index_url")
    val relationshipsIndexUrl: String = "",
    @SerializedName("release_date_components")
    val releaseDateComponents: ReleaseDateComponents? = ReleaseDateComponents(),
    @SerializedName("release_date_for_display")
    val releaseDateForDisplay: String? = "",
    @SerializedName("release_date_with_abbreviated_month_for_display")
    val releaseDateWithAbbreviatedMonthForDisplay: String? = "",
    @SerializedName("song_art_image_thumbnail_url")
    val songArtImageThumbnailUrl: String? = "",
    @SerializedName("song_art_image_url")
    val songArtImageUrl: String? = "",
    @SerializedName("stats")
    val stats: Stats = Stats(),
    @SerializedName("title")
    val title: String? = "",
    @SerializedName("title_with_featured")
    val titleWithFeatured: String? = "",
    @SerializedName("url")
    val url: String = ""
)