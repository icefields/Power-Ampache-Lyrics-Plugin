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
package luci.sixsixsix.powerampache2.lyricsplugin.data.local

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.BASE_URL_POWER_LYRICS
import luci.sixsixsix.powerampache2.lyricsplugin.data.di.WeakContext
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.BEARER_TOKEN_GENIUS
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferenceDelegate
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import javax.inject.Inject
import javax.inject.Singleton

private const val KEY_BEARER_TOKEN = "luci.sixsixsix.powerampache2.plugin.lyrics.data.KEY_BEARER_TOKEN"
private const val KEY_SELECTED_MIRROR = "luci.sixsixsix.powerampache2.plugin.lyrics.data.KEY_SELECTED_MIRROR"

@Singleton
class SharedPreferencesManagerImpl @Inject constructor(
    private val weakContext: WeakContext,
): SharedPreferencesManager, SharedPreferenceDelegate by SharedPreferenceDelegateImpl(weakContext) {

    private val _tokenStateFlow = MutableStateFlow(token)
    override val tokenStateFlow: StateFlow<String> = _tokenStateFlow

    private val _selectedMirrorFlow = MutableStateFlow(selectedMirror)
    override val selectedMirrorFlow: StateFlow<String> = _selectedMirrorFlow

    override var token: String
        get() = getString(KEY_BEARER_TOKEN, BEARER_TOKEN_GENIUS).run {
            if (isNullOrBlank()) BEARER_TOKEN_GENIUS else this
        }
        set(value) = setString(KEY_BEARER_TOKEN, value).also {
            _tokenStateFlow.value = token
        }

    override var selectedMirror: String
        get() = getString(KEY_SELECTED_MIRROR, BASE_URL_POWER_LYRICS).run {
            if (isNullOrBlank()) BASE_URL_POWER_LYRICS else this
        }
        set(value) = setString(KEY_SELECTED_MIRROR, value).also {
            _selectedMirrorFlow.value = selectedMirror
        }
}
