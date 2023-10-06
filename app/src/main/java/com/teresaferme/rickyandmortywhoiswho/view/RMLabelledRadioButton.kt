package com.teresaferme.rickyandmortywhoiswho.view

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf

@SuppressLint("UnrememberedMutableState")
@Composable
fun RMLabelledRadioButton(isSelected: Boolean, label: String, executeOnClick: (Boolean) -> Unit) {
    var isSelected = mutableStateOf(isSelected)
    Row {
        RadioButton(selected = isSelected.value, onClick = {
            isSelected.value = !isSelected.value
            executeOnClick.invoke(isSelected.value) })
        Text(text = label)
    }
}