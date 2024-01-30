package com.example.photonassesment.model.data

import com.example.photonassesment.model.NySchoolItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchoolsRemoteSource @Inject constructor(private val apiService: ApiService) {

    private var nySchoolsList: List<NySchoolItem>? = null

    suspend fun getSchoolsList(): List<NySchoolItem> = withContext(Dispatchers.IO) {
        var nySchoolsList = nySchoolsList
        if (nySchoolsList == null) {
            nySchoolsList = apiService.getSchoolsList()
            this@SchoolsRemoteSource.nySchoolsList = nySchoolsList
        }
        return@withContext nySchoolsList
    }

}