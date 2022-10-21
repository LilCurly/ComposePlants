package com.example.soplant.domain.interactors.wall

import com.example.soplant.domain.repositories.ProductsRepository
import javax.inject.Inject

class GetUserWall @Inject constructor(private val repository: ProductsRepository) {
    operator fun invoke(lastPaginationTimestamp: Long?) = repository.getUserWall(lastPaginationTimestamp)
}