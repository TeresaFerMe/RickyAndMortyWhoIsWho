package com.teresaferme.rickyandmortywhoiswho.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class MainViewModel() : ViewModel(), KoinComponent {

    var characterList: MutableLiveData<MutableList<RMCharacter>?> = MutableLiveData()
    var episodeNumber: MutableLiveData<Int?> = MutableLiveData()
    private val interactor: RMMainInteractor by inject()

    init {
        getCharacterList()
        getEpisodeList()
    }

    fun getMoreCharacters() {
        viewModelScope.launch {
            interactor.getMoreCharacters {
                characterList.value = it
            }
        }
    }

    private fun getCharacterList() {
        viewModelScope.launch {
            interactor.getCharacterList {
                characterList.value = it
            }
        }
    }

    private fun getEpisodeList() {
        viewModelScope.launch {
            interactor.getEpisodeList {
                episodeNumber.value = it
            }
        }
    }

}