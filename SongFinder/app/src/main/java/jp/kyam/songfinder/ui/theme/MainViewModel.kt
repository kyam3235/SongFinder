package jp.kyam.songfinder.ui.theme

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.kyam.songfinder.api.Future
import jp.kyam.songfinder.api.spotify.SpotifyTokenResponse
import jp.kyam.songfinder.repository.SpotifyTokenRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val spotifyTokenRepository: SpotifyTokenRepository
) : ViewModel() {

    val spotifyToken = MutableLiveData<Future<SpotifyTokenResponse>>(Future.Proceeding)

    init {
        viewModelScope.launch {
            spotifyTokenRepository.requestToken()
        }
    }
}