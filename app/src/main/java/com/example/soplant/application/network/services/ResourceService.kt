package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.CountryListDto
import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.GET

interface ResourceService {
    @GET(Constants.Endpoints.ResourceApi.COUNTRIES)
    suspend fun getCountries(): Response<CountryListDto>
}