package com.example.photonassesment.ui.feature.schools

import com.example.photonassesment.model.NySchoolItem


class SchoolContract {

    data class State(
        val schoolsList: List<NySchoolItem> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}