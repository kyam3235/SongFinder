package jp.kyam.songfinder.api.spotify

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Singleton

@Singleton
class SpotifyInterceptor(
    private val clientId: String,
    private val clientSecret: String
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().newBuilder().addHeader(
            name = "Authorization",
            value = Credentials.basic(clientId, clientSecret)
        ).addHeader("Content-Type", "x-www-form-urlencoded")
            .build()
        return chain.proceed(newRequest)
    }
}