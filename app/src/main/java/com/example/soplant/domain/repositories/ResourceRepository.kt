package com.example.soplant.domain.repositories

import com.example.soplant.domain.entities.Country
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ResourceRepository {
    fun getCountries(onlySupported: Boolean): Flow<Resource<List<Country>>>
}