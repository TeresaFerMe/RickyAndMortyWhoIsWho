package com.teresaferme.rickyandmortywhoiswho.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.gson.annotations.SerializedName
import com.teresaferme.rickyandmortywhoiswho.R
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMGetCharactersPlaceResponseModel
import com.teresaferme.rickyandmortywhoiswho.model.RMSpecies

@Composable
fun CharacterListItem(
    model: RMCharacter
) {
    Card (
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 12.dp)) {
        Row {
            Image(
                modifier = Modifier.padding(20.dp).clip(RoundedCornerShape(12.dp)),
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = ""
            )
            Column (
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(vertical = 20.dp)) {
                Text(text = model.name)
                Text(text = "Gender")
                Text(text = "Human")
                Text(text = "alive")
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterListItemsPreview() {
    CharacterListItem(
        RMCharacter(
            id = "name",
            name = "status",
            status = "species",
            species = "type",
            type = "gender",
            gender = "origin",
            origin = RMGetCharactersPlaceResponseModel("location", "url"),
            location = RMGetCharactersPlaceResponseModel("location", "url"),
            image = "episode",
            episode = arrayOf("url"),
            url = "created",
            created = "url"
        )
    )
}