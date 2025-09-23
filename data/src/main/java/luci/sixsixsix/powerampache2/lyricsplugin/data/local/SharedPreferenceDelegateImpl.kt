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

import android.content.Context
import luci.sixsixsix.powerampache2.lyricsplugin.data.di.WeakContext
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferenceDelegate

private const val KEY_SETTINGS_PREFERENCE = "luci.sixsixsix.powerampache2.plugin.lyrics.data.KEY_SETTINGS_PREFERENCE"

class SharedPreferenceDelegateImpl (private val weakContext: WeakContext):
    SharedPreferenceDelegate {
    private fun getSharedPreferences() =
        weakContext.get()?.getSharedPreferences(KEY_SETTINGS_PREFERENCE, Context.MODE_PRIVATE)
    
    override fun getString(key: String, defaultValue: String) = getSharedPreferences()?.let { sp ->
        sp.getString(key, defaultValue)
    } ?: defaultValue

    override fun setString(key: String, value: String) = getSharedPreferences()?.edit()?.run {
        putString(key, value)
        apply()
    } ?: Unit

    override fun getInt(key: String, defaultValue: Int) = getSharedPreferences()?.let { sp ->
        sp.getInt(key, defaultValue)
    } ?: defaultValue

    override fun setInt(key: String, value: Int) = getSharedPreferences()?.edit()?.run {
        putInt(key, value)
        apply()
    } ?: Unit

    override fun getBool(key: String, defaultValue: Boolean) = getSharedPreferences()?.let { sp ->
        sp.getBoolean(key, defaultValue)
    } ?: defaultValue

    override fun setBool(key: String, value: Boolean) = getSharedPreferences()?.edit()?.run {
        putBoolean(key, value)
        apply()
    } ?: Unit
}
