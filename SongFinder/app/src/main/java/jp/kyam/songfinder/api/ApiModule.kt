package jp.kyam.songfinder.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import jp.kyam.songfinder.BuildConfig
import jp.kyam.songfinder.api.spotify.SpotifyApi
import jp.kyam.songfinder.api.spotify.SpotifyInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {
    @Singleton
    @Provides
    fun providePostApi(retrofit: Retrofit): SpotifyApi = retrofit.create(SpotifyApi::class.java)

    @Singleton
    @Provides
    fun provideSpotifyInterceptor(): SpotifyInterceptor =
        SpotifyInterceptor(
            clientId = BuildConfig.CLINET_ID,
            clientSecret = BuildConfig.CLINET_SECRET
        )

    @Singleton
    @Provides
    fun provideOkHttpClient(spotifyInterceptor: SpotifyInterceptor): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(90, TimeUnit.SECONDS)
            .readTimeout(90, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        }

        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.SPOTIFY_BASE_URL)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .client(okHttpClient)
            .build()
    }
}