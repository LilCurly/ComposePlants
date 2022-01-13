package com.example.soplant.application.network.mappers


import com.example.soplant.application.network.models.ProductListDto
import com.example.soplant.domain.entities.ProductList
import com.example.soplant.domain.mappers.Mapper
import com.squareup.moshi.Json
import javax.inject.Inject

class ProductListMapper @Inject constructor(private val productMapper: ProductMapper): Mapper<ProductListDto, ProductList> {
    override fun mapToEntity(model: ProductListDto): ProductList {
        return ProductList(
            products = model.products?.map {
                productMapper.mapToEntity(it)
            } ?: listOf(),
            lastPaginationId = model.lastPaginationId ?: "",
            isLast = model.isLast ?: false
        )
    }

    override fun mapFromEntity(entity: ProductList): ProductListDto {
        return ProductListDto(
            products = entity.products.map {
                productMapper.mapFromEntity(it)
            },
            lastPaginationId = entity.lastPaginationId,
            isLast = entity.isLast
        )
    }
}