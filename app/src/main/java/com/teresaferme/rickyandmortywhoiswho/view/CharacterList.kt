package com.teresaferme.rickyandmortywhoiswho.view

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMSpecies

@Composable
fun CharacterList(
    characterList: List<RMCharacter>
) {
    LazyColumn(content = {
        characterList.forEach { character ->
            item {
                CharacterListItem(
                    character
                )
            }
        }
    })
}

@Preview
@Composable
fun CharacterListPreview() {
    CharacterList(
        listOf(
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
    )
}