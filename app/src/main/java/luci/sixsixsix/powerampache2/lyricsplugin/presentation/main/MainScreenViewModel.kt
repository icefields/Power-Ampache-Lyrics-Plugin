/**
 * Copyright (C) 2025  Antonio Tari
 *
 * This file is a part of Power Ampache 2
 * Ampache Android client application
 * @author Antonio Tari
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 */
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
