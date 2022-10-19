package com.example.soplant.application.network.mappers

import com.example.soplant.application.network.models.ErrorDto
import com.example.soplant.domain.entities.ApiError
import com.example.soplant.domain.mappers.Mapper
import javax.inject.Inject

class ErrorMapper @Inject constructor() : Mapper<ErrorDto, ApiError> {
    override fun mapToEntity(model: ErrorDto): ApiError {
        return ApiError(
            type = model.errorType ?: "",
            message = model.errorMessage ?: ""
        )
    }

    override fun mapFromEntity(entity: ApiError): ErrorDto {
        return ErrorDto(
            errorType = entity.type,
            errorMessage = entity.message
        )
    }
}