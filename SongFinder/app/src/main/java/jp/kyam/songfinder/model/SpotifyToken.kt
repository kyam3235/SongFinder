package jp.kyam.songfinder.model

data class SpotifyToken(
    val accessToken: String,
    val tokenType: String,
    val expiresIn: Int
)
