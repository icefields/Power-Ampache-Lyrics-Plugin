package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local.entity.LyricsEntity


@Dao
interface PluginDao {
    @Query("""SELECT * FROM LyricsEntity""")
    fun getAllSongLyrics(): Flow<List<LyricsEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateLyrics(lyricsEntity: LyricsEntity)

    @Query("DELETE FROM LyricsEntity")
    suspend fun clearLyrics()

    @Query("""SELECT * FROM LyricsEntity WHERE LOWER(songTitle) == LOWER(:songTitle) AND LOWER(artistName) == LOWER(:artistName)""")
    suspend fun getSongLyrics(songTitle: String, artistName: String): LyricsEntity?
}
