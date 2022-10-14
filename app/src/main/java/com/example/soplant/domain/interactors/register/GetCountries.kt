package com.example.soplant.domain.interactors.register

import com.example.soplant.domain.repositories.ResourceRepository
import javax.inject.Inject

class GetCountries @Inject constructor(private val repository: ResourceRepository) {
    operator fun invoke(onlySupported: Boolean) = repository.getCountries(onlySupported)
}