package com.teresaferme.rickyandmortywhoiswho.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.lifecycle.MutableLiveData
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import kotlin.reflect.KMutableProperty0

@Composable
fun CharacterList(
    characterList: List<RMCharacter>?,
    episodeCount: Int?,
    executeWhenEnd: () -> Unit
) {
    LazyColumn(content = {
        characterList?.forEach { character ->
            item {
                CharacterListItem(
                    episodeCount,
                    character
                )
                if (characterList.indexOf(character) == characterList.size - 1) executeWhenEnd.invoke()
            }
        }
    })
}