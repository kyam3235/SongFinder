package jp.kyam.songfinder.data

interface SpotifyTokenDataSource {
    fun getToken(): String
    fun setToken(token: String)
}