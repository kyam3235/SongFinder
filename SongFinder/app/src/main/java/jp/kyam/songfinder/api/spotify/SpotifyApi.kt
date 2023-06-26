package jp.kyam.songfinder.api.spotify

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface SpotifyApi {
    @FormUrlEncoded
    @POST("api/token")
    suspend fun requestToken(
        @Field("grant_type") grantType: String,
        @Field("client_id") clientId: String,
        @Field("client_secret") clientSecret: String
    ): Response<SpotifyTokenResponse>
}