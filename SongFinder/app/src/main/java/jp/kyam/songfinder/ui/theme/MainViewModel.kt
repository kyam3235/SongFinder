package jp.kyam.songfinder.ui.theme

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import jp.kyam.songfinder.model.SpotifyToken
import jp.kyam.songfinder.repository.SpotifyTokenRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val spotifyTokenRepository: SpotifyTokenRepository
) : ViewModel() {

    private val _spotifyToken = MutableLiveData<SpotifyToken>()
    val spotifyToken: LiveData<SpotifyToken> = _spotifyToken

    init {
        viewModelScope.launch {
            spotifyTokenRepository.requestToken().collectLatest {
                _spotifyToken.value = it
            }
        }
    }

    fun onRequestToken() {
        viewModelScope.launch {
            spotifyTokenRepository.requestToken().collectLatest {
                _spotifyToken.value = it
            }
        }
    }
}