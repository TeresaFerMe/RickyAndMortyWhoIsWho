package com.teresaferme.rickyandmortywhoiswho.model

import androidx.compose.runtime.MutableState

data class Filters(
    var gender: MutableState<RMGender?>,
    var status: MutableState<RMStatus?>
)