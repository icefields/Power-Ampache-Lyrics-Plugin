package luci.sixsixsix.powerampache2.lyricsplugin.data.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.BASE_URL_GENIUS
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.DATABASE_NAME
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.TIMEOUT_CONNECTION_S
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.TIMEOUT_READ_S
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.common.TIMEOUT_WRITE_S
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.genius.MainNetwork
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.local.PluginDatabase
import luci.sixsixsix.powerampache2.lyricsplugin.data.api.powerlyrics.PowerLyricsMainNetwork
import luci.sixsixsix.powerampache2.lyricsplugin.domain.local.SharedPreferencesManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

typealias WeakContext = WeakReference<Context>

@Module
@InstallIn(SingletonComponent::class)
object DataModule {

    /**
     * Provides application managed coroutine scope for usage in data layer.
     */
    @Provides
    @Singleton
    fun provideApplicationScope() = CoroutineScope(Dispatchers.IO + SupervisorJob())

    @Provides
    @Singleton
    fun provideWeakApplicationContext(application: Application) =
        WeakContext(application.applicationContext)

    @Provides
    @Singleton
    fun provideOkHttp() = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .connectTimeout(TIMEOUT_CONNECTION_S, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_READ_S, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_WRITE_S, TimeUnit.SECONDS)
        .build()

    @Provides
    @Singleton
    @GeniusApi
    fun provideGeniusRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_GENIUS)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    @PowerLyricsApi
    fun providePowerLyricsRetrofit(
        okHttpClient: OkHttpClient,
        sharedPreferencesManager: SharedPreferencesManager
    ): Retrofit = Retrofit.Builder()
        .baseUrl(sharedPreferencesManager.selectedMirror)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build().also {
            println(sharedPreferencesManager.selectedMirror)
        }

    @Provides
    @Singleton
    @GeniusApi
    fun provideGeniusApi(@GeniusApi retrofit: Retrofit): MainNetwork =
        retrofit.create(MainNetwork::class.java)

    @Provides
    @Singleton
    @PowerLyricsApi
    fun provideAmpacheApi(@PowerLyricsApi retrofit: Retrofit): PowerLyricsMainNetwork =
        retrofit.create(PowerLyricsMainNetwork::class.java)


    @Provides
    @Singleton
    fun providePluginDatabase(application: Application): PluginDatabase =
        Room.databaseBuilder(
            application,
            PluginDatabase::class.java,
            DATABASE_NAME
        ).build()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class GeniusApi

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class PowerLyricsApi