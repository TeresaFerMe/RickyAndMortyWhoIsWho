package com.teresaferme.rickyandmortywhoiswho.model

enum class RMStatus(val value: String) {
    LIFE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown");

    companion object {
        fun getFrom(value: String): RMStatus {
            RMStatus.values().forEach {
                if (it.value == value) return it
            }
            return UNKNOWN
        }
    }
}