package luci.sixsixsix.powerampache2.lyricsplugin.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.LyricsFetcherImpl
import luci.sixsixsix.powerampache2.lyricsplugin.data.local.SharedPreferencesManagerImpl
import luci.sixsixsix.powerampache2.lyricsplugin.domain.LyricsFetcher
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager


@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractModule {
    @Binds abstract fun bindLyricsFetcher(lyricsFetcherImpl: LyricsFetcherImpl): LyricsFetcher
    @Binds abstract fun bindSharedPreferencesManager(sharedPreferencesManager: SharedPreferencesManagerImpl): SharedPreferencesManager
}
