package com.teresaferme.rickyandmortywhoiswho.model

enum class RMSpecies(val value: String) {
    HUMAN("Human"),
    UNKNOWN("Unknown");

    companion object {
        fun getFrom(value: String): RMSpecies {
            RMSpecies.values().forEach {
                if (it.value == value) return it
            }
            return UNKNOWN
        }
    }
}