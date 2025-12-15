package luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Meta(
    @SerializedName("status")
    val status: Int = 0
)