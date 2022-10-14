package com.example.soplant.application.network.services

import com.example.soplant.application.network.models.CountryDto
import com.example.soplant.application.network.models.CountryListDto
import com.example.soplant.commons.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface ResourceService {
    @GET(Constants.Endpoints.ResourceApi.COUNTRIES)
    suspend fun getCountries(@QueryMap queryMap: Map<String, String>): Response<List<CountryDto>>
}