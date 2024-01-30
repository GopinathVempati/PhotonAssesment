package com.example.photonassesment.ui.feature.school_details


import com.example.photonassesment.model.NySchoolItem


class SchoolDetailsContract {
    data class State(
        val nySchoolItem: NySchoolItem? = null
    )
}