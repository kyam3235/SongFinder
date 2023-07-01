package jp.kyam.songfinder.data

class SpotifyTokenDataSourceImpl : SpotifyTokenDataSource {
    private var token: String = ""

    override fun getToken(): String = token

    override fun setToken(token: String) {
        this.token = token
    }
}