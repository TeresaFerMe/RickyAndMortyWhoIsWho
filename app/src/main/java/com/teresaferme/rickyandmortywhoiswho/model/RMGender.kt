package com.teresaferme.rickyandmortywhoiswho.model

import com.teresaferme.rickyandmortywhoiswho.R

enum class RMGender(val value: String, val resourceId: Int) {
    MALE("Male", R.drawable.baseline_male_24),
    FEMALE("Female", R.drawable.baseline_female_24),
    UNKNOWN("Unknown", R.drawable.baseline_question_mark_24);

    companion object {
        fun getFrom(value: String): RMGender {
            RMGender.values().forEach {
                if (it.value == value) return it
            }
            return UNKNOWN
        }
    }
}