package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api

import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.models.SongGeniusDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface MainNetwork {
    //@Headers("Authorization: Bearer $BEARER_TOKEN")
    @GET("/search")
    suspend fun getLyrics(
        @Header("Authorization") bearerToken: String,
        @Query("q") songTitle: String
    ): SongGeniusDto
}
