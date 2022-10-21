package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.DeliveryPricesRangeDto
import com.example.soplant.domain.entities.DeliveryPricesRange
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class DeliveryPricesRangeMapper @Inject constructor(): Mapper<DeliveryPricesRangeDto?, DeliveryPricesRange> {
    override fun mapToEntity(model: DeliveryPricesRangeDto?): DeliveryPricesRange {
        return DeliveryPricesRange(
            rangeMax = model?.rangeMax ?: 0,
            rangeMin = model?.rangeMin ?: 0,
            price = model?.price ?: ""
        )
    }

    override fun mapFromEntity(entity: DeliveryPricesRange): DeliveryPricesRangeDto {
        return DeliveryPricesRangeDto(
            rangeMin = entity.rangeMin,
            rangeMax = entity.rangeMax,
            price = entity.price
        )
    }

}
