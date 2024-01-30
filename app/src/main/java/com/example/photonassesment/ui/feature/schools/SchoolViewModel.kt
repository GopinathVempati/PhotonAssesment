package com.example.photonassesment.ui.feature.schools

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photonassesment.model.data.SchoolsRemoteSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val remoteSource: SchoolsRemoteSource) :
    ViewModel() {

    var state by mutableStateOf(
        SchoolContract.State(
            schoolsList = listOf(),
            isLoading = true
        )
    )
        private set

    var effects = Channel<SchoolContract.Effect>(UNLIMITED)
        private set

    init {
        viewModelScope.launch { getSchoolsList() }
    }

    private suspend fun getSchoolsList() {
        val schoolList = remoteSource.getSchoolsList()
        viewModelScope.launch {
            state = state.copy(schoolsList = schoolList, isLoading = false)
            effects.send(SchoolContract.Effect.DataWasLoaded)
        }
    }

}



