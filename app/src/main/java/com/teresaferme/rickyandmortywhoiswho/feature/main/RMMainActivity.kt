package com.teresaferme.rickyandmortywhoiswho.feature.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.teresaferme.rickyandmortywhoiswho.model.Filters
import com.teresaferme.rickyandmortywhoiswho.model.RMCharacter
import com.teresaferme.rickyandmortywhoiswho.model.RMGender
import com.teresaferme.rickyandmortywhoiswho.model.RMStatus
import com.teresaferme.rickyandmortywhoiswho.ui.theme.RickyAndMortyWhoIsWhoTheme
import com.teresaferme.rickyandmortywhoiswho.view.CharacterList
import com.teresaferme.rickyandmortywhoiswho.view.RMLabelledRadioButton
import org.koin.androidx.viewmodel.ext.android.viewModel

class RMMainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    private val characterList: MutableState<List<RMCharacter>?> = mutableStateOf(null)
    private val episodeCount: MutableState<Int?> = mutableStateOf(null)
    private var showFilters = mutableStateOf(false)
    private var activeFilters = Filters(mutableStateOf(null), mutableStateOf(null))
    private var selectedFilters = Filters(mutableStateOf(null), mutableStateOf(null))

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
            Row(Modifier.padding(horizontal = 20.dp, vertical = 12.dp)) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.75f, true),
                    text = "Characters",
                    fontSize = 30.sp
                )
                Button(modifier = Modifier
                    .weight(0.25f, true),
                    contentPadding = PaddingValues(8.dp),
                    onClick = {
                        showFilters.value = true
                    }) {
                    Text(
                        modifier = Modifier.padding(0.dp), text = "Filter"
                    )
                }
            }
            CharacterList(
                episodeCount = episodeCount.value,
                characterList = characterList.value,
                executeWhenEnd = mainViewModel::getMoreCharacters
            )
        }
        if (showFilters.value) DisplayPopUp()
    }

    @Composable
    private fun DisplayPopUp() {
        Popup(alignment = Alignment.Center, onDismissRequest = {
            selectedFilters = activeFilters
            this.showFilters.value = false
        }) {
            Box(
                Modifier
                    .background(Color.LightGray.copy(alpha = 0.5f))
                    .fillMaxSize()
                    .padding(24.dp)
                    .clickable {
                        selectedFilters = activeFilters
                        this.showFilters.value = false
                    }) {
                Column(
                    Modifier
                        .background(Color.LightGray)
                        .fillMaxWidth()
                        .align(Alignment.Center)
                        .clip(RoundedCornerShape(12.dp))
                        .padding(24.dp)
                ) {
                    DisplayGenderFilters()
                    DisplayStatusFilters()
                    Button(modifier = Modifier
                        .padding(vertical = 12.dp),
                        contentPadding = PaddingValues(8.dp),
                        onClick = {
                            activeFilters = selectedFilters
                            showFilters.value = false
                            mainViewModel.getFilteredCharacters(activeFilters)
                        }) {
                        Text(
                            modifier = Modifier.padding(0.dp), text = "Apply"
                        )
                    }
                }
            }
        }
    }

    @Composable
    private fun DisplayGenderFilters() {
        Text(text = "Gender")
        RMGender.values().forEach {
            RMLabelledRadioButton(selectedFilters.gender.value == it, it.value) { enabled ->
                selectedFilters.gender.value =  if (enabled) it else null
            }
        }
    }

    @Composable
    private fun DisplayStatusFilters() {
        Text(text = "Status")
        RMStatus.values().forEach {
            RMLabelledRadioButton(selectedFilters.status.value == it, it.value) { enabled ->
                selectedFilters.status.value =  if (enabled) it else null
            }
        }
    }
}