package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Hit(
    @SerializedName("highlights")
    val highlights: List<Any> = listOf(),
    @SerializedName("index")
    val index: String = "",
    @SerializedName("result")
    val result: Result = Result(),
    @SerializedName("type")
    val type: String = ""
)