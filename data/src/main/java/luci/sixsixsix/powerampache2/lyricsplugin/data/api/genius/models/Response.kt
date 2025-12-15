package luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Response(
    @SerializedName("hits")
    val hits: List<Hit> = listOf()
)