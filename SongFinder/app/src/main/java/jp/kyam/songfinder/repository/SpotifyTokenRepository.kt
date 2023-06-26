package jp.kyam.songfinder.repository

import jp.kyam.songfinder.BuildConfig
import jp.kyam.songfinder.api.spotify.SpotifyApi
import jp.kyam.songfinder.api.spotify.SpotifyTokenResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpotifyTokenRepository @Inject constructor(private val spotifyApi: SpotifyApi) {
    suspend fun requestToken(): SpotifyTokenResponse? {
        val response = spotifyApi.requestToken(
            grantType = "client_credentials",
            clientId = BuildConfig.CLINET_ID,
            clientSecret = BuildConfig.CLINET_SECRET
        )
        return response.body()
    }
}