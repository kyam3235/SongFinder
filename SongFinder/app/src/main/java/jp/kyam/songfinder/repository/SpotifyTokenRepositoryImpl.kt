package jp.kyam.songfinder.repository

import android.util.Log
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

class SpotifyTokenRepositoryImpl @Inject constructor(
    private val spotifyApi: SpotifyApi
) : SpotifyTokenRepository {
    private var token: SpotifyToken? = null

    override suspend fun requestToken(): Flow<SpotifyToken> = flow {
        token?.let {
            emit(it)
            Log.d("SpotifyToken", "get from cache")
            return@flow
        }

        val response = spotifyApi.requestToken(
            grantType = "client_credentials",
            clientId = BuildConfig.CLINET_ID,
            clientSecret = BuildConfig.CLINET_SECRET
        )
        if (response.isSuccessful) {
            response.body()?.let {
                val newToken = SpotifyToken(
                    accessToken = it.accessToken,
                    tokenType = it.tokenType,
                    expiresIn = it.expiresIn
                )
                token = newToken
                emit(newToken)
                Log.d("SpotifyToken", "get from API")
            }
        } else {
            throw HttpException(response)
        }
    }.catch { throwable -> throw throwable }
        .flowOn(Dispatchers.IO)
}