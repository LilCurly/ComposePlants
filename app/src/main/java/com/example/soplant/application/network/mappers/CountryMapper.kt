package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.CountryDto
import com.example.soplant.application.network.models.CountryImageDto
import com.example.soplant.application.network.models.CountryNameDto
import com.example.soplant.domain.entities.Country
import com.example.soplant.domain.entities.CountryImage
import com.example.soplant.domain.entities.CountryName
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class CountryMapper @Inject constructor(): Mapper<CountryDto, Country> {
    override fun mapToEntity(model: CountryDto): Country {
        return Country(
            name = CountryName(
                common = model.name?.common ?: ""
            ),
            code = model.cca2 ?: "",
            image = CountryImage(
                png = model.flags?.png ?: ""
            )
        )
    }

    override fun mapFromEntity(entity: Country): CountryDto {
        return CountryDto(
            name = CountryNameDto(
                common = entity.name.common
            ),
            cca2 = entity.code,
            flags = CountryImageDto(
                png = entity.image.png
            )
        )
    }
}