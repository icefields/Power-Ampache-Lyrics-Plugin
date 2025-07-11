package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class PrimaryArtist(
    @SerializedName("api_path")
    val apiPath: String? = "",
    @SerializedName("header_image_url")
    val headerImageUrl: String? = "",
    @SerializedName("id")
    val id: Int? = 0,
    @SerializedName("image_url")
    val imageUrl: String? = "",
    @SerializedName("is_meme_verified")
    val isMemeVerified: Boolean = false,
    @SerializedName("is_verified")
    val isVerified: Boolean = false,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("url")
    val url: String? = ""
)