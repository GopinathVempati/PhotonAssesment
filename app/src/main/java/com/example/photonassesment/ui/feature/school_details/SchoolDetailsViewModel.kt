package com.example.photonassesment.ui.feature.school_details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel

import androidx.lifecycle.viewModelScope
import com.example.photonassesment.model.data.SchoolsRemoteSource
import com.example.photonassesment.ui.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SchoolDetailsViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val repository: SchoolsRemoteSource
) : ViewModel() {

    var state by mutableStateOf(
        SchoolDetailsContract.State(
            null
        )
    )
        private set

    init {
        viewModelScope.launch {
            val categoryId = stateHandle.get<String>(NavigationKeys.Arg.SCHOOL_DETAILS)
                ?: throw IllegalStateException("No categoryId was passed to destination.")
            val schoolsList = repository.getSchoolsList()
            val schoolItem = schoolsList.first { it.schoolName == categoryId }
            state = state.copy(nySchoolItem = schoolItem)
        }
    }

}
