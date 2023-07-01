package jp.kyam.songfinder.repository

import jp.kyam.songfinder.model.SpotifyToken
import kotlinx.coroutines.flow.Flow

interface SpotifyTokenRepository {
    suspend fun requestToken(): Flow<SpotifyToken>
}