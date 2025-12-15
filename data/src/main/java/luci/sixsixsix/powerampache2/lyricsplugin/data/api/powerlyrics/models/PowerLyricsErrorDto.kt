package luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import luci.sixsixsix.powerampache2.lyricsplugin.domain.models.LyricsError

@Keep
data class PowerLyricsErrorDto(
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("description")
    val description: String? = null
)

fun PowerLyricsErrorDto.toLyricsError(
    songTitle: String,
    albumName: String?,
    artistName: String
) = LyricsError(
    code = code ?: "",
    description = description ?: "",
    artistName = artistName,
    albumName = albumName,
    songTitle = songTitle
)
