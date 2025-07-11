package luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local

import androidx.room.Database
import androidx.room.RoomDatabase
import luci.sixsixsix.powerampache2.lyricsplugin.data.common.DATABASE_VERSION
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.local.entity.LyricsEntity

@Database(
    entities = [
        LyricsEntity::class,
    ], version = DATABASE_VERSION,
    exportSchema = true
)
abstract class PluginDatabase: RoomDatabase() {
    abstract val dao: PluginDao
}
