package com.example.soplant.domain.mappers

interface Mapper<M, E> {
    fun mapToEntity(model: M): E
    fun mapFromEntity(entity: E): M
}