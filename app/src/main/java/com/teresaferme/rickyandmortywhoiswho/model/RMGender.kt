package com.teresaferme.rickyandmortywhoiswho.model

enum class RMGender(val value: String) {
    MALE("male"),
    FEMALE("female"),
    UNKNOWN("unknown");

    companion object {
        fun getFrom(value: String): RMGender {
            RMGender.values().forEach {
                if (it.value == value) return it
            }
            return UNKNOWN
        }
    }
}