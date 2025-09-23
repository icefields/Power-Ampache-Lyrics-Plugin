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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import luci.sixsixsix.powerampache2.lyricsplugin.R
import luci.sixsixsix.powerampache2.lyricsplugin.presentation.main.screenPadding

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(modifier: Modifier = Modifier, onBack: () -> Unit) {
    TopAppBar(
        modifier = modifier.background(MaterialTheme.colorScheme.primary).statusBarsPadding(),
        title = { Text(
            text = stringResource(R.string.app_name_topBar),
            fontSize = 18.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        ) },
        navigationIcon = { IconButton(onClick = onBack) {
            Icon(imageVector = Icons.AutoMirrored.Default.ArrowBack, contentDescription = "Back") }
        },
//        colors = TopAppBarDefaults.topAppBarColors(
//            containerColor = MaterialTheme.colorScheme.surface,
//            navigationIconContentColor = MaterialTheme.colorScheme.onSurface,
//            titleContentColor = MaterialTheme.colorScheme.onSurface,
//            actionIconContentColor = MaterialTheme.colorScheme.onSurface,
//        )
    )
}

@Composable
@Deprecated("Use MainTopBar(modifier, onBack")
fun MainTopBarOld(modifier: Modifier = Modifier) {
        Column(modifier) {
        Spacer(Modifier
            .fillMaxWidth()
            .height(WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
            .background(MaterialTheme.colorScheme.onBackground)
        )

        Spacer(Modifier.Companion.height(screenPadding))
        Text(
            text = stringResource(R.string.app_name_topBar),
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal,
            fontWeight = FontWeight.Bold,
            //color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.fillMaxWidth().padding(horizontal = screenPadding),
            textAlign = TextAlign.Start
        )
        Spacer(Modifier.Companion.height(screenPadding))
    }
}
