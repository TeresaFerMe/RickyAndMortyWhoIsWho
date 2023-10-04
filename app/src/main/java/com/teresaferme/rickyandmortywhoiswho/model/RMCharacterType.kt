package com.teresaferme.rickyandmortywhoiswho.model

enum class RMCharacterType(val description: String) {
    PROTAGONIST("Protagonist"),
    DEUTERAGONIST("Deuteragonist"),
    TERTIARY_CHARACTER("Tertiary"),
    EXTRA("Extra");

    companion object {
        fun getCharacterType(rate: Double): RMCharacterType {
            return if (rate == 1.0) PROTAGONIST
            else if (rate > 0.8) DEUTERAGONIST
            else if (rate > 0.5) TERTIARY_CHARACTER
            else EXTRA
        }
    }
}