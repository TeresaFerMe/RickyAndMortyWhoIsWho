package com.teresaferme.rickyandmortywhoiswho

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMSpecies
import com.teresaferme.rickyandmortywhoiswho.ui.theme.RickyAndMortyWhoIsWhoTheme
import com.teresaferme.rickyandmortywhoiswho.view.CharacterList

class RMMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickyAndMortyWhoIsWhoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }
}

@Composable
private fun Content() {
    CharacterList(characterList = listOf(
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
    ))
}
@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    RickyAndMortyWhoIsWhoTheme {
        Content()
    }
}