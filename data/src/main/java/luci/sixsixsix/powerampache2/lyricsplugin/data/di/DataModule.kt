package luci.sixsixsix.powerampache2.lyricsplugin.data.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.MainNetwork
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.BASE_URL
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.TIMEOUT_CONNECTION_S
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.TIMEOUT_READ_S
import luci.sixsixsix.powerampache2.lyricsplugin.data.genius_api.common.TIMEOUT_WRITE_S
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference
import java.util.concurrent.TimeUnit
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
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideAmpacheApi(retrofit: Retrofit): MainNetwork =
        retrofit.create(MainNetwork::class.java)


//    @Provides
//    @Singleton
//    fun provideMusicDatabase(application: Application): MusicDatabase =
//        Room.databaseBuilder(
//            application,
//            MusicDatabase::class.java,
//            DB_LOCAL_NAME
//        )
//            //.fallbackToDestructiveMigration()
//            //.addMigrations(MIGRATION_73_74())
//            .build()

}