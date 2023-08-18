package jp.kyam.songfinder.data

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Singleton
    @Binds
    abstract fun bindSpotifyTokenDataSource(
        spotifyTokenDataSourceImpl: SpotifyTokenDataSourceImpl
    ): SpotifyTokenDataSource
}