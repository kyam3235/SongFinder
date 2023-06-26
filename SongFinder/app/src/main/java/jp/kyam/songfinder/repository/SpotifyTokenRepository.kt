package jp.kyam.songfinder.repository

import jp.kyam.songfinder.BuildConfig
import jp.kyam.songfinder.api.spotify.SpotifyApi
import jp.kyam.songfinder.api.spotify.SpotifyTokenResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SpotifyTokenRepository @Inject constructor(private val spotifyApi: SpotifyApi) {
    // TODO トークンをキャッシュするよう変更
    suspend fun requestToken(): Flow<SpotifyTokenResponse> = flow {
        val response = spotifyApi.requestToken(
            grantType = "client_credentials",
            clientId = BuildConfig.CLINET_ID,
            clientSecret = BuildConfig.CLINET_SECRET
        )
        if (response.isSuccessful) {
            response.body()?.let {
                emit(it)
            }
        } else {
            throw HttpException(response)
        }
    }.catch { throwable -> throw throwable }
        .flowOn(Dispatchers.IO)
}