package com.teresaferme.rickyandmortywhoiswho.model

enum class RMSpecies(val value: String) {
    HUMAN("Human"),
    ALIEN("Alien"),
    HUMANOID("Humanoid"),
    ANIMAL("Animal"),
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