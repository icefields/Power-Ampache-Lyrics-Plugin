package luci.sixsixsix.powerampache2.lyricsplugin.domain.local

import kotlinx.coroutines.flow.StateFlow

interface SharedPreferencesManager {
    // most apis require an auth token
    var token: String
    val tokenStateFlow: StateFlow<String>
}
