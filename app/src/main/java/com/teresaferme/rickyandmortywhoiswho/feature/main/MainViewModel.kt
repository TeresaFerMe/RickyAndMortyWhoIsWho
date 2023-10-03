package com.teresaferme.rickyandmortywhoiswho.feature.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMSpecies

class MainViewModel: ViewModel() {
    var characterList: MutableLiveData<List<RMCharacter>?> = MutableLiveData()

    init {
        this.getCharacterList()
    }

    private fun getCharacterList() {
        this.characterList.value = listOf(
            RMCharacter(
                name = "Name example",
                RMGender.FEMALE,
                RMSpecies.HUMAN
            ),
            RMCharacter(
                name = "Name example",
                RMGender.FEMALE,
                RMSpecies.HUMAN
            ),
            RMCharacter(
                name = "Name example",
                RMGender.FEMALE,
                RMSpecies.HUMAN
            )
        )
    }

}