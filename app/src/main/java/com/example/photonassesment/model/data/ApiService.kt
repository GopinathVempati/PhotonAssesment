package com.example.photonassesment.model.data

import com.example.photonassesment.model.NySchoolItem
import retrofit2.http.GET
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiService @Inject constructor(private val service: Service) {

    suspend fun getSchoolsList(): List<NySchoolItem> = service.getSchools()

    interface Service {
        @GET("s3k6-pzi2.json")
        suspend fun getSchools(): List<NySchoolItem>
    }

    companion object {
        const val BASE_URL = "https://data.cityofnewyork.us/resource/"
    }
}


