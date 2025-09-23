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
package luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import luci.sixsixsix.powerampache2.lyricsplugin.R
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.mainFontSize
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.smallFontSize

@Composable
fun ClearDbButton(
    modifier: Modifier = Modifier,
    onClearLyrics: () -> Unit
) {
    Column(modifier) {
        Button(
            shape = RoundedCornerShape(4.dp),
            onClick = onClearLyrics) {
            Text(
                text = stringResource(R.string.clear_stored_title),
                modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                textAlign = TextAlign.Center,
                fontSize = mainFontSize
            )
        }
        Text(
            text = stringResource(R.string.clear_stored_subtitle),
            fontSize = smallFontSize,
            modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
            textAlign = TextAlign.Start
        )
    }
}
