package jp.kyam.songfinder.api.spotify

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpotifyTokenResponse(
    @Json(name = "access_token")
    val accessToken: String,

    @Json(name = "token_type")
    val tokenType: String,

    /**
     * 有効期限:秒
     */
    @Json(name = "expires_in")
    val expiresIn: Int
)
