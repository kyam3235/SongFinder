package jp.kyam.songfinder.api.spotify

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SpotifyTokenResponse(
    @SerialName("access_token")
    val accessToken: String,

    @SerialName("token_type")
    val tokenType: String,

    /**
     * 有効期限:秒
     */
    @SerialName("expires_in")
    val expiresIn: Int
)
