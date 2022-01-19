package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.CountryDto
import com.example.soplant.domain.entities.Country
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class CountryMapper @Inject constructor(): Mapper<CountryDto, Country> {
    override fun mapToEntity(model: CountryDto): Country {
        return Country(
            name = model.name ?: "",
            code = model.code ?: "",
            image = model.image ?: ""
        )
    }

    override fun mapFromEntity(entity: Country): CountryDto {
        return CountryDto(
            code = entity.code,
            image = entity.image,
            name = entity.name
        )
    }
}