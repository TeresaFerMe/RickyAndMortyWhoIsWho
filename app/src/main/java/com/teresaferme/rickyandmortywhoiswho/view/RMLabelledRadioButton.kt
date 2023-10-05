package com.teresaferme.rickyandmortywhoiswho.view

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun RMLabelledRadioButton(isSelected: Boolean, label: String, executeOnClick: (Boolean) -> Unit) {
    var isSelected by remember { mutableStateOf(isSelected) }
    Row {
        RadioButton(selected = isSelected, onClick = {
            isSelected = !isSelected
            executeOnClick.invoke(isSelected) })
        Text(text = label)
    }
}