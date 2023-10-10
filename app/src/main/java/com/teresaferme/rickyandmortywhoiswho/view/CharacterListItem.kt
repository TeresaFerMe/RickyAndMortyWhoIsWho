package com.teresaferme.rickyandmortywhoiswho.view

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.squareup.picasso.Picasso
import com.teresaferme.rickyandmortywhoiswho.R
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacterType
import com.teresaferme.rickyandmortywhoiswho.model.RMStatus
import kotlinx.coroutines.coroutineScope

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun CharacterListItem(
    episodeCount: Int?, model: RMCharacter
) {
    var isExtended by remember {
        mutableStateOf(false)
    }
    val modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 20.dp, vertical = 12.dp)
        .clickable {
            isExtended = !isExtended
        }
    if (!isExtended) modifier.height(100.dp)
    else modifier.wrapContentHeight()
    var averageColor by remember {
        mutableStateOf<Color?>(null)
    }
    val thread: Thread = object : Thread() {
        override fun run() {
            try {
                val originalBitmap = Picasso.get().load(model.image).get()
                val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 1, 1, false)
                averageColor = Color(scaledBitmap.getPixel(0, 0)).copy(0.5f)
            } catch (e: InterruptedException) {
                e.printStackTrace()
            } catch (e: IllegalStateException) {
                e.printStackTrace()
            }
        }
    }
    thread.start()

    Card(
        modifier, elevation = CardDefaults.cardElevation(
            defaultElevation = 10.dp
        )
    ) {
        Column(Modifier.background(averageColor ?: Color.Gray)) {
            Row {
                val imageModifier = if (isExtended) Modifier.size(150.dp) else Modifier.size(100.dp)
                AsyncImage(
                    modifier = imageModifier.clip(
                        RoundedCornerShape(
                            topStart = 12.dp,
                            bottomStart = if (isExtended) 0.dp else 12.dp,
                            bottomEnd = if (isExtended) 12.dp else 0.dp
                        )
                    ), model = model.image, contentDescription = model.image
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                ) {
                    Column {
                        Text(fontWeight = FontWeight.Bold, fontSize = 20.sp, text = model.name)
                        FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                            if (isExtended) {
                                Button(
                                    modifier = Modifier.padding(vertical = 12.dp),
                                    contentPadding = PaddingValues(8.dp),
                                    onClick = { /**/ },
                                    enabled = false
                                ) {
                                    Icon(
                                        modifier = Modifier
                                            .size(24.dp),
                                        painter = painterResource(id = model.getGender().resourceId),
                                        contentDescription = "Gender",
                                        tint = Color.White
                                    )
                                }
                                episodeCount?.let {
                                    Button(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        contentPadding = PaddingValues(8.dp),
                                        onClick = { /**/ },
                                        enabled = false
                                    ) {
                                        Text(
                                            modifier = Modifier.padding(0.dp),
                                            text = calculateProtagonismLevel(model, it).description,
                                            color = Color.White
                                        )
                                    }
                                }

                                if (model.getStatus() == RMStatus.DEAD) {
                                    Button(
                                        modifier = Modifier.padding(vertical = 12.dp),
                                        contentPadding = PaddingValues(8.dp),
                                        onClick = { /**/ },
                                        enabled = false
                                    ) {
                                        Image(
                                            modifier = Modifier
                                                .size(24.dp),
                                            painter = painterResource(id = R.drawable.image_dead),
                                            contentDescription = "Dead"
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (isExtended) ExtendedItemInfo(model = model, episodeCount = episodeCount)
        }
    }
}

@Composable
private fun ExtendedItemInfo(model: RMCharacter, episodeCount: Int?) {
    Column(Modifier.padding(20.dp)) {
        Text(text = "Species: ${model.getSpecies().value}")
        Text(text = "Status: ${model.getStatus().value}")
        Text(text = "Origin: ${model.origin.name}")
        Text(text = "Location: ${model.location.name}")
        Text(text = "Appears in ${model.episode.size}/$episodeCount episodes")
    }
}

private fun calculateProtagonismLevel(model: RMCharacter, episodeCount: Int): RMCharacterType {
    return RMCharacterType.getCharacterType(
        model.episode.size.div(
            episodeCount.toDouble()
        )
    )
}