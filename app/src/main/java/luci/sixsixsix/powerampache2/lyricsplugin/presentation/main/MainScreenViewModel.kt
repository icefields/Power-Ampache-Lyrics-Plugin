package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase.ClearStoredLyricsUseCase
import luci.sixsixsix.powerampache2.lyricsplugin.domain.usecase.FetchLyricsUseCase
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val fetchLyricsUseCase: FetchLyricsUseCase,
    private val clearStoredLyricsUseCase: ClearStoredLyricsUseCase,
    private val sharedPreferencesManager: SharedPreferencesManager
): ViewModel() {
    val tokenStateFlow = sharedPreferencesManager.tokenStateFlow

    fun setToken(newToken: String) {
        sharedPreferencesManager.token = newToken
    }

    fun clearStoredLyrics() = viewModelScope.launch {
        clearStoredLyricsUseCase()
    }

    fun fetchLyricsDebug() = fetchLyricsUseCase("dream on", "", "aerosmith") { lyrics ->
        println("aaaa callback resp:$lyrics")
    }
}
