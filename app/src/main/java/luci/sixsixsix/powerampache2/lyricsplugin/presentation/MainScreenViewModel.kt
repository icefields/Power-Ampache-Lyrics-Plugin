package luci.sixsixsix.powerampache2.lyricsplugin.presentation

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase.FetchLyricsUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val fetchLyricsUseCase: FetchLyricsUseCase,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    val tokenStateFlow = sharedPreferencesManager.tokenStateFlow

    fun setToken(newToken: String) {
        sharedPreferencesManager.token = newToken
    }

    fun fetchLyricsDebug() = fetchLyricsUseCase("dream on", "", "aerosmith") { lyrics ->
        println("aaaa callback resp:$lyrics")
    }
}
