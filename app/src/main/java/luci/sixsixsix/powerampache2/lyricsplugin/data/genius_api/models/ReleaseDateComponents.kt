package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ReleaseDateComponents(
    @SerializedName("day")
    val day: Int? = 0,
    @SerializedName("month")
    val month: Int? = 0,
    @SerializedName("year")
    val year: Int? = 0
)