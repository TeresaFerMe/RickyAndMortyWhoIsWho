package com.teresaferme.rickyandmortywhoiswho.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.teresaferme.rickyandmortywhoiswho.R
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGetCharactersPlaceResponseModel
import com.teresaferme.rickyandmortywhoiswho.model.RMStatus

@Composable
fun CharacterListItem(
    episodeCount: Int?,
    model: RMCharacter
) {
    Card(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 24.dp, vertical = 12.dp)
    ) {
        Row {
            Box(
                modifier = Modifier
                    .wrapContentSize()
            ) {
                AsyncImage(
                    modifier = Modifier.clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                    model = model.image,
                    contentDescription = model.image
                )
                Icon(
                    modifier = Modifier.align(Alignment.TopEnd).padding(12.dp).size(32.dp),
                    painter = painterResource(id = model.getGender().resourceId),
                    contentDescription = "Gender",
                    tint = Color.White
                )
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(20.dp)
            ) {
                Text(fontWeight = FontWeight.Bold, fontSize = 20.sp, text = model.name)
                Text(text = model.getSpecies().value)
                if (model.getStatus() == RMStatus.DEAD) {
                    Image(
                        modifier = Modifier.padding(12.dp).size(32.dp).align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.image_dead),
                        contentDescription = "Dead"
                    )
                }
                //TODO TERESA implement protagonism level
            }
        }

    }
}

@Preview(showSystemUi = true)
@Composable
fun CharacterListItemsPreview() {
    CharacterListItem(
        null,
        RMCharacter(
            id = "name",
            name = "status",
            status = "Dead",
            species = "type",
            type = "gender",
            gender = "origin",
            origin = RMGetCharactersPlaceResponseModel("location", "url"),
            location = RMGetCharactersPlaceResponseModel("location", "url"),
            image = "https://rickandmortyapi.com/api/character/avatar/165.jpeg",
            episode = arrayOf("url"),
            url = "created",
            created = "url"
        )
    )
}