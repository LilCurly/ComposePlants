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
    suspend fun getCountries(onlySupported: Boolean): CustomResponse<List<Country>> {
        val queryMap = mutableMapOf<String, String>()
        queryMap["supported"] = if (onlySupported) "true" else "false"
        val result = resourceService.getCountries(queryMap)
        return CustomResponse(
            isSuccessful = result.isSuccessful,
            code = result.code(),
            value = result.body()?.map {
                countryMapper.mapToEntity(it)
            }
        )
    }
}