package jp.kyam.songfinder.api.spotify

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SpotifyTokenRequest(
    @Json(name = "grant_type")
    val clientCredential: String,

    @Json(name = "client_id")
    val clientId: String,

    @Json(name = "client_secret")
    val clientSecret: String
)
