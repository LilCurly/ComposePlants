package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.DeliveryCountriesAdditionalDto
import com.example.soplant.domain.entities.DeliveryCountriesAdditional
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class DeliveryCountriesAdditionalMapper @Inject constructor(): Mapper<DeliveryCountriesAdditionalDto?, DeliveryCountriesAdditional> {
    override fun mapToEntity(model: DeliveryCountriesAdditionalDto?): DeliveryCountriesAdditional {
        return DeliveryCountriesAdditional(
            countries = model?.countries ?: listOf(),
            additionalDeliveryPrice = model?.additionalDeliveryPrice ?: ""
        )
    }

    override fun mapFromEntity(entity: DeliveryCountriesAdditional): DeliveryCountriesAdditionalDto {
        return DeliveryCountriesAdditionalDto(
            countries = entity.countries,
            additionalDeliveryPrice = entity.additionalDeliveryPrice
        )
    }
}