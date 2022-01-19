package com.example.soplant.application.network.sources

import com.example.soplant.application.network.mappers.CountryMapper
import com.example.soplant.application.network.services.ResourceService
import com.example.soplant.application.utils.CustomResponse
import com.example.soplant.domain.entities.Country
import javax.inject.Inject

class ResourceRemoteDataSource @Inject constructor(
    private val resourceService: ResourceService,
    private val countryMapper: CountryMapper
) {
    suspend fun getCountries(): CustomResponse<List<Country>> {
        val result = resourceService.getCountries()
        return CustomResponse(
            isSuccessful = result.isSuccessful,
            code = result.code(),
            value = result.body()?.results?.map {
                countryMapper.mapToEntity(it)
            }
        )
    }
}