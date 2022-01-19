package com.example.soplant.domain.repositories

import com.example.soplant.domain.entities.Country
import com.example.soplant.domain.utils.Resource
import kotlinx.coroutines.flow.Flow

interface ResourceRepository {
    fun getCountries(): Flow<Resource<List<Country>>>
}