package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMSpecies
import com.teresaferme.rickyandmortywhoiswho.ui.theme.RickyAndMortyWhoIsWhoTheme
import com.teresaferme.rickyandmortywhoiswho.view.CharacterList
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.startKoin

class RMMainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private val characterList: MutableState<List<RMCharacter>?> = mutableStateOf(null)
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
    }

    @Composable
    private fun Content() {
        CharacterList(
            characterList = this.characterList.value
        )
    }

    @Preview(showBackground = true)
    @Composable
    fun ContentPreview() {
        RickyAndMortyWhoIsWhoTheme {
            Content()
        }
    }
}