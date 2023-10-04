package com.teresaferme.rickyandmortywhoiswho.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter

@Composable
fun CharacterList(
    characterList: List<RMCharacter>?,
    episodeCount: Int?
) {
    LazyColumn(content = {
        characterList?.forEach { character ->
            item {
                CharacterListItem(
                    episodeCount,
                    character
                )
            }
        }
    })
}