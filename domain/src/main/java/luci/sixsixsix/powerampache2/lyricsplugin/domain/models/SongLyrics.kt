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
package luci.sixsixsix.powerampache2.lyricsplugin.domain.models

import com.google.gson.Gson

data class SongLyrics(
//    //  remove and include the PrimaryArtist object
//    //    val primaryArtist: PrimaryArtist = PrimaryArtist(),
//    //    val primaryArtists: List<PrimaryArtist> = listOf(),
//    val artistHeaderImageUrl: String = "",
//    val artistId: Int = 0,
//    val artistImageUrl: String = "",
//    val artistName: String = "",
//    val artistUrl: String = "",
//
//    val day: Int = 0,
//    val month: Int = 0,
//    val year: Int = 0,
//    val artistNames: String = "",
//    val fullTitle: String = "",
//    val headerImageThumbnailUrl: String = "",
//    val headerImageUrl: String = "",
//    val id: Int = 0,
//    val primaryArtistNames: String = "",
//    val releaseDateForDisplay: String = "",
//    val releaseDateWithAbbreviatedMonthForDisplay: String = "",
//    val songArtImageThumbnailUrl: String = "",
//    val songArtImageUrl: String = "",
//    val title: String = "",
//    val titleWithFeatured: String = "",

    val lyricsUrl: String = "",
    val lyrics: String = ""
)

fun SongLyrics.toJson(): String = Gson().toJson(this)
fun SongLyrics.isLyricsAvailable() = lyrics.isNotBlank() || lyricsUrl.isNotBlank()
