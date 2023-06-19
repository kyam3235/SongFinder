package jp.kyam.songfinder.api.spotify

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface SpotifyApi {
    @Headers("Content-Type")
    @POST("api/token")
    fun requestToken(
        @Body request: SpotifyTokenRequest
    ): Call<SpotifyTokenResponse>
}