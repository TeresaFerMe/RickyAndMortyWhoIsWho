package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.ui.theme.RickyAndMortyWhoIsWhoTheme
import com.teresaferme.rickyandmortywhoiswho.view.CharacterList
import org.koin.androidx.viewmodel.ext.android.viewModel

class RMMainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private val characterList: MutableState<List<RMCharacter>?> = mutableStateOf(null)
    private val episodeCount: MutableState<Int?> = mutableStateOf(null)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUpObservers()
        setContent {
            RickyAndMortyWhoIsWhoTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background
                ) {
                    Content()
                }
            }
        }
    }

    private fun setUpObservers() {
        mainViewModel.characterList.observe(this) {
            this.characterList.value = it
        }
        mainViewModel.episodeNumber.observe(this) {
            this.episodeCount.value = it
        }
    }

    @Composable
    private fun Content() {
        Column {
            Text(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth(),
                text = "Characters",
                fontSize = 30.sp,
                textAlign = TextAlign.Center
            )
            CharacterList(
                episodeCount = episodeCount.value,
                characterList = characterList.value,
                executeWhenEnd = mainViewModel::getMoreCharacters
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview() {
        RickyAndMortyWhoIsWhoTheme {
            Content()
        }
    }
}