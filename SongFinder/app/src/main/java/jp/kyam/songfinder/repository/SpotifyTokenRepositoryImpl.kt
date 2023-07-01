package jp.kyam.songfinder.repository

import jp.kyam.songfinder.BuildConfig
import jp.kyam.songfinder.api.spotify.SpotifyApi
import jp.kyam.songfinder.model.SpotifyToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import javax.inject.Inject

class SpotifyTokenRepositoryImpl @Inject constructor(private val spotifyApi: SpotifyApi) :
    SpotifyTokenRepository {
    // TODO トークンをキャッシュするよう変更
    override suspend fun requestToken(): Flow<SpotifyToken> = flow {
        val response = spotifyApi.requestToken(
            grantType = "client_credentials",
            clientId = BuildConfig.CLINET_ID,
            clientSecret = BuildConfig.CLINET_SECRET
        )
        if (response.isSuccessful) {
            response.body()?.let {
                val token = SpotifyToken(
                    accessToken = it.accessToken,
                    tokenType = it.tokenType,
                    expiresIn = it.expiresIn
                )
                emit(token)
            }
        } else {
            throw HttpException(response)
        }
    }.catch { throwable -> throw throwable }
        .flowOn(Dispatchers.IO)
}