package com.example.soplant.domain.interactors.wall

import com.example.soplant.domain.repositories.ProductsRepository
import java.sql.Timestamp
import javax.inject.Inject

class GetOfflineWall @Inject constructor(private val repository: ProductsRepository) {
    operator fun invoke(lastPaginationTimestamp: Long) = repository.getOfflineWall(lastPaginationTimestamp)
}