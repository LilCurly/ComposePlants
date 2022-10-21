package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.PlantDto
import com.example.soplant.domain.entities.Plant
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class PlantMapper @Inject constructor(): Mapper<PlantDto?, Plant> {
    override fun mapToEntity(model: PlantDto?): Plant {
        return Plant(
            id = model?.plantId ?: "",
            isPetFriendly = model?.isPetFriendly ?: false,
            typeDisplay = model?.typeDisplay ?: "",
            typeFilter = model?.typeFilter ?: "",
            isBeginnerFriendly = model?.isBeginnerFriendly ?: false,
            waterLevel = model?.waterLevel ?: 1,
            lightLevel = model?.lightLevel ?: 1,
            isIndoor = model?.isIndoor ?: false
        )
    }

    override fun mapFromEntity(entity: Plant): PlantDto {
        return PlantDto(
            plantId = entity.id,
            isPetFriendly = entity.isPetFriendly,
            typeDisplay = entity.typeDisplay,
            typeFilter = entity.typeFilter,
            isBeginnerFriendly = entity.isBeginnerFriendly,
            waterLevel = entity.waterLevel,
            lightLevel = entity.lightLevel,
            isIndoor = entity.isIndoor
        )
    }
}