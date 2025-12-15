package luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class Stats(
    @SerializedName("hot")
    val hot: Boolean = false,
    @SerializedName("unreviewed_annotations")
    val unreviewedAnnotations: Int = 0
)